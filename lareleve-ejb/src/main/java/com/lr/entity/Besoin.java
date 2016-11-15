package com.lr.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Besoins")
public class Besoin implements Serializable{


	private static final long serialVersionUID = 4841875255683148757L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable=false, unique=true)
	private String libelle;

	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="idBesoin")
	private Collection<Prestation> prestations;


	public void addPrestations(Prestation prestation) {
		prestation.setBesoin(this);
		this.prestations.add(prestations);
	}

	public int getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}

	public Collection<Prestation> getPrestations() {
		return prestations;
	}

	private void setId(int id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
