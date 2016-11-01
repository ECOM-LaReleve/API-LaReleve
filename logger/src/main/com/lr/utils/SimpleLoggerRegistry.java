package com.lr.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Map Registry of SimpleLogger
 *
 * @author gattazr
 *
 */
public class SimpleLoggerRegistry {

	private static Map<String, ISimpleLogger> sRegistry = new HashMap<>();

	public static void bind(String aName, ISimpleLogger aLogger) {
		sRegistry.put(aName, aLogger);
	}

	public static ISimpleLogger lookup(String aName) {
		return sRegistry.get(aName);
	}

	public static void unbind(String aName) {
		sRegistry.remove(aName);
	}

}
