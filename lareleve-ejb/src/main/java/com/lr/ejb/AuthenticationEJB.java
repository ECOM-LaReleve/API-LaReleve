package com.lr.ejb;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;

import com.lr.entity.Role;
import com.lr.entity.Role.RoleName;
import com.lr.entity.Utilisateur;
import com.lr.remote.IAuthenticationEJBRemote;

@Stateless
public class AuthenticationEJB extends BasicEJB implements IAuthenticationEJBRemote {

	@Override
	public boolean checkCredentials(String username, String hashedPassword) {
		LOGGER.logDebug(this, "AuthenticationEJB.checkCredentials", "Test credentials of user : %s",
				username);
		String wStrQuery = String.format("SELECT id from %s WHERE username='%s' and password='%s'",
				Utilisateur.class.getSimpleName(), username, hashedPassword);
		try {
			em.createQuery(wStrQuery).getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public List<RoleName> getRoles(String username) {
		LOGGER.logDebug(this, "AuthenticationEJB.getRoles", "Retrieve roles for user : %s",
				username);
		String wStrQuery = String.format(
				"SELECT r FROM %s r JOIN r.utilisateurs u WHERE u.username = '%s'",
				Role.class.getSimpleName(), username);
		try {
			List<Object> roles = em.createQuery(wStrQuery).getResultList();
			LinkedList<RoleName> strRoles = new LinkedList<>();

			RoleName[] roleNameValues = RoleName.values();
			for (Object role : roles) {
				Role realRole = (Role) role;

				for (RoleName roleName : roleNameValues) {
					if (realRole.getLibelle().equals(roleName.name())) {
						strRoles.add(roleName);
					}
				}
			}
			return strRoles;
		} catch (Exception e) {
			return null;
		}
	}

}
