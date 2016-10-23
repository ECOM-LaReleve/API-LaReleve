package com.lr.routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Handler of routes /users
 */
@Path("/users")
public class UsersRoute {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String find() {
		return "[{'id': 1}, {'id': 2}]";
	}

	@GET
	@Path("{id : \\d+}") // id must be digits
	@Produces(MediaType.APPLICATION_JSON)
	public String findById(@PathParam("id") String id) {
		return "{'id': 1}";
	}
}
