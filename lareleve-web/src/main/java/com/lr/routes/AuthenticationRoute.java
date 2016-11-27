package com.lr.routes;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import com.lr.auth.AuthToken;
import com.lr.auth.LaRelevePrincipal;
import com.lr.auth.Secured;
import com.lr.entity.Credentials;
import com.lr.entity.Role.RoleName;
import com.lr.listeners.LaReleveContext;
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
		LOGGER.logDebug(this, "AuthenticationRoute.login", "username=%s",
				credentials.getUsername());

		String username = credentials.getUsername();
		String password = credentials.getPassword();
		// TODO Hash password
		String hashedPassword = password;

		if (authEJB.checkCredentials(username, hashedPassword)) {

			// Credentials are good
			/* Retrieve roles */
			List<RoleName> roleNames = authEJB.getRoles(username);

			/* Generation of token */
			AuthToken token = new AuthToken();
			int sec = 600; // number of seconds of valid token
			Timestamp expirationDate = new Timestamp(System.currentTimeMillis() + sec * 1000);
			token.setUsername(username);
			token.setExpirationDate(expirationDate);
			token.setRoles(roleNames);

			LOGGER.logInfo(this, "login", "Successful login : %s", token);
			/* Save the token */
			List<AuthToken> usertokens = LaReleveContext.TOKENS.get(username);
			if (usertokens == null) {
				usertokens = new LinkedList<>();
			}
			usertokens.add(token);
			LaReleveContext.TOKENS.put(username, usertokens);

			String out = String.format("{ \"token\": \"%s\" }", token.generate());
			return responseBuilder(Status.OK).entity(out).build();
		}
		// Wrong username and/or password
		return responseBuilder(Status.UNAUTHORIZED).build();
	}

	@POST
	@Secured
	@Path("/logout")
	public Response logout(@Context SecurityContext securityContext) {
		LOGGER.logDebug(this, "logout", "");
		LaRelevePrincipal principal = null;
		try {
			principal = (LaRelevePrincipal) securityContext.getUserPrincipal();
		} catch (ClassCastException e) {
			LOGGER.logSevere(this, "logout", "User informations could not be retrieved");
			return responseBuilder(Status.INTERNAL_SERVER_ERROR).build();
		}

		String username = principal.getName();
		List<AuthToken> usertokens = LaReleveContext.TOKENS.get(username);
		if (usertokens != null) {
			if (usertokens.remove(principal.getUsedToken())) {
				return responseBuilder(Status.OK).build();
			}
		}
		// Request was good but token was not found (normally impossible since the given token was
		// used to execute this code
		LOGGER.logSevere(this, "logout",
				"The token used to run this secured route could not be removed");
		return responseBuilder(Status.INTERNAL_SERVER_ERROR).build();

	}

}
