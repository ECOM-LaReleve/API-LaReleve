package com.lr.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Besoins")
@NamedQueries({
	@NamedQuery(name = "Besoin.findAll", query = "SELECT b FROM Besoin b"),
	@NamedQuery(name = "Besoin.findById", query = "SELECT b FROM Besoin b WHERE b.id = :id"),
	@NamedQuery(name = "Besoin.findByLibelle", query = "SELECT b FROM Besoin b WHERE b.libelle = :libelle")
})
public class Besoin implements Serializable {

	private static final long serialVersionUID = 4841875255683148757L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	private String libelle;

	//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "besoin")
	//	private Collection<Prestation> prestations;

	//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "besoin")
	//	private Set<ActesRealises> actesRealises;

	//	public void addActesRealises(ActesRealises acteRealise) {
	//		this.actesRealises.add(acteRealise);
	//	}

	//	public void addPrestations(Prestation prestation) {
	//		prestation.setBesoin(this);
	//		this.prestations.add(prestation);
	//	}

	//	public Set<ActesRealises> getActesRealises() {
	//		return actesRealises;
	//	}

	public int getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}

	//	public Collection<Prestation> getPrestations() {
	//		return prestations;
	//	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
