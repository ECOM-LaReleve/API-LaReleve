package com.lr.routes;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.lr.utils.ISimpleLogger;
import com.lr.utils.SimpleLoggerRegistry;

public abstract class BasicRoute {

	protected static final ISimpleLogger LOGGER = SimpleLoggerRegistry.REGISTRY
			.lookup("DEFAULT_LARELEVE");

	public static ResponseBuilder responseBuilder(Status status) {
		return Response.status(status)
				.header("Access-Control-Allow-Origin", "http://localhost:9000")
				.header("Access-Control-Allow-Headers",
						"origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Credentials", "true")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				.header("Access-Control-Max-Age", "1209600");
	}

}
