package com.lr.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * @author gattazr
 *
 */
public class SimpleLoggerFormatter extends Formatter {

	private final static String sPatternTimeStamp = "yyyy-MM-dd_HH-mm-ss_SSS";
	private final static SimpleLoggerFormatter sFormatter = new SimpleLoggerFormatter();
	private final static SimpleDateFormat sTimeStampFormater = new SimpleDateFormat(
			sPatternTimeStamp);

	/**
	 *
	 * @return simple logger formatter
	 */
	public static Formatter getFormater() {
		return sFormatter;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.logging.Formatter#format(java.util.logging.LogRecord)
	 */
	@Override
	public String format(LogRecord aLogRecord) {
		String wTimeStamp = sTimeStampFormater.format(new Date(aLogRecord.getMillis()));

		return String.format("%s|%8s|%s\n", wTimeStamp, aLogRecord.getLevel(),
				aLogRecord.getMessage());
	}
}
