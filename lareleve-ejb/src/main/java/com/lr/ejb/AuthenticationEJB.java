package com.lr.ejb;

import javax.ejb.Stateless;

import com.lr.entity.Utilisateur;
import com.lr.remote.IAuthenticationEJBRemote;

@Stateless
public class AuthenticationEJB extends BasicEJB implements IAuthenticationEJBRemote {

	@Override
	public boolean checkCredentials(String username, String hashedPassword) {
		String wStrQuery = String.format("SELECT id from %s WHERE username='%s' and password='%s'",
				Utilisateur.class.getSimpleName(), username, hashedPassword);
		try {
			em.createQuery(wStrQuery).getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
