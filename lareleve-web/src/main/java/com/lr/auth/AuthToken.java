package com.lr.auth;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;

public class AuthToken implements Serializable {

	// private static String SECRET = System.getenv("JWT_SECRET");
	private static String SECRET = "TEMPORARY_SECRET";

	/**
	 *
	 */
	private static final long serialVersionUID = 777725148482357838L;

	public static AuthToken decode(String jwtoken) throws JWTVerifyException {
		AuthToken token = new AuthToken();
		try {
			final JWTVerifier verifier = new JWTVerifier(SECRET);
			final Map<String, Object> claims = verifier.verify(jwtoken);

			token.setUsername((String) claims.get("username"));
			token.setExpirationDate(new Timestamp((Long) claims.get("expirationDate")));
		} catch (JWTVerifyException e) {
			// Invalid Token
			throw e;
		} catch (Exception e) {
			return null;
		}
		return token;
	}

	private String username;
	private Timestamp expirationDate;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AuthToken) {
			AuthToken token = (AuthToken) obj;

			if (username.equals(token.username) && expirationDate.equals(token.expirationDate)) {
				return true;
			}
		}
		return false;
	}

	public String generate() {

		/* Place data in MAP */
		final HashMap<String, Object> claims = new HashMap<String, Object>();
		claims.put("username", this.username);
		claims.put("expirationDate", this.expirationDate);

		/* Sign */
		final JWTSigner signer = new JWTSigner(SECRET);

		/* Make String token */
		final String jwt = signer.sign(claims);

		return jwt;
	}

	public Timestamp getExpirationDate() {
		return expirationDate;
	}

	public String getUsername() {
		return username;
	}

	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return String.format("[%s: username=%s, expirationDate=%s]",
				this.getClass().getSimpleName(), username, expirationDate);
	}

}
