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
@Table(name = "Actes")
@NamedQueries({
	@NamedQuery(name = "Acte.findAll", query = "SELECT a FROM Acte a"),
	@NamedQuery(name = "Acte.findById", query = "SELECT a FROM Acte a WHERE a.id = :id")
})
public class Acte implements Serializable {

	private static final long serialVersionUID = -5051726920264580998L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	private String libelle;

	//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "acte")
	//	private Set<ActesRealises> actesRealises;

	//	public void addActesRealises(ActesRealises acteRealise) {
	//		this.actesRealises.add(acteRealise);
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

	public void setId(int id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}