package com.lr.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Prestations")
public class Prestation implements Serializable{


	private static final long serialVersionUID = 687176476972886825L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable=false, unique=true)
	private String libelle;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn (name="idBesoin")
	private Besoin besoin;

	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "idPrestation")
	private Set<PrestationsRealisees> prestationsRealisees;


	public void addPrestationsRealisees(PrestationsRealisees prestationRealisee) {
		this.prestationsRealisees.add(prestationRealisee);
	}

	public Besoin getBesoin() {
		return besoin;
	}

	public int getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}

	public Set<PrestationsRealisees> getPrestationsRealisees() {
		return prestationsRealisees;
	}

	public void setBesoin(Besoin besoin) {
		this.besoin = besoin;
	}

	private void setId(int id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
