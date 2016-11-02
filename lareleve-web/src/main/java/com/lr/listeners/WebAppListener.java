package com.lr.listeners;

import java.io.FileNotFoundException;
import java.util.logging.Level;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.lr.utils.SimpleLogger;
import com.lr.utils.SimpleLoggerRegistry;

public class WebAppListener implements ServletContextListener {

	private SimpleLogger pLogger = null;

	public WebAppListener() {
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
