package com.lr.ejb;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Timestamp;

import javax.ejb.Stateless;

import com.lr.entity.AuthToken;
import com.lr.entity.Utilisateur;
import com.lr.remote.IAuthenticationEJBRemote;

@Stateless
public class AuthenticationEJB extends BasicEJB implements IAuthenticationEJBRemote {

	private boolean checkInformations(String username, String hashedPassword) {
		String wStrQuery = String.format(
				"SELECT username from %s WHERE username='%s' and password='%s'",
				Utilisateur.class.getSimpleName(), username, hashedPassword);
		try {
			em.createQuery(wStrQuery).getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public String login(String username, String hashedPassword) {

		if (checkInformations(username, hashedPassword)) {
			// credentials are correct
			// String token =
			/* Save the token */
			AuthToken token = new AuthToken();
			token.setUsername(username);
			token.setValue(new BigInteger(130, new SecureRandom()).toString(32));
			// set expiration timeStamp
			int sec = 600; // number of seconds of valid token
			Timestamp validStamp = new Timestamp(System.currentTimeMillis() + sec * 1000);
			token.setExpirationDate(validStamp);
			// em.persist(token);
			LOGGER.logDebug(this, "login", "Token : %s", token);
			return token.getValue();
		}
		return null;

	}

	@Override
	public void logout(String token) {
		// TODO
	}

}
