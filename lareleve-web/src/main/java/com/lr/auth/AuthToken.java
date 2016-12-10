package com.lr.auth;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.lr.entity.Role.RoleName;

/**
 * AuthToken class. Uses JWT to generate serializable tokens with info in.
 */
public class AuthToken implements Serializable {

	// private static String SECRET = System.getenv("JWT_SECRET");
	private static String SECRET = "TEMPORARY_SECRET";

	/**
	 *
	 */
	private static final long serialVersionUID = 777725148482357838L;

	/**
	 * Create a AuthToken from the given string. Thrown an exception if the string is not a valid
	 * AuthToken
	 *
	 * @param jwtoken
	 *            token in String format
	 * @return AuthToken decoded from string
	 * @throws JWTVerifyException
	 *             jwToken string isn't a valid AuthToken
	 */
	public static AuthToken decode(String jwtoken) throws JWTVerifyException {

		AuthToken token = new AuthToken();
		try {
			final JWTVerifier verifier = new JWTVerifier(SECRET);
			final Map<String, Object> claims = verifier.verify(jwtoken);
			/* Retrieve username */
			token.setUsername((String) claims.get("username"));
			/* Retrieve expiration date */
			token.setExpirationDate(new Timestamp((Long) claims.get("expirationDate")));
			/* Retrieve roles */
			LinkedList<RoleName> roles = new LinkedList<>();
			int nbRoles = (int) claims.get("roles");
			for (int i = 0; i < nbRoles; i++) {
				roles.add(RoleName.valueOf((String) claims.get("roles_" + i)));
			}
			token.setRoles(roles);
		} catch (JWTVerifyException e) {
			// Invalid Token
			throw e;
		} catch (Exception e) {
			return null;
		}
		return token;
	}

	/** User username */
	private String username;
	/** AuthToken expiration date */
	private Timestamp expirationDate;
	/** User roles */
	private List<RoleName> roles;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AuthToken) {
			AuthToken token = (AuthToken) obj;

			if (username.equals(token.username) && expirationDate.equals(token.expirationDate)
					&& roles.equals(token.roles)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Generate a String containing the informations of the AuthToken. This string can be decoded
	 * back into an AuthToken using the function decode
	 *
	 * @return generated String of the AuthToken
	 */
	public String generate() {

		/* Place data in MAP */
		final HashMap<String, Object> claims = new HashMap<String, Object>();
		claims.put("username", this.username);
		claims.put("expirationDate", this.expirationDate);

		if (this.roles != null) {
			claims.put("roles", this.roles.size());
			for (int i = 0; i < this.roles.size(); i++) {
				claims.put("roles_" + i, this.roles.get(i));
			}
		} else {
			claims.put("roles", 0);
		}

		/* Sign */
		final JWTSigner signer = new JWTSigner(SECRET);

		/* Make String token */
		final String jwt = signer.sign(claims);

		return jwt;
	}

	/**
	 *
	 * @return the AuthToken expiration date
	 */
	public Timestamp getExpirationDate() {
		return expirationDate;
	}

	/**
	 *
	 * @return the list of Roles of the user
	 */
	public List<RoleName> getRoles() {
		return this.roles;
	}

	/**
	 *
	 * @return the username of the user for whom that AuthToken was created
	 */
	public String getUsername() {
		return username;
	}

	/**
	 *
	 * @param expirationDate
	 *            the AuthToken expiration date
	 */
	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 *
	 * @param roles
	 *            the list of Roles of the user
	 */
	public void setRoles(List<RoleName> roles) {
		this.roles = roles;
	}

	/**
	 *
	 * @param username
	 *            the username of the user for whom that AuthToken was created
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		StringBuilder strRoles = new StringBuilder();
		strRoles.append('[');
		if (roles != null && roles.size() > 0) {
			strRoles.append(roles.get(0));
			for (int i = 1; i < roles.size(); i++) {
				strRoles.append(", " + roles.get(i));
			}
		}
		strRoles.append(']');

		return String.format("[%s: username=%s, expirationDate=%s, roles=%s]",
				this.getClass().getSimpleName(), username, expirationDate, strRoles);
	}

}
