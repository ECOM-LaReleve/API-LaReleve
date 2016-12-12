package com.lr.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "NationnalitesIndividus.findAll", query = "SELECT a FROM NationnalitesIndividus a"),
	@NamedQuery(name = "NationnalitesIndividus.findByIdIndividu", query = "SELECT a FROM NationnalitesIndividus a WHERE a.pk.individu.id = :id"),
	@NamedQuery(name = "NationnalitesIndividus.findByIdNationnalite", query = "SELECT a FROM NationnalitesIndividus a WHERE a.pk.nationnalite.id = :id")
})
public class NationnalitesIndividus implements Serializable {

	private static final long serialVersionUID = -5205950905984456395L;

	@Id
	private NationnalitesIndividusID pk;

	public NationnalitesIndividusID getPk() {
		return pk;
	}

	public void setPk(NationnalitesIndividusID pk) {
		this.pk = pk;
	}

}
