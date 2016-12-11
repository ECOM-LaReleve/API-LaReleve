package com.lr.auth;

import java.io.Serializable;
import java.util.List;

import com.lr.entity.Role.RoleName;

public class AuthResponse implements Serializable {

	private static final long serialVersionUID = 7588673971503033603L;

	private String token;
	private List<RoleName> roles;

	public List<RoleName> getRoles(){
		return roles;
	}

	public String getToken(){
		return token;
	}

	public void setRoles(List<RoleName> roleNames){
		this.roles = roleNames;
	}

	public void setToken(String token){
		this.token = token;
	}

}
