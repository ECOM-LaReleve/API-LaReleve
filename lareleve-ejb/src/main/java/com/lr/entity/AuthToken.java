package com.lr.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AuthTokens")
public class AuthToken implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 777725148482357838L;
	@Id
	private int id;
	private String username;
	private String value;
	private Timestamp expirationDate;

	public Timestamp getExpirationDate() {
		return expirationDate;
	}

	public String getUsername() {
		return username;
	}

	public String getValue() {
		return value;
	}

	public void setExpirationDate(Timestamp expirationDate) {
		this.expirationDate = expirationDate;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.format("[%s: username=%s, value=%s, expirationDate=%s]",
				this.getClass().getSimpleName(), username, value, expirationDate);
	}

}
