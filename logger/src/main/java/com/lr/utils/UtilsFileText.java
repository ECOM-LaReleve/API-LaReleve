package com.lr.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/***
 *
 * @author gattazr
 *
 */
public class UtilsFileText {

	public final static String ENCODING_UTF8 = "UTF-8";

	/**
	 * Read all the content of a file and return it in a String
	 *
	 * @param aFile
	 *            file to read
	 * @param aEncoding
	 *            char encoding. eg. "UTF-8"
	 * @return content of file
	 * @throws IOException
	 *             thrown if an error occur during file manipulation
	 */
	public static String readAll(File aFile, String aEncoding) throws IOException {
		return new String(readAllBytes(aFile), aEncoding);
	}

	/**
	 * Read all the content of a file and return it as a byte array
	 *
	 * @param aFile
	 *            file to read
	 * @return content of file
	 * @throws IOException
	 *             thrown if an error occur during file manipulation
	 */
	public static byte[] readAllBytes(File aFile) throws IOException {
		FileInputStream wStream = new FileInputStream(aFile);
		int wSize = wStream.available();
		byte[] wData = new byte[wSize];
		wStream.read(wData);
		wStream.close();
		return wData;
	}

	/**
	 * Read all the content of a file and return it in a String. The encoding of the file is assumed
	 * to be UTF-8
	 *
	 * @param aFile
	 *            file to read
	 * @return content of file
	 * @throws IOException
	 *             thrown if an error occur during file manipulation
	 */
	public static String readAllUtf8(File aFile) throws IOException {
		return readAll(aFile, ENCODING_UTF8);
	}

	/**
	 * Write the content of a String in a file
	 *
	 * @param aFile
	 *            output file
	 * @param aString
	 *            string to write
	 * @param aEncoding
	 *            char encoding. eg. "UTF-8"
	 * @throws IOException
	 *             thrown if an error occur during file manipulation
	 */
	public static void writeAll(File aFile, String aString, String aEncoding) throws IOException {
		// you don't need to create a File object, FileWriter takes a string for
		// the filepath as well

		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(aFile), aEncoding));
		writer.write(aString);

		writer.close();
	}

	/**
	 * Write the content of a String in a file. The encoding of the file is assumed to be UTF-8.
	 *
	 * @param aFile
	 *            output file
	 * @param aString
	 *            string to write
	 * @throws IOException
	 *             thrown if an error occur during file manipulation
	 */
	public static void writeAllUtf8(File aFile, String aString) throws IOException {
		writeAll(aFile, aString, ENCODING_UTF8);
	}
}
