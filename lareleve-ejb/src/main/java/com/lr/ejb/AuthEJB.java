package com.lr.ejb;

import javax.ejb.Stateless;

import com.lr.entity.Utilisateur;
import com.lr.remote.IAuthEJBRemote;

@Stateless
public class AuthEJB extends BasicEJB implements IAuthEJBRemote {

	@Override
	public boolean connect(String username, String hashedPassword) {

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

}
