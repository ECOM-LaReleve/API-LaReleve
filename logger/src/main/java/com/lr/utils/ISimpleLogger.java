package com.lr.utils;

import java.util.logging.Level;

/**
 * @author gattazr
 *
 */
public interface ISimpleLogger {

	/**
	 * @return true if logging with the level DEBUG is possible
	 */
	public boolean isLogDebugOn();

	/**
	 * return true if logging with the level aLevel is possible
	 *
	 * @param aLevel
	 *            level to test
	 * @return true if logging with the level aLevel is possible
	 */
	public boolean isLoggable(Level aLevel);

	/**
	 * @return true if logging with the level INFO is possible
	 */
	public boolean isLogInfoOn();

	/**
	 * @return true if logging with the level SEVERE is possible
	 */
	public boolean isLogSevereOn();

	/**
	 * @return true if logging with the level WARNING is possible
	 */
	public boolean isLogWarningOn();

	/**
	 * Create a log entry of the given informations at the given level
	 *
	 * @param aLevel
	 *            Level of log entry
	 * @param aWho
	 *            Object from which the event is coming from
	 * @param aWhat
	 *            Simple name for event
	 * @param aInfos
	 *            Detailed informations about the event
	 */
	public void log(Level aLevel, Object aWho, CharSequence aWhat, Object... aInfos);

	/**
	 * Create a DEBUG entry in the log with the given informations
	 *
	 * @param aWho
	 *            Object from which the event is coming from
	 * @param aWhat
	 *            Simple name for event
	 * @param aInfos
	 *            Detailed informations about the event
	 */
	public void logDebug(Object aWho, CharSequence aWhat, Object... aInfos);

	/**
	 * Create a INFO entry in the log with the given informations
	 *
	 * @param aWho
	 *            Object from which the event is coming from
	 * @param aWhat
	 *            Simple name for event
	 * @param aInfos
	 *            Detailed informations about the event
	 */
	public void logInfo(Object aWho, CharSequence aWhat, Object... aInfos);

	/**
	 * Create a SEVERE entry in the log with the given informations
	 *
	 * @param aWho
	 *            Object from which the event is coming from
	 * @param aWhat
	 *            Simple name for event
	 * @param aInfos
	 *            Detailed informations about the event
	 */
	public void logSevere(Object aWho, CharSequence aWhat, Object... aInfos);

	/**
	 * Create a WARN entry in the log with the given informations
	 *
	 * @param aWho
	 *            Object from which the event is coming from
	 * @param aWhat
	 *            Simple name for event
	 * @param aInfos
	 *            Detailed informations about the event
	 */
	public void logWarn(Object aWho, CharSequence aWhat, Object... aInfos);

	/**
	 * Change Log level to given level
	 *
	 * @param aLevel
	 *            new log level
	 */
	public void setLogLevel(Level aLevel);
}
