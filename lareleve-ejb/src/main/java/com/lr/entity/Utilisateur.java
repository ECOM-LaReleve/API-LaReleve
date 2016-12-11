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
@Table(name = "Utilisateurs")
@NamedQueries({
	@NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u"),
	@NamedQuery(name = "Utilisateur.findByIdService", query = "SELECT u FROM Utilisateur u WHERE u.service.id = :id"),
	@NamedQuery(name = "Utilisateur.findByUsername", query = "SELECT u FROM Utilisateur u WHERE u.username = :name")
})
public class Utilisateur implements Serializable {

	private static final long serialVersionUID = 7524415434131282699L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String nom;

	@Column(nullable = false)
	private String prenom;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idService")
	private Service service;

	/*
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "referant")
	private Collection<Menage> menages;
	 */

	/*
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "chefPole")
	private Collection<Pole> poles;

	@ManyToMany(mappedBy = "utilisateurs", fetch = FetchType.LAZY)
	private Set<Role> roles;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "utilisateur")
	private Set<PrestationsRealisees> prestationsRealisees;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "utilisateur")
	private Set<ActesRealises> actesRealises;

	public void addActesRealises(ActesRealises acteRealise) {
		this.actesRealises.add(acteRealise);
	}
	 */

	/*
	public void addMenages(Menage menage) {
		menage.setReferant(this);
		this.menages.add(menage);
	}
	 */
	/*
	public void addPoles(Pole pole) {
		pole.setChefPole(this);
		this.poles.add(pole);
	}
	 */

	/*
	public void addPrestationsRealisees(PrestationsRealisees prestationRealisee) {
		this.prestationsRealisees.add(prestationRealisee);
	}
	 */

	/*
	public void addRoles(Role role) {
		this.roles.add(role);
	}

	public Set<ActesRealises> getActesRealises() {
		return actesRealises;
	}

	 */

	public int getId() {
		return id;
	}
	/*
	public Collection<Menage> getMenages() {
		return menages;
	}
	 */
	public String getNom() {
		return nom;
	}

	public String getPassword() {
		//return password;
		return null;
	}
	/*
	public Collection<Pole> getPoles() {
		return poles;
	}
	 */
	public String getPrenom() {
		return prenom;
	}
	/*
	public Set<PrestationsRealisees> getPrestationsRealisees() {
		return prestationsRealisees;
	}

	public Set<Role> getRoles() {
		return roles;
	}
	 */
	public Service getService() {
		return service;
	}

	public String getUsername() {
		return username;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPassword(String password) {
		this.password = MD5Hashing.getInstance().MD5Hashing(password);
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
