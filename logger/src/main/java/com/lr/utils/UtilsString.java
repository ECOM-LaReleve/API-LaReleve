package com.lr.utils;

import java.util.Arrays;

/**
 * @author gattazr
 *
 */
public class UtilsString {

	public final static String EMPTY = "";

	public final static char SEP_LINE = '\n';

	/**
	 * Create a string containing aValue and adjusted to the left. The string is adjusted using
	 * aAdjustChar. If the String aValue is longer than aLen, the result will be the first aLen
	 * characters of that String
	 *
	 * @param aValue
	 *            string to adjust
	 * @param aLen
	 *            length of result string
	 * @param aAdjustChar
	 *            character used for adjustment
	 * @return adjusted string
	 */
	public static String strAdjustLeft(String aValue, final int aLen, final char aAdjustChar) {

		if (aValue == null) {
			aValue = EMPTY;
		}

		int wLen = aValue.length();
		if (wLen < aLen) {
			return aValue + strFromChar(aAdjustChar, aLen - wLen);
		} else if (wLen > aLen) {
			return aValue.substring(0, aLen);
		} else {
			return aValue;
		}
	}

	/**
	 * Create a string containing aValue and adjusted to the right. The string is adjusted using 0.
	 *
	 * @param aValue
	 *            value to adjust
	 * @param aLen
	 *            length of result string
	 * @return adjusted string
	 */
	public static String strAdjustRight(long aValue, int aLen) {
		return strAdjustRight(String.valueOf(aValue), aLen, '0');
	}

	/**
	 * Create a string containing aValue and adjusted to the right. Since aValue is a number, the
	 * string is adjusted using spaces.
	 *
	 * @param aValue
	 *            String to adjust
	 * @param aLen
	 *            length of result string
	 * @return adjusted string
	 */
	public static String strAdjustRight(String aValue, int aLen) {
		return strAdjustRight(aValue, aLen, ' ');
	}

	/**
	 * Create a string containing aValue and adjusted to the right. The string is adjusted using
	 * aLeadingChar. If the String aValue is longer than aLen, the result will be the last aLen
	 * characters of that String
	 *
	 * @param aValue
	 *            string to adjust
	 * @param aLen
	 *            length of result string
	 * @param aAdjustChar
	 *            character used for adjustment
	 * @return adjusted string
	 */
	public static String strAdjustRight(String aValue, int aLen, char aAdjustChar) {

		if (aValue == null) {
			aValue = EMPTY;
		}
		int wLen = aValue.length();
		if (wLen < aLen)
			return strFromChar(aAdjustChar, aLen - wLen) + aValue;
		else if (wLen > aLen)
			return aValue.substring(aValue.length() - aLen);
		else
			return aValue;
	}

	/**
	 * Create a string of length aLen repeating the character aChar. If aLen is inferior to 1, empty
	 * string is returned.
	 *
	 * @param aChar
	 *            character to repeat
	 * @param aLen
	 *            length of new string
	 * @return created string
	 */
	public static String strFromChar(char aChar, int aLen) {
		if (aLen < 1) {
			return "";
		}

		char[] wBuffer = new char[aLen];
		Arrays.fill(wBuffer, aChar);
		return new String(wBuffer);
	}
}
