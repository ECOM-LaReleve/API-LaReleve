package com.lr.routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Handler of routes /
 */
@Path("/")
public class RootRoute {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String find() {
		return "{}";
	}
}
