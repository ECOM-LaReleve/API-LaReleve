package com.lr.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Menages")
@NamedQueries({
	@NamedQuery(name = "Menage.findAll", query = "SELECT m FROM Menage m"),
	@NamedQuery(name = "Menage.findById", query = "SELECT m FROM Menage m WHERE m.id = :id"),
	@NamedQuery(name = "Menage.findByNameChefMenage", query = "SELECT m FROM Menage m WHERE m.chefMenage.nomUsage = :name")
})

public class Menage implements Serializable {

	private static final long serialVersionUID = 4987314229184230756L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateEntree;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateSortie;

	private String adresseActuelle;

	private String adresseSortie;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "idChefMenage")
	private Individu chefMenage;

	/*
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idReferant")
	private Utilisateur referant;
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idLogement")
	private Logement logement;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "menage")
	private Collection<Individu> individus;

	@OneToMany(mappedBy = "pk.menage")
	private Set<RessourcesMenages> ressourcesMenages;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "menage")
	private Set<PrestationsRealisees> prestationsRealisees;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "menage")
	private Set<ActesRealises> actesRealises;

	public void addActesRealises(ActesRealises acteRealise) {
		this.actesRealises.add(acteRealise);
	}

	public void addIndividus(Individu individu) {
		individu.setMenage(this);
		this.individus.add(individu);
	}

	public void addPrestationsRealisees(PrestationsRealisees prestationRealisee) {
		this.prestationsRealisees.add(prestationRealisee);
	}

	public void addRessourcesMenages(RessourcesMenages ressourcesMenages) {
		this.ressourcesMenages.add(ressourcesMenages);
	}

	public Set<ActesRealises> getActesRealises() {
		return actesRealises;
	}

	public String getAdresseActuelle() {
		return adresseActuelle;
	}

	public String getAdresseSortie() {
		return adresseSortie;
	}

	public Individu getChefMenage() {
		return chefMenage;
	}

	public Date getDateEntree() {
		return dateEntree;
	}

	public Date getDateSortie() {
		return dateSortie;
	}

	public int getId() {
		return id;
	}

	public Collection<Individu> getIndividus() {
		return individus;
	}

	public Logement getLogement() {
		return logement;
	}

	public Set<PrestationsRealisees> getPrestationsRealisees() {
		return prestationsRealisees;
	}
	/*
	public Utilisateur getReferant() {
		return referant;
	}
	 */
	public Set<RessourcesMenages> getRessourcesMenages() {
		return ressourcesMenages;
	}

	public void setAdresseActuelle(String adresseActuelle) {
		this.adresseActuelle = adresseActuelle;
	}

	public void setAdresseSortie(String adresseSortie) {
		this.adresseSortie = adresseSortie;
	}

	public void setChefMenage(Individu chefMenage) {
		this.chefMenage = chefMenage;
	}

	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	private void setId(int id) {
		this.id = id;
	}

	public void setLogement(Logement logement) {
		this.logement = logement;
	}
	/*
	public void setReferant(Utilisateur referant) {
		this.referant = referant;
	}
	 */
}
