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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Individus")
@NamedQueries({ @NamedQuery(name = "Individu.findAll", query = "SELECT i FROM Individu i") })
public class Individu implements Serializable {

	public enum StatutEntreeFrance {
		EP_DemandeAsile("En procedure de demande d'asile"), EP_DemandeTitreSejour(
				"En procédure de demande de titre de sejour"), EP_CNDA(
						"En procedure CNDA"), Deboute("Deboute"), PI_Refugie(
								"Protection internationale refugie"), PI_TitreSubsidiaire(
										"Protection internationale à titre subsidiaire");
		private String name = "";

		private StatutEntreeFrance(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public enum StatutMatrimonial {
		Celibataire("Celibataire"), Marie("Marie"), divorce("Divorce"), veuf("Veuf"), EnCouple(
				"En couple");
		private String name = "";

		private StatutMatrimonial(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
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

	private int tel;

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

	public int getTel() {
		return tel;
	}

	public String getVilleNaissance() {
		return villeNaissance;
	}

	public void setActesRealises(Set<ActesRealises> actesRealises) {
		this.actesRealises = actesRealises;
	}

	public void setDateEntreeFr(Date dateEntreeFr) {
		this.dateEntreeFr = dateEntreeFr;
	}

	private void setId(int id) {
		this.id = id;
	}

	public void setLanguesIndividus(Set<LanguesIndividus> languesIndividus) {
		this.languesIndividus = languesIndividus;
	}

	public void setMenage(Menage menage) {
		this.menage = menage;
	}

	public void setNationnalites(Set<Nationnalite> nationnalites) {
		this.nationnalites = nationnalites;
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

	public void setPrestationsRealisees(Set<PrestationsRealisees> prestationsRealisees) {
		this.prestationsRealisees = prestationsRealisees;
	}

	public void setRessourcesIndividus(Set<RessourcesIndividus> ressourcesIndividus) {
		this.ressourcesIndividus = ressourcesIndividus;
	}

	public void setStatutFr(StatutEntreeFrance statutFr) {
		this.statutFr = statutFr;
	}

	public void setStatutMatrimonial(StatutMatrimonial statutMatrimonial) {
		this.statutMatrimonial = statutMatrimonial;
	}

	public void setTel(int tel) {
		this.tel = tel;
	}

	public void setVilleNaissance(String villeNaissance) {
		this.villeNaissance = villeNaissance;
	}

}
