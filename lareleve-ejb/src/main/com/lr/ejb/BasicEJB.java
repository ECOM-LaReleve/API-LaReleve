package com.lr.ejb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lr.utils.ISimpleLogger;
import com.lr.utils.SimpleLoggerRegistry;

public abstract class BasicEJB {
	static final ISimpleLogger LOGGER = SimpleLoggerRegistry.REGISTRY.lookup("DEFAULT_LARELEVE");

	@PersistenceContext(unitName = "LaRelevePU")
	protected EntityManager em;
}
