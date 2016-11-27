package com.lr.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Nationnalites")
public class Nationnalite implements Serializable{


	private static final long serialVersionUID = -4261585549879441362L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(nullable=false, unique=true)
	private String libelle;

	@ManyToMany()
	@JoinTable(name = "NationnalitesIndividus", joinColumns = @JoinColumn(name="idNationnalite",referencedColumnName="id"), inverseJoinColumns = @JoinColumn(name="idIndividu",referencedColumnName="id"))
	Set<Individu> individus;


	public void addIndividus(Individu individu) {
		this.individus.add(individu);
	}

	public String getId() {
		return id;
	}

	public Set<Individu> getIndividus(){
		return this.individus;
	}

	public String getLibelle() {
		return libelle;
	}

	private void setId(String id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}