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

	/** An available SimpleLoggerRegistry */
	public static SimpleLoggerRegistry REGISTRY = new SimpleLoggerRegistry();

	/** Map linking names to ISimpleLogger */
	private Map<String, ISimpleLogger> pRegistry = new HashMap<>();

	/**
	 * Add a Logger to the registry. If a logger with the given name already exists, throws a
	 * RunTimeException
	 *
	 * @param aName
	 *            name of ISimpleLogger
	 * @param aLogger
	 *            ISimpleLogger to add in the register
	 */
	public void bind(String aName, ISimpleLogger aLogger) {
		if (pRegistry.containsKey(aName)) {
			throw new RuntimeException("A ISimpleLogger with that name is already binded.");
		}
		pRegistry.put(aName, aLogger);
	}

	/**
	 * Return the ISimpleLogger with the name aName in the registry. null if not found
	 *
	 * @param aName
	 *            name of ISimpleLogger
	 * @return the ISimpleLogger found. null if not found
	 */
	public ISimpleLogger lookup(String aName) {
		return pRegistry.get(aName);
	}

	/**
	 * Remove a Logger to the registry. If no logger with the given name is in the registry, throws
	 * a RunTimeException
	 *
	 * @param aName
	 *            name of ISimpleLogger
	 */
	public void unbind(String aName) {
		if (!pRegistry.containsKey(aName)) {
			throw new RuntimeException("No ISimpleLogger with that name in the registry");
		}
		pRegistry.remove(aName);
	}

}
