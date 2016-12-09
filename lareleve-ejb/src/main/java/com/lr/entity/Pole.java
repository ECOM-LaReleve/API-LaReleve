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
@Table(name = "Poles")
@NamedQueries({
	@NamedQuery(name = "Pole.findAll", query = "SELECT p FROM Pole p"),
	@NamedQuery(name = "Pole.findById", query = "SELECT p FROM Pole p WHERE p.id = :id")
})
public class Pole implements Serializable {

	private static final long serialVersionUID = 7483223108623355830L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	private String libelle;

	//	@ManyToOne(fetch = FetchType.EAGER)
	//	@JoinColumn(name = "chefPole")
	//	private Utilisateur chefPole;
	/*
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pole")
	private Collection<Service> services;

	public void addServices(Service service) {
		service.setPole(this);
		this.services.add(service);
	}
	 */
	//	public Utilisateur getChefPole() {
	//		return chefPole;
	//	}

	public int getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}
	/*
	public Collection<Service> getServices() {
		return services;
	}
	 */
	//	public void setChefPole(Utilisateur chefPole) {
	//		this.chefPole = chefPole;
	//	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
