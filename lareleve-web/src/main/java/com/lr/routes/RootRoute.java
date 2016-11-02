package com.lr.routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Handler of routes /
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class RootRoute extends BasicRoute {

	@GET
	public String find() {
		return "{}";
	}
}
