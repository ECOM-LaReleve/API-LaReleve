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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Services")
public class Service implements Serializable {

	private static final long serialVersionUID = -3847457095945195715L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	private String libelle;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPole")
	private Pole pole;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "service")
	private Collection<Utilisateur> utilisateurs;

	public void addUtilisateurs(Utilisateur utilisateur) {
		utilisateur.setService(this);
		this.utilisateurs.add(utilisateur);
	}

	public int getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}

	public Pole getPole() {
		return pole;
	}

	public Collection<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	private void setId(int id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public void setPole(Pole pole) {
		this.pole = pole;
	}

}
