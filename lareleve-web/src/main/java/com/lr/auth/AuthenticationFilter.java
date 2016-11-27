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

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	/** Logger */
	protected static final ISimpleLogger LOGGER = LaReleveContext.LOGGER;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		LOGGER.logDebug(this, "AuthenticationFilter.filter",
				"Checking authentication and setting security context");

		// Get the HTTP Authorization header from the request
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		// Check if the HTTP Authorization header is present and formatted correctly
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			LOGGER.logSevere(this, "AuthenticationFilter.filter",
					"Authorization header not provided");
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
		} catch (JWTVerifyException e) {
			LOGGER.logSevere(this, "AuthenticationFilter.filter", "Invalid token : ",
					e.getMessage());
			requestContext
					.abortWith(BasicRoute.responseBuilder(Response.Status.UNAUTHORIZED).build());
			return;
		}
		/* Set security context */
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

	/**
	 * Decode the token jwToken in AuthToken and test whether it is valid of not. Return the token
	 * if it valid. Throw an exception otherwise
	 *
	 * @param jwToken
	 *            token in String format
	 * @return valid AuthToken decoded from string
	 * @throws JWTVerifyException
	 *             jwToken string isn't a valid AuthToken
	 */
	private AuthToken validateToken(String jwToken) throws JWTVerifyException {
		// Check it's not expired
		AuthToken token = AuthToken.decode(jwToken); // Retrieve token
		Timestamp now = new Timestamp(System.currentTimeMillis());

		if (token.getExpirationDate().after(now)) {
			/* Check if token is in the list of token the server generated for the user */
			List<AuthToken> usertokens = LaReleveContext.TOKENS.get(token.getUsername());
			if (usertokens != null) {
				for (AuthToken usertoken : usertokens) {
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
