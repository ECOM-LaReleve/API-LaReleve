package com.lr.auth;

import java.io.Serializable;
import java.util.List;

import com.lr.entity.Role.RoleName;
import com.lr.entity.Utilisateur;

public class AuthResponse implements Serializable {

	private static final long serialVersionUID = 7588673971503033603L;

	private String token;
	private List<RoleName> roles;
	private Utilisateur user;

	public List<RoleName> getRoles(){
		return this.roles;
	}

	public String getToken(){
		return this.token;
	}

	public Utilisateur getUtilisateur(){
		return this.user;
	}

	public void setRoles(List<RoleName> roleNames){
		this.roles = roleNames;
	}

	public void setToken(String token){
		this.token = token;
	}

	public void setUtilisateur(Utilisateur username){
		this.user = username;
	}

}
