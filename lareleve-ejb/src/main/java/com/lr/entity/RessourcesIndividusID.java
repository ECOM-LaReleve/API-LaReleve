package com.lr.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class RessourcesIndividusID implements Serializable {

	private static final long serialVersionUID = -3456767678677789755L;

	@ManyToOne
	@JoinColumn(name = "idIndividu")
	private Individu individu;

	@ManyToOne
	@JoinColumn(name = "idRessource")
	private Ressource ressource;

	public Individu getIndividu() {
		return individu;
	}

	public Ressource getRessource() {
		return ressource;
	}

	public void setIndividu(Individu individu) {
		this.individu = individu;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}

}
