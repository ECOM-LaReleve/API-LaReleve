package com.lr.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "RessourcesIndividus.findAll", query = "SELECT r FROM RessourcesIndividus r"),
	@NamedQuery(name = "RessourcesIndividus.findByIdIndividu", query = "SELECT r FROM RessourcesIndividus r WHERE r.pk.individu.id = :id"),
	@NamedQuery(name = "RessourcesIndividus.findByIdRessource", query = "SELECT r FROM RessourcesIndividus r WHERE r.pk.ressource.id = :id")
})
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
