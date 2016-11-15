package com.lr.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RessourcesMenages implements Serializable {


	private static final long serialVersionUID = -3413243678757555755L;


	@Id
	private RessourcesMenagesID pk;

	private int montantRessource;


	public int getMontantRessource() {
		return montantRessource;
	}

	public RessourcesMenagesID getPk() {
		return pk;
	}

	public void setMontantRessource(int montantRessource) {
		this.montantRessource = montantRessource;
	}

	public void setPk(RessourcesMenagesID pk) {
		this.pk = pk;
	}


}
