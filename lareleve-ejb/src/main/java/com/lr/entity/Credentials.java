package com.lr.entity;

import java.io.Serializable;

public class Credentials implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2454539090366041691L;

	private String username;
	private String password;

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
