package com.lr.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class RessourcesMenagesID implements Serializable{

	private static final long serialVersionUID = -3456767678757562755L;


	@ManyToOne
	@Column(name="idMenage")
	private Menage menage;

	@ManyToOne
	@Column(name="idRessource")
	private Ressource ressource;

	public Menage getMenage() {
		return menage;
	}

	public Ressource getRessource() {
		return ressource;
	}

	public void setMenage(Menage menage) {
		this.menage = menage;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}




}
