package com.lr.routes;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lr.entity.User;
import com.lr.remote.IUserEJBRemote;

/**
 * Handler of routes /users
 */
@Path("/users")
public class UsersRoute {

	@EJB
	private IUserEJBRemote userEJB;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String find() {
		return userEJB.findAll().toString();
	}

	@GET
	@Path("{id : \\d+}") // id must be digits
	@Produces(MediaType.APPLICATION_JSON)
	public String findById(@PathParam("id") String id) {
		return userEJB.find(Integer.parseInt(id)).toString();
	}
}
