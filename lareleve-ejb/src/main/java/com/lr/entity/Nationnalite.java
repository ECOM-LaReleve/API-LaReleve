package com.lr.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Nationnalites")
public class Nationnalite implements Serializable{


	private static final long serialVersionUID = -4261585549879441362L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(nullable=false, unique=true)
	private String libelle;


	public String getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}

	private void setId(String id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}