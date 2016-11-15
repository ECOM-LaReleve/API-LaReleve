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
@Table(name = "Poles")
public class Pole implements Serializable{


	private static final long serialVersionUID = 7483223108623355830L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable=false, unique=true)
	private String libelle;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "chefPole")
	private Utilisateur chefPole;

	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="idPole")
	private Collection<Service> services;


	public void addServices(Service service) {
		service.setPole(this);
		this.services.add(service);
	}

	public Utilisateur getChefPole() {
		return chefPole;
	}

	public int getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}

	public Collection<Service> getServices() {
		return services;
	}

	public void setChefPole(Utilisateur chefPole) {
		this.chefPole = chefPole;
	}

	private void setId(int id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


}
