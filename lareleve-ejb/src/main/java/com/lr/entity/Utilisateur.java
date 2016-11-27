package com.lr.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Utilisateurs")
public class Utilisateur implements Serializable {


	private static final long serialVersionUID = 2254274640238186573L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable=false, unique=true)
	private String username;

	@Column(nullable=false)
	private String password;

	@Column(nullable=false)
	private String nom;

	@Column(nullable=false)
	private String prenom;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "idService")
	private Service service;

	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="referant")
	private Collection<Menage> menages;

	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="chefPole")
	private Collection<Pole> poles;

	@ManyToMany(mappedBy="utilisateurs")
	private Set<Role> roles;

	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "idUtilisateur")
	private Set<PrestationsRealisees> prestationsRealisees;

	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "idUtilisateur")
	private Set<ActesRealises> actesRealises;


	public void addActesRealises(ActesRealises acteRealise) {
		this.actesRealises.add(acteRealise);
	}

	public void addMenages(Menage menage) {
		menage.setReferant(this);
		this.menages.add(menage);
	}

	public void addPoles(Pole pole) {
		pole.setChefPole(this);
		this.poles.add(pole);
	}

	public void addPrestationsRealisees(PrestationsRealisees prestationRealisee) {
		this.prestationsRealisees.add(prestationRealisee);
	}

	public void addRoles(Role role) {
		this.roles.add(role);
	}

	public Set<ActesRealises> getActesRealises() {
		return actesRealises;
	}

	public int getId() {
		return id;
	}


	public Collection<Menage> getMenages() {
		return menages;
	}

	public String getNom() {
		return nom;
	}

	public String getPassword() {
		return password;
	}

	public Collection<Pole> getPoles() {
		return poles;
	}

	public String getPrenom() {
		return prenom;
	}

	public Set<PrestationsRealisees> getPrestationsRealisees() {
		return prestationsRealisees;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public Service getService() {
		return service;
	}

	public String getUsername() {
		return username;
	}

	private void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	@Override
	public String toString() {
		return String.format("[Utilisateur : id=%d, username=%s, nom=%s, prenom=%s]", this.id,
				this.username, this.nom, this.prenom);
	}

}
