package com.lr.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RessourcesIndividus implements Serializable {

	private static final long serialVersionUID = -3413677878757555755L;

	@Id
	private RessourcesIndividusID pk;

	private int montantRessource;

	public int getMontantRessource() {
		return montantRessource;
	}

	public RessourcesIndividusID getPk() {
		return pk;
	}

	public void setMontantRessource(int montantRessource) {
		this.montantRessource = montantRessource;
	}

	public void setPk(RessourcesIndividusID pk) {
		this.pk = pk;
	}

}
