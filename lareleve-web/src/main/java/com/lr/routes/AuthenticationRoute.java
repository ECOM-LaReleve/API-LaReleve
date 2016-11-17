package com.lr.routes;

import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.lr.auth.AuthToken;
import com.lr.auth.Secured;
import com.lr.entity.Credentials;
import com.lr.remote.IAuthenticationEJBRemote;

/**
 * Handler of routes /auth
 */
@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationRoute extends BasicRoute {

	@EJB
	private IAuthenticationEJBRemote authEJB;

	@POST
	@Path("/login")
	public Response login(Credentials credentials) {
		LOGGER.logDebug(this, "login", "username=%s", credentials.getUsername());

		String username = credentials.getUsername();
		String password = credentials.getPassword();
		// TODO Hash password
		String hashedPassword = password;

		if (authEJB.checkCredentials(username, hashedPassword)) {
			// Credentials are good
			/* Generation of token */
			AuthToken token = new AuthToken();
			int sec = 600; // number of seconds of valid token
			Timestamp expirationDate = new Timestamp(System.currentTimeMillis() + sec * 1000);
			token.setUsername(username);
			token.setExpirationDate(expirationDate);

			LOGGER.logDebug(this, "login", "Token : %s", token);

			String out = String.format("{ \"token\": \"%s\" }", token.generate());
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
