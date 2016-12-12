package com.lr.auth;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import com.lr.entity.Role.RoleName;
import com.lr.listeners.LaReleveContext;
import com.lr.routes.BasicRoute;
import com.lr.utils.ISimpleLogger;

@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

	/** Logger */
	protected static final ISimpleLogger LOGGER = LaReleveContext.LOGGER;

	@Context
	private ResourceInfo resourceInfo;

	private boolean checkPermissions(List<RoleName> allowedRoles, List<RoleName> userRoles) {
		if (allowedRoles == null || allowedRoles.isEmpty()) {
			return true;
		}

		for (RoleName allowedRole : allowedRoles) {
			if (userRoles.contains(allowedRole)) {
				return true;
			}
		}
		return false;
	}

	// Extract the roles from the annotated element
	private List<RoleName> extractRoles(AnnotatedElement annotatedElement) {
		if (annotatedElement == null) {
			return new ArrayList<RoleName>();
		} else {
			Secured secured = annotatedElement.getAnnotation(Secured.class);
			if (secured == null) {
				return new ArrayList<RoleName>();
			} else {
				RoleName[] allowedRoles = secured.value();
				return Arrays.asList(allowedRoles);
			}
		}
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		LOGGER.logDebug(this, "AuthorizationFilter.filter", "Checking authorizations");
		// Get the resource class which matches with the requested URL
		// Extract the roles declared by it
		Class<?> resourceClass = resourceInfo.getResourceClass();
		List<RoleName> classRoles = extractRoles(resourceClass);

		// Get the resource method which matches with the requested URL
		// Extract the roles declared by it
		Method resourceMethod = resourceInfo.getResourceMethod();
		List<RoleName> methodRoles = extractRoles(resourceMethod);

		SecurityContext securityContext = requestContext.getSecurityContext();

		LaRelevePrincipal principal = (LaRelevePrincipal) securityContext.getUserPrincipal();
		AuthToken authToken = principal.getUsedToken();

		// Check if the user is allowed to execute the method
		// The method annotations override the class annotations
		if (methodRoles.isEmpty() && !checkPermissions(classRoles, authToken.getRoles())
				|| !checkPermissions(methodRoles, authToken.getRoles())) {
			requestContext.abortWith(BasicRoute.responseBuilder(Response.Status.FORBIDDEN).build());
		}
	}
}