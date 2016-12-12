package com.lr.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class NationnalitesIndividusID implements Serializable {

	private static final long serialVersionUID = -1442699615808206294L;

	@ManyToOne
	@JoinColumn(name = "idNationnalite")
	private Nationnalite nationnalite;

	@ManyToOne
	@JoinColumn(name = "idIndividu")
	private Individu individu;

	public Individu getIndividu() {
		return individu;
	}

	public Nationnalite getNationnalite() {
		return nationnalite;
	}

	public void setIndividu(Individu individu) {
		this.individu = individu;
	}

	public void setNationnalite(Nationnalite nationnalite) {
		this.nationnalite = nationnalite;
	}

}
