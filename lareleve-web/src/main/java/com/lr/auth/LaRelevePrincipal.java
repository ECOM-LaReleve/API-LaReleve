package com.lr.auth;

import java.security.Principal;

/**
 * Principal class used by LaReleve API
 */
public interface LaRelevePrincipal extends Principal {

	/**
	 *
	 * @return the AuthToken used by the current user to run the current request
	 */
	public AuthToken getUsedToken();

}
