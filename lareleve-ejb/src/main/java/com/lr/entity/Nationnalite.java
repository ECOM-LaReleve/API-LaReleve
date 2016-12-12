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
@Table(name = "Nationnalites")
@NamedQueries({
	@NamedQuery(name = "Nationnalite.findAll", query = "SELECT n FROM Nationnalite n"),
	@NamedQuery(name = "Nationnalite.findById", query = "SELECT n FROM Nationnalite n WHERE n.id = :id")
})
public class Nationnalite implements Serializable {

	private static final long serialVersionUID = -4261585549879441362L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	private String libelle;

	/*
	@ManyToMany()
	@JoinTable(name = "NationnalitesIndividus", joinColumns = @JoinColumn(name = "idNationnalite", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "idIndividu", referencedColumnName = "id"))
	Set<Individu> individus;
	 */
	/*
	public void addIndividus(Individu individu) {
		this.individus.add(individu);
	}
	 */
	public int getId() {
		return id;
	}
	/*
	public Set<Individu> getIndividus() {
		return this.individus;
	}
	 */
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