package com.lr.utils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author gattazr
 *
 */
public class UtilsThrowable {

	private final static String PATTERN_TIMESTAMP = "yyyy-MM-dd_HH-mm-ss_SSS";

	private final static SimpleDateFormat sTimeStampFormater = new SimpleDateFormat(
			PATTERN_TIMESTAMP);

	private final static String THROWABLE_MESSAGE = "(%s | %s)";

	private final static String THROWABLE_TITLE = "\n---------------- %d -- %s ----------------------";

	/**
	 * Build the name of a dump error file. The name is created with the following pattern :
	 * PREFIX_Error_DATE.txt eg. ExcelFileBuilder_Error_2013-05-31_17-40-23_256.txt
	 *
	 * @param aFileNamePrefix
	 * @return created name
	 */
	private static String buildErrorDumpFileName(String aFileNamePrefix) {
		return String.format("%s_Error_%s.log", aFileNamePrefix,
				sTimeStampFormater.format(new Date()));
	}

	/**
	 * Create a String containing informations on the error. The String contains the name and the
	 * full stacktrace leading to the error
	 *
	 * @param aThrowable
	 *            Throwable containing the error
	 * @return string containing informations about the error
	 */
	public static String dumpError(Throwable aThrowable) {
		StringBuilder wSB = new StringBuilder();
		int wThrowableLevel = 0;
		while (aThrowable != null) {

			wSB.append(String.format(THROWABLE_TITLE, wThrowableLevel,
					aThrowable.getClass().getName()));
			StringWriter wStack = new StringWriter();
			aThrowable.printStackTrace(new PrintWriter(wStack));
			wSB.append(UtilsString.SEP_LINE).append(wStack.toString());
			aThrowable = aThrowable.getCause();
			wThrowableLevel++;
		}

		return wSB.toString();
	}

	/**
	 * Create a String containing informations of the error. The String contains the name and the
	 * message of the error
	 *
	 * @param aThrowable
	 *            Throwable containing the error
	 * @return string containing short informations about the error
	 */
	public static String dumpErrorMessages(Throwable aThrowable) {
		StringBuilder wSB = new StringBuilder();
		while (aThrowable != null) {

			wSB.append(String.format(THROWABLE_MESSAGE, aThrowable.getClass().getSimpleName(),
					aThrowable.getLocalizedMessage()));

			aThrowable = aThrowable.getCause();
		}

		return wSB.toString();
	}

	/**
	 * Create a file containing the dump of the throwable
	 *
	 * @param aFileNamePrefix
	 *            prefix of the name of the dump file
	 * @param aThrowable
	 *            throwable of error
	 * @return absolute path of the created error file
	 */
	public static String storeErrorDumpInTmp(String aFileNamePrefix, Throwable aThrowable) {

		File wTempDirFile = new File(System.getProperty("java.io.tmpdir"));

		File wErrorDumpFile = new File(wTempDirFile, buildErrorDumpFileName(aFileNamePrefix));

		String wDump = dumpError(aThrowable);
		try {
			UtilsFileText.writeAllUtf8(wErrorDumpFile, wDump);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(wDump);
		}
		return wErrorDumpFile.getAbsolutePath();

	}
}
