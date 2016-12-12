package com.lr.listeners;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.lr.auth.AuthToken;
import com.lr.utils.SimpleLogger;
import com.lr.utils.SimpleLoggerRegistry;

/**
 * Context of LaReleve API
 *
 */
public class LaReleveContext implements ServletContextListener {

	/** Tokens Map */
	public static Map<String, List<AuthToken>> TOKENS = new HashMap<>();

	/** Logger */
	public static SimpleLogger LOGGER = null;

	public LaReleveContext() {
		// Create and register Logger
		if (LOGGER == null) {
			LOGGER = new SimpleLogger();
			String wLogDir = System.getProperty("jboss.server.log.dir");
			if (wLogDir != null) {
				try {
					LOGGER.setLogFolderPath(wLogDir);
				} catch (FileNotFoundException e) {
				}
			}
			LOGGER.open("web-lareleve", Level.ALL, false, true);

			SimpleLoggerRegistry.REGISTRY.bind("DEFAULT_LARELEVE", LOGGER);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// Destroy Logger
		SimpleLoggerRegistry.REGISTRY.unbind("DEFAULT_LARELEVE");
		LOGGER.close();

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
	}

}
