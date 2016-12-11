package com.lr.ejb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

	public String MD5Hashing(String password) {

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());

			byte byteData[] = md.digest();

			//convert the byte to hex format
			StringBuffer hexString = new StringBuffer();
			for (int i=0;i<byteData.length;i++) {
				String hex=Integer.toHexString(0xff & byteData[i]);
				if(hex.length()==1) hexString.append('0');
				hexString.append(hex);
			}

			LOGGER.logDebug(this, "MD5Hashing :", hexString.toString());
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}

}
