package com.lr.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Logger
 *
 * A log line follows the following format : WHEN| LOG_LEVEL| THREAD_NAME| WHO| WHAT| INFOS
 *
 * @author gattazr
 */
public class SimpleLogger implements ISimpleLogger {

	static final String DUMMY_SHORT_HASHCODE = "0000";

	/** Empty string */
	static final String EMPTY = "";

	/** the width of the thread column */
	static final int LENGTH_THREADNAME = 16;

	/** the width of the what column */
	static final int LENGTH_WHAT = 25;

	/** the width of the who column */
	static final int LENGTH_WHO = 25;

	private final static Level[] LEVELS = { Level.OFF, Level.INFO, Level.FINE, Level.FINER,
			Level.FINEST, Level.ALL, Level.CONFIG, Level.SEVERE, Level.WARNING };

	static final String NULL = "null";

	private final static String LOG_PREFIX_LEVEL = "LEVEL";

	private final static String LOG_PREFIX_LOG = "LOG";

	private final static String PATTERN_TIMESTAMP = "yyyy-MM-dd_HH-mm-ss_SSS";

	static final char REPLACE_COLUMN = '_';

	static final char SEP_COLUMN = '|';

	static final String SEP_COLUMN_DELIM = SEP_COLUMN + " ";

	private final static char SEP_LINE = '\n';

	private final static SimpleDateFormat sTimeStampFormater = new SimpleDateFormat(
			PATTERN_TIMESTAMP);

	private final static String THROWABLE_TITLE = "\n---------------- %d -- %s ----------------------";

	/**
	 * Create a String THREAD_NAME| WHO| WHAT| INFOS
	 *
	 * @param aThread
	 *            Thread from which the event is coming from
	 * @param aWho
	 *            Object from which the event is coming from
	 * @param aWhat
	 *            Simple name for event
	 * @param aInfos
	 *            Detailed informations about the event
	 * @return builded String
	 */
	public static String buildLogLine(final Thread aThread, final Object aWho,
			final CharSequence aWhat, final Object... aInfos) {

		String wLogWho = buildWhoObjectId(aWho);

		String wLogWhat = (aWhat != null) ? aWhat.toString() : NULL;

		String wLogText = buildLogText(aInfos);

		return formatLine(aThread.getName(), wLogWho, wLogWhat, wLogText);
	}

	/**
	 * Build a String identifying an Object.
	 *
	 * <ul>
	 * <li>The value empty string is used for every object set to null.</li>
	 * <li>If the array contains a single object, the value toString of that object is returned</li>
	 * <li>If the array contains several object, the value toString of these objects are returned in
	 * the following format [Object1, Object2, ...]</li>
	 * <li>If the first Object is a format, the other Objects are injected into that format.</li>
	 * </ul>
	 *
	 * @param aObjects
	 *            Array of object to transform into String
	 * @return builded String
	 */
	public static String buildLogText(final Object... aObjects) {

		if ((aObjects == null) || (aObjects.length == 0)) {
			return EMPTY;
		}

		StringBuilder wSB = new StringBuilder(128);

		// converts null and Throwable to strings
		Object wObj;
		for (int wI = 0; wI < aObjects.length; wI++) {
			wObj = aObjects[wI];
			if (wObj == null) {
				aObjects[wI] = NULL;
			} else if (wObj instanceof Throwable) {
				aObjects[wI] = formatThrowable((Throwable) wObj);
			} else if (aObjects[wI].getClass().isArray()) {
				aObjects[wI] = Arrays.toString((Object[]) wObj);
			}
		}

		// if there is only one info
		if (aObjects.length == 1) {
			return wSB.append(String.valueOf(aObjects[0])).toString();
		}

		// if the first object is a format, return the result of the
		// String.format() method
		if (aObjects[0].toString().indexOf('%') > -1) {
			return wSB.append(
					String.format(aObjects[0].toString(), UtilsArray.removeOneObject(aObjects, 0)))
					.toString();
		}

		// builds the text by appending the string value of each object.
		boolean wIsId = false;
		boolean wIsValue = false;
		String wStr;
		final int wMax = aObjects.length;
		for (int wI = 0; wI < wMax; wI++) {
			wIsValue = wIsId;
			wStr = String.valueOf(aObjects[wI]);
			wIsId = wStr.endsWith("=");

			if (wIsValue) {
				wSB.append('[');
			}

			wSB.append(wStr);

			if (wIsValue) {
				wSB.append(']');
			}
			if (!wIsId) {
				wSB.append(' ');
			}
		}
		return wSB.toString();
	}

	/**
	 * Build a String identifying an Object
	 *
	 * @param aWho
	 *            the Object to identify
	 * @return builded String
	 */
	public static String buildWhoObjectId(final Object aWho) {

		if (aWho == null) {
			return NULL;
		}

		if (aWho instanceof Class) {
			return ((Class<?>) aWho).getName() + '_' + DUMMY_SHORT_HASHCODE;
		}

		return new StringBuilder().append(aWho.getClass().getName()).append('_')
				.append(UtilsString.strAdjustRight(aWho.hashCode(), 4)).toString();
	}

	/**
	 * Build a String with the format "THREAD_ID| WHO| WHAT| INFOS"
	 *
	 * @param aThreadName
	 *            name of thread
	 * @param aWho
	 *            name of class
	 * @param aWhat
	 *            name of event
	 * @param aInfos
	 *            informations
	 * @return formated string
	 */
	public static String formatLine(final String aThreadName, final String aWho, final String aWhat,
			final String aInfos) {

		// clean the buffer
		StringBuilder wSB = new StringBuilder();

		wSB.append(formatThreadName(aThreadName));

		wSB.append(SEP_COLUMN_DELIM);
		wSB.append(formatWho(aWho));

		wSB.append(SEP_COLUMN_DELIM);
		wSB.append(formatWhat(aWhat));

		wSB.append(SEP_COLUMN_DELIM);
		wSB.append(formatText(aInfos));

		return wSB.toString();
	}

	/**
	 * Format a text. Returns empty String if the String is null. Returns the String otherwise.
	 *
	 * @param aText
	 *            String to format
	 * @return formated text
	 */
	public static String formatText(final String aText) {

		return (aText == null) ? NULL : aText;
	}

	/**
	 * Format the name of a Thread into a String that can be used as THREAD_NAME in a Log line
	 *
	 * @param aThreadName
	 *            Name of a tread to format
	 * @return formated name
	 */
	public static String formatThreadName(final String aThreadName) {

		return UtilsString.strAdjustRight(aThreadName, LENGTH_THREADNAME, ' ');
	}

	/**
	 * Format a throwable into a String that can be used as INFO in a log line
	 *
	 * @param aThrowable
	 *            Throwable to format
	 * @return formated throwable
	 */
	public static String formatThrowable(Throwable aThrowable) {

		StringBuilder wSB = new StringBuilder();
		int wThrowableLevel = 0;
		while (aThrowable != null) {
			wSB.append(String.format(THROWABLE_TITLE, wThrowableLevel,
					aThrowable.getClass().getName()));
			StringWriter wStack = new StringWriter();
			aThrowable.printStackTrace(new PrintWriter(wStack));
			wSB.append(SEP_LINE).append(wStack.toString());
			aThrowable = aThrowable.getCause();
			wThrowableLevel++;
		}
		return wSB.toString();
	}

	/**
	 * Format a String into a WHAT String that can be used in a Log line
	 *
	 * @param aWhat
	 *            String to format
	 * @return formated WHAT
	 */
	public static String formatWhat(final String aWhat) {

		return UtilsString.strAdjustRight(
				aWhat != null ? aWhat.replace(SEP_COLUMN, REPLACE_COLUMN) : EMPTY, LENGTH_WHAT,
				' ');
	}

	/**
	 * Format a String into a WHO String that can be used in a Log line
	 *
	 * @param aWho
	 *            String to format
	 * @return formated WHO
	 */
	public static String formatWho(final String aWho) {

		return UtilsString.strAdjustRight(
				aWho != null ? aWho.replace(SEP_COLUMN, REPLACE_COLUMN) : EMPTY, LENGTH_WHO, ' ');
	}

	private Logger pLogger;
	// The default logging folder is the temporary directory of the JVM
	private String plogFolderPath = System.getProperty("java.io.tmpdir");

	/**
	 * Create a FileHandler for the logger
	 *
	 * @param aFhPattern
	 *            Name of the FileHandler
	 * @return
	 */
	private FileHandler buildLoggerFileHandler(String aFhPattern) {
		try {
			FileHandler wFileHandler = new FileHandler(aFhPattern);

			return wFileHandler;

		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Build a FileHandler name pattern.
	 *
	 * The pattern is the following : NAME_date.txt eg.
	 * Error_ExcelFileBuilder_2013-05-31_17-40-23_256.txt
	 *
	 * @param aName
	 *            name of Handler
	 * @return
	 */
	private String buildLoggerFileNamePattern(String aName) {
		String wTimeStamp = sTimeStampFormater.format(new Date());

		String wFhPattern = String.format("%s_%s.log", aName, wTimeStamp);

		wFhPattern = new File(getLogFolderPath(), wFhPattern).getAbsolutePath();

		return wFhPattern;
	}

	private String cleanLevelName(String aLevelName) {
		if (aLevelName != null) {
			aLevelName = aLevelName.toUpperCase();

			// si LOGLEVELINFO => LEVELINFO
			if (aLevelName.startsWith(LOG_PREFIX_LOG)) {
				aLevelName = aLevelName.substring(LOG_PREFIX_LOG.length());
			}
			// si LEVELINFO => INFO
			if (aLevelName.startsWith(LOG_PREFIX_LEVEL)) {
				aLevelName = aLevelName.substring(LOG_PREFIX_LEVEL.length());
			}
		}
		return aLevelName;
	}

	/**
	 * Close the logger. Reset the level to OFF and close all handlers.
	 */
	public void close() {
		if (pLogger != null) {
			Logger wLogger = pLogger;
			pLogger = null;
			wLogger.setLevel(Level.OFF);
			for (Handler wHandler : wLogger.getHandlers()) {
				wHandler.close();
				wLogger.removeHandler(wHandler);
			}
		}
	}

	/**
	 *
	 * @return the folder in which logger files are created when a logger file handler is used
	 */
	public String getLogFolderPath() {
		return plogFolderPath;
	}

	@Override
	public boolean isLogDebugOn() {
		return isLoggable(Level.FINE);
	}

	@Override
	public boolean isLoggable(Level aLevel) {
		return (pLogger != null) && pLogger.isLoggable(aLevel);
	}

	@Override
	public boolean isLogInfoOn() {
		return isLoggable(Level.INFO);
	}

	@Override
	public boolean isLogSevereOn() {
		return isLoggable(Level.SEVERE);
	}

	@Override
	public boolean isLogWarningOn() {
		return isLoggable(Level.WARNING);
	}

	/**
	 * @param aFlag
	 *            flag to test
	 * @return true if the given flag is a valid console flag
	 */
	public boolean isValidConsoleFlag(String aFlag) {
		return "LOGCONSOLE".equalsIgnoreCase(aFlag);
	}

	/**
	 * @param aFlag
	 *            flag to test
	 * @return true if the given flag is a valid file flag
	 */
	public boolean isValidFileFlag(String aFlag) {
		return "LOGFILE".equalsIgnoreCase(aFlag);
	}

	/**
	 * Return true if the name given in parameter can be matched to an existing log level
	 *
	 * @param aLevelName
	 *            level name to test
	 * @return true if the given level is valid
	 */
	public boolean isValidLevel(String aLevelName) {
		if (aLevelName != null) {
			aLevelName = cleanLevelName(aLevelName);
			for (Level wLevel : LEVELS) {
				if (wLevel.getName().equalsIgnoreCase(aLevelName)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Return the level associated to the name given in parameter. Return OFF if no level matches
	 *
	 * @param aLevelName
	 *            name of level
	 * @return associated level
	 */
	public Level levelNameToLevel(String aLevelName) {
		if (aLevelName != null) {
			aLevelName = cleanLevelName(aLevelName);
			for (Level wLevel : LEVELS) {
				if (wLevel.getName().equalsIgnoreCase(aLevelName)) {
					return wLevel;
				}
			}
		}
		return Level.OFF;
	}

	@Override
	public void log(Level aLevel, Object aWho, CharSequence aWhat, Object... aInfos) {

		if (isLoggable(aLevel)) {
			pLogger.log(aLevel, buildLogLine(Thread.currentThread(), aWho, aWhat, aInfos));
		}
	}

	@Override
	public void logDebug(Object aWho, CharSequence aWhat, Object... aInfos) {
		log(Level.FINE, aWho, aWhat, aInfos);
	}

	@Override
	public void logInfo(Object aWho, CharSequence aWhat, Object... aInfos) {
		if (isLogInfoOn()) {
			log(Level.INFO, aWho, aWhat, aInfos);
		}
	}

	@Override
	public void logSevere(Object aWho, CharSequence aWhat, Object... aInfos) {
		if (isLogSevereOn()) {
			log(Level.SEVERE, aWho, aWhat, aInfos);
		}
	}

	@Override
	public void logWarn(Object aWho, CharSequence aWhat, Object... aInfos) {
		if (isLogWarningOn()) {
			log(Level.WARNING, aWho, aWhat, aInfos);
		}
	}

	/**
	 * Open a new logger by closing the current one if it is already open
	 *
	 * @param aName
	 *            name of logger
	 * @param aLevel
	 *            Level
	 * @param aWithConsole
	 *            true if the CONSOLE Handler is used
	 * @param aWithFile
	 *            true if the CONSOLE Handler is used
	 */
	public void open(String aName, Level aLevel, boolean aWithConsole, boolean aWithFile) {

		close();

		if ((pLogger == null) && !Level.OFF.equals(aLevel) && (aWithConsole || aWithFile)) {

			String wLoggerId = getClass().getSimpleName();
			pLogger = Logger.getLogger(wLoggerId);
			String wFhPattern = buildLoggerFileNamePattern(aName);
			pLogger.setUseParentHandlers(false);

			if (aWithConsole) {
				Handler wHandler = new ConsoleHandler();
				wHandler.setFormatter(SimpleLoggerFormatter.getFormater());
				pLogger.addHandler(wHandler);
			}

			if (aWithFile) {
				FileHandler wHandler = buildLoggerFileHandler(wFhPattern);
				wHandler.setFormatter(SimpleLoggerFormatter.getFormater());
				pLogger.addHandler(wHandler);

			}

			setLogLevel(aLevel);

			logInfo(this, "<init>", "Start logger=[%s] Level=[%s] WithConsole=[%b] WithFile=[%b]",
					pLogger.getName(), pLogger.getLevel(), aWithConsole, aWithFile);
			if (aWithFile) {
				logInfo(this, "<init>", "FileHandlerPattern=[%s] ", wFhPattern);
			}
		}
	}

	/**
	 * Open a new logger by closing the current one if it is already open
	 *
	 * @param aName
	 *            name of logger
	 * @param aLevelName
	 *            name of level
	 * @param aWithConsole
	 *            true if the CONSOLE Handler is used
	 * @param aWithFile
	 *            true if the CONSOLE Handler is used
	 */
	public void open(String aName, String aLevelName, boolean aWithConsole, boolean aWithFile) {
		open(aName, levelNameToLevel(aLevelName), aWithConsole, aWithFile);
	}

	/**
	 * Change the folder in which logger files are created when a logger file handler is used
	 *
	 * @param aLogFolderPath
	 *            new path
	 * @throws FileNotFoundException
	 *             thrown if the folder doesn't exist
	 */
	public void setLogFolderPath(String aLogFolderPath) throws FileNotFoundException {
		File f = new File(aLogFolderPath);
		if (f.isDirectory()) {
			this.plogFolderPath = aLogFolderPath;
		} else {
			throw new FileNotFoundException(
					String.format("The folder '%s' doesn't exist", aLogFolderPath));
		}

	}

	@Override
	public void setLogLevel(Level aLevel) {
		pLogger.setLevel(aLevel);
	}
}
