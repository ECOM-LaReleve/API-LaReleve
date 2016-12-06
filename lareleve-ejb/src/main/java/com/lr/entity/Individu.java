package com.lr.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Individus")
public class Individu implements Serializable {

	public enum StatutEntreeFrance {
		/* TODO */
	}

	public enum StatutMatrimonial {
		/* TODO */
	}

	private static final long serialVersionUID = -3413247064757562755L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String nomNaissance;

	@Column(nullable = false)
	private String nomUsage;

	@Column(nullable = false)
	private String prenom;

	private String tel;

	private String villeNaissance;

	@Enumerated(EnumType.STRING)
	private StatutMatrimonial statutMatrimonial;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateEntreeFr;

	@Enumerated(EnumType.STRING)
	private StatutEntreeFrance statutFr;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idMenage")
	private Menage menage;

	@OneToOne(mappedBy="chefMenage")
	private Menage chefMenage ;

	@ManyToMany(mappedBy = "individus")
	private Set<Nationnalite> nationnalites;

	@OneToMany(mappedBy = "pk.individu")
	private Set<LanguesIndividus> languesIndividus;

	@OneToMany(mappedBy = "pk.individu")
	private Set<RessourcesIndividus> ressourcesIndividus;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "individu")
	private Set<PrestationsRealisees> prestationsRealisees;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "individu")
	private Set<ActesRealises> actesRealises;

	public void addActesRealises(ActesRealises acteRealise) {
		this.actesRealises.add(acteRealise);
	}

	public void addLanguesIndividus(LanguesIndividus languesIndividus) {
		this.languesIndividus.add(languesIndividus);
	}

	public void addNationnalites(Nationnalite nationnalite) {
		this.nationnalites.add(nationnalite);
	}

	public void addPrestationsRealisees(PrestationsRealisees prestationRealisee) {
		this.prestationsRealisees.add(prestationRealisee);
	}

	public void addRessourcesIndividus(RessourcesIndividus ressourcesIndividus) {
		this.ressourcesIndividus.add(ressourcesIndividus);
	}

	public Set<ActesRealises> getActesRealises() {
		return actesRealises;
	}

	public Menage getChefMenage() {
		return chefMenage;
	}

	public Date getDateEntreeFr() {
		return dateEntreeFr;
	}

	public int getId() {
		return id;
	}

	public Set<LanguesIndividus> getLanguesIndividus() {
		return languesIndividus;
	}

	public Menage getMenage() {
		return menage;
	}

	public Set<Nationnalite> getNationnalites() {
		return nationnalites;
	}

	public String getNomNaissance() {
		return nomNaissance;
	}

	public String getNomUsage() {
		return nomUsage;
	}

	public String getPrenom() {
		return prenom;
	}

	public Set<PrestationsRealisees> getPrestationsRealisees() {
		return prestationsRealisees;
	}

	public Set<RessourcesIndividus> getRessourcesIndividus() {
		return ressourcesIndividus;
	}

	public StatutEntreeFrance getStatutFr() {
		return statutFr;
	}

	public StatutMatrimonial getStatutMatrimonial() {
		return statutMatrimonial;
	}

	public String getTel() {
		return tel;
	}

	public String getVilleNaissance() {
		return villeNaissance;
	}

	public void setChefMenage(Menage chefMenage) {
		this.chefMenage = chefMenage;
	}

	public void setDateEntreeFr(Date dateEntreeFr) {
		this.dateEntreeFr = dateEntreeFr;
	}

	private void setId(int id) {
		this.id = id;
	}

	public void setMenage(Menage menage) {
		this.menage = menage;
	}

	public void setNomNaissance(String nomNaissance) {
		this.nomNaissance = nomNaissance;
	}

	public void setNomUsage(String nomUsage) {
		this.nomUsage = nomUsage;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setStatutFr(StatutEntreeFrance statutFr) {
		this.statutFr = statutFr;
	}

	public void setStatutMatrimonial(StatutMatrimonial statutMatrimonial) {
		this.statutMatrimonial = statutMatrimonial;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setVilleNaissance(String villeNaissance) {
		this.villeNaissance = villeNaissance;
	}

}
