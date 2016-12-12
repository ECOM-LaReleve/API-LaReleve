package com.lr.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Prestations")
@NamedQueries({
	@NamedQuery(name = "Prestation.findAll", query = "SELECT p FROM Prestation p"),
	@NamedQuery(name = "Prestation.findById", query = "SELECT p FROM Prestation p WHERE p.id = :id"),
	@NamedQuery(name = "Prestation.findByBesoinId", query = "SELECT p FROM Prestation p WHERE p.besoin.id = :id")
})
public class Prestation implements Serializable {

	private static final long serialVersionUID = 687176476972886825L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	private String libelle;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idBesoin")
	private Besoin besoin;

	//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "prestation")
	//	private Set<PrestationsRealisees> prestationsRealisees;

	//	public void addPrestationsRealisees(PrestationsRealisees prestationRealisee) {
	//		this.prestationsRealisees.add(prestationRealisee);
	//	}

	public Besoin getBesoin() {
		return besoin;
	}

	public int getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}

	//	public Set<PrestationsRealisees> getPrestationsRealisees() {
	//		return prestationsRealisees;
	//	}

	public void setBesoin(Besoin besoin) {
		this.besoin = besoin;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
