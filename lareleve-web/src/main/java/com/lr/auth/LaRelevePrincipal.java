package com.lr.auth;

import java.security.Principal;

public interface LaRelevePrincipal extends Principal {

	public AuthToken getUsedToken();

}
