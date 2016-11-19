package com.lr.auth;

import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import com.auth0.jwt.JWTVerifyException;
import com.lr.listeners.LaReleveContext;
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
		AuthToken authToken = null;
		try {
			// Validate the token
			authToken = validateToken(token);
			LOGGER.logDebug(this, "authentication", "Token is valid");
		} catch (JWTVerifyException e) {
			LOGGER.logDebug(this, "authentication", "Invalid token : ", e.getMessage());
			requestContext
					.abortWith(BasicRoute.responseBuilder(Response.Status.UNAUTHORIZED).build());
			return;
		}
		/* Set security context */
		LOGGER.logDebug(this, "authentication", "Setting security context");
		final AuthToken usedToken = authToken;
		requestContext.setSecurityContext(new SecurityContext() {
			@Override
			public String getAuthenticationScheme() {
				return null;
			}

			@Override
			public Principal getUserPrincipal() {
				return new LaRelevePrincipal() {
					@Override
					public String getName() {
						return usedToken.getUsername();
					}

					@Override
					public AuthToken getUsedToken() {
						return usedToken;
					}
				};
			}

			@Override
			public boolean isSecure() {
				return false;
			}

			@Override
			public boolean isUserInRole(String role) {
				return true;
			}
		});

	}

	private AuthToken validateToken(String jwToken) throws JWTVerifyException {
		// Check it's not expired
		LOGGER.logDebug(this, "validateToken", "Checking token %s", jwToken);

		AuthToken token = AuthToken.decode(jwToken); // Retrieve token
		Timestamp now = new Timestamp(System.currentTimeMillis());

		if (token.getExpirationDate().after(now)) {
			LOGGER.logDebug(this, "validateToken", "Token not expired yet");
			/* Check token is registered */
			List<AuthToken> usertokens = LaReleveContext.TOKENS.get(token.getUsername());
			if (usertokens != null) {
				for (AuthToken usertoken : usertokens) {
					LOGGER.logDebug(this, "validateToken", "Testing tokens : %s == %s", usertoken,
							token);
					if (usertoken.equals(token)) {
						return token;
					}
				}
			}
			throw new JWTVerifyException("Token not found in user list");
		} else {
			throw new JWTVerifyException("Expired token");
		}
	}
}
