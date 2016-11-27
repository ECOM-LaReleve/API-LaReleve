package com.lr.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Langues")
public class Langue implements Serializable{


	private static final long serialVersionUID = -8407119004464828014L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable=false, unique=true)
	private String libelle;

	@OneToMany(mappedBy = "pk.langue")
	private Set<LanguesIndividus> languesIndividus;


	public void addLanguesIndividus(LanguesIndividus languesIndividus) {
		this.languesIndividus.add(languesIndividus);
	}

	public int getId() {
		return id;
	}

	public Set<LanguesIndividus> getLanguesIndividus() {
		return languesIndividus;
	}

	public String getLibelle() {
		return libelle;
	}

	private void setId(int id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}