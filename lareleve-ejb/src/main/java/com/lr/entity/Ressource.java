package com.lr.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Ressources")
public class Ressource implements Serializable {

	public enum Type {
		INDIVIDU("individu"), MENAGE("menage");

		private String name = "";

		Type(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	private static final long serialVersionUID = -7185975847291097712L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(nullable = false, unique = true)
	private String libelle;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Type type;

	@OneToMany(mappedBy = "pk.ressource")
	private Set<RessourcesMenages> ressourcesMenages;

	@OneToMany(mappedBy = "pk.ressource")
	private Set<RessourcesIndividus> ressourcesIndividus;

	public void addRessourcesIndividus(RessourcesIndividus ressourcesIndividus) {
		this.ressourcesIndividus.add(ressourcesIndividus);
	}

	public void addRessourcesMenages(RessourcesMenages ressourcesMenages) {
		this.ressourcesMenages.add(ressourcesMenages);
	}

	public String getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}

	public Set<RessourcesIndividus> getRessourcesIndividus() {
		return ressourcesIndividus;
	}

	public Set<RessourcesMenages> getRessourcesMenages() {
		return ressourcesMenages;
	}

	public Type getType() {
		return type;
	}

	private void setId(String id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public void setType(Type type) {
		this.type = type;
	}

}