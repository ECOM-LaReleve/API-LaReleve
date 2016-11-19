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

public class LaReleveContext implements ServletContextListener {

	/** Tokens Map */
	public static Map<String, List<AuthToken>> TOKENS = new HashMap<>();

	private SimpleLogger pLogger = null;

	public LaReleveContext() {
		if (pLogger == null) {
			pLogger = new SimpleLogger();
			String wLogDir = System.getProperty("jboss.server.log.dir");
			if (wLogDir != null) {
				try {
					pLogger.setLogFolderPath(wLogDir);
				} catch (FileNotFoundException e) {
				}
			}
			pLogger.open("web-lareleve", Level.ALL, false, true);

			SimpleLoggerRegistry.REGISTRY.bind("DEFAULT_LARELEVE", pLogger);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		SimpleLoggerRegistry.REGISTRY.unbind("DEFAULT_LARELEVE");
		pLogger.close();
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
	}

}
