package com.lr.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "RessourcesMenages.findAll", query = "SELECT r FROM RessourcesMenages r"),
	@NamedQuery(name = "RessourcesMenages.findByIdMenage", query = "SELECT r FROM RessourcesMenages r WHERE r.pk.menage.id = :id"),
	@NamedQuery(name = "RessourcesMenages.findByIdRessource", query = "SELECT r FROM RessourcesMenages r WHERE r.pk.ressource.id = :id")
})
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
