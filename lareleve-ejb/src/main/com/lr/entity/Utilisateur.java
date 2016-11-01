package com.lr.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Utilisateurs")
public class Utilisateur implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = 2254274640238186573L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String nom;
	private String prenom;

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getPassword() {
		return password;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getUsername() {
		return username;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return String.format("[Utilisateur : id=%d, username=%s, nom=%s, prenom=%s]", this.id,
				this.username, this.nom, this.prenom);
	}
}
