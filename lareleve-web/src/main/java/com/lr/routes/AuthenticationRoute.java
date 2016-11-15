package com.lr.routes;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.lr.auth.Secured;
import com.lr.entity.Credentials;
import com.lr.remote.IAuthEJBRemote;

/**
 * Handler of routes /auth
 */
@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationRoute extends BasicRoute {

	@EJB
	private IAuthEJBRemote authEJB;

	@POST
	@Path("/login")
	public Response login(Credentials credentials) {
		LOGGER.logDebug(this, "login", "username=%s", credentials.getUsername());

		String username = credentials.getUsername();
		String password = credentials.getPassword();
		String hashedPassword = password;

		String token = authEJB.login(username, hashedPassword);
		if (token != null) {
			// if token is not null, login was sucessfull
			String out = String.format("{ \"token\": \"%s\" }", token);
			return responseBuilder(Status.OK).entity(out).build();
		}
		// Wrong username and/or password
		return responseBuilder(Status.UNAUTHORIZED).build();

	}

	@POST
	@Secured
	@Path("/logout")
	public Response logout() {
		LOGGER.logDebug(this, "logout", "");
		return responseBuilder(Status.OK).build();
	}

}
