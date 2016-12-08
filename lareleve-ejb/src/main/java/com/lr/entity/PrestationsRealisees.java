package com.lr.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name = "PrestationsRealisees.findAll", query = "SELECT p FROM PrestationsRealisees p"),
	@NamedQuery(name = "PrestationsRealisees.findByIdMenage", query = "SELECT p FROM PrestationsRealisees p WHERE p.menage.id = :id"),
	@NamedQuery(name = "PrestationsRealisees.findByIdIndividu", query = "SELECT p FROM PrestationsRealisees p WHERE p.individu.id = :id")
})
public class PrestationsRealisees implements Serializable {

	public enum StatutPrestation {
		EnCours("EnCours"), Validee("Validee"), Refusee("Refusee");

		private String name = "";

		StatutPrestation(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	private static final long serialVersionUID = -3413677878744895755L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private int seqPrestation;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatutPrestation statut;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFin;

	private String commentaire;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPrestation", nullable = false)
	private Prestation prestation;
	/*
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUtilisateur", nullable = false)
	private Utilisateur utilisateur;
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idMenage")
	private Menage menage;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idIndividu")
	private Individu individu;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "prestationRealisee")
	private Collection<ActesRealises> actesRealises;

	public void addActesRealises(ActesRealises acteRealise) {
		this.actesRealises.add(acteRealise);
	}

	public Collection<ActesRealises> getActesRealises() {
		return actesRealises;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public int getId() {
		return id;
	}

	public Individu getIndividu() {
		return individu;
	}

	public Menage getMenage() {
		return menage;
	}

	public Prestation getPrestation() {
		return prestation;
	}

	public int getSeqPrestation() {
		return seqPrestation;
	}

	public StatutPrestation getStatut() {
		return statut;
	}
	/*
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	 */
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	private void setId(int id) {
		this.id = id;
	}

	public void setIndividu(Individu individu) {
		this.individu = individu;
	}

	public void setMenage(Menage menage) {
		this.menage = menage;
	}

	public void setPrestation(Prestation prestation) {
		this.prestation = prestation;
	}

	public void setSeqPrestation(int seqPrestation) {
		this.seqPrestation = seqPrestation;
	}

	public void setStatut(StatutPrestation statut) {
		this.statut = statut;
	}
	/*
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	 */
}
