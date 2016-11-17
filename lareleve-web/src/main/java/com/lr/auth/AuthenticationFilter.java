package com.lr.auth;

import java.io.IOException;
import java.sql.Timestamp;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.auth0.jwt.JWTVerifyException;
import com.lr.routes.BasicRoute;
import com.lr.utils.ISimpleLogger;
import com.lr.utils.SimpleLoggerRegistry;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	protected static final ISimpleLogger LOGGER = SimpleLoggerRegistry.REGISTRY
			.lookup("DEFAULT_LARELEVE");

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		LOGGER.logDebug(this, "filter", "Checking authentication");

		// Get the HTTP Authorization header from the request
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		// Check if the HTTP Authorization header is present and formatted correctly
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			// throw new NotAuthorizedException("Authorization header must be provided");
			LOGGER.logDebug(this, "authentication", "Authorization header not provided");
			requestContext
					.abortWith(BasicRoute.responseBuilder(Response.Status.UNAUTHORIZED).build());
			return;
		}

		// Extract the token from the HTTP Authorization header
		String token = authorizationHeader.substring("Bearer".length()).trim();

		try {
			// Validate the token
			validateToken(token);
		} catch (JWTVerifyException e) {
			LOGGER.logInfo(this, "authentication", "Invalid token");
			requestContext
					.abortWith(BasicRoute.responseBuilder(Response.Status.UNAUTHORIZED).build());
		}
	}

	private boolean validateToken(String jwToken) throws JWTVerifyException {
		// Check it's not expired
		LOGGER.logDebug(this, "validateToken", "Checking token %s", jwToken);

		AuthToken token = AuthToken.decode(jwToken); // Retrieve token
		Timestamp now = new Timestamp(System.currentTimeMillis());

		return token.getExpirationDate().before(now);
	}
}