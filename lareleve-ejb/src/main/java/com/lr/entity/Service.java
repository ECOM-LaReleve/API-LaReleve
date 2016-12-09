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
@Table(name = "Services")
@NamedQueries({
	@NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s"),
	@NamedQuery(name = "Service.findById", query = "SELECT s FROM Service s WHERE s.id = :id"),
	@NamedQuery(name = "Service.findPoleByIdService", query = "SELECT s.pole FROM Service s WHERE s.id = :id")
})
public class Service implements Serializable {

	private static final long serialVersionUID = -3847457095945195715L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	private String libelle;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idPole")
	private Pole pole;

	/*
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "service")
	private Collection<Utilisateur> utilisateurs;

	public void addUtilisateurs(Utilisateur utilisateur) {
		utilisateur.setService(this);
		this.utilisateurs.add(utilisateur);
	}
	 */

	public int getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}

	public Pole getPole() {
		return pole;
	}

	/*
	public Collection<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}
	 */
	public void setId(int id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public void setPole(Pole pole) {
		this.pole = pole;
	}


}
