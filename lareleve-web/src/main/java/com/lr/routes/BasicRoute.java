package com.lr.routes;

import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import com.lr.listeners.LaReleveContext;
import com.lr.utils.ISimpleLogger;

public abstract class BasicRoute {

	protected static final ISimpleLogger LOGGER = LaReleveContext.LOGGER;

	public static final String ENCODING = ";charset=utf-8";

	public static ResponseBuilder responseBuilder(Status status) {
		return Response.status(status);
		// .header("Access-Control-Allow-Origin", "*")
		// .header("Access-Control-Allow-Headers",
		// "origin, content-type, accept, authorization")
		// .header("Access-Control-Allow-Credentials", "true")
		// .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
		// .header("Access-Control-Max-Age", "1209600");
	}

	@Context
	protected SecurityContext securityContext;

	@OPTIONS
	@Path("{path : .*}")
	public Response options() {
		return Response.status(Status.OK).build();
	}

}
