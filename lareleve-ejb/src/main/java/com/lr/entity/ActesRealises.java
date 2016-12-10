package com.lr.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
	@NamedQuery(name = "ActesRealises.findAll", query = "SELECT a FROM ActesRealises a"),
	@NamedQuery(name = "ActesRealises.findByIdMenage", query = "SELECT a FROM ActesRealises a WHERE a.menage.id = :id"),
	@NamedQuery(name = "ActesRealises.findByIdIndividu", query = "SELECT a FROM ActesRealises a WHERE a.individu.id = :id"),
	@NamedQuery(name = "ActesRealises.findByIdUtilisateur", query = "SELECT a FROM ActesRealises a WHERE a.utilisateur.id = :id"),
	@NamedQuery(name = "ActesRealises.findByIdBesoin", query = "SELECT a FROM ActesRealises a WHERE a.besoin.id = :id"),
	@NamedQuery(name = "ActesRealises.findByIdPrestation", query = "SELECT a FROM ActesRealises a WHERE a.prestationRealisee.id = :id"),
	@NamedQuery(name = "ActesRealises.findByIdActe", query = "SELECT a FROM ActesRealises a WHERE a.acte.id = :id")
})
public class ActesRealises implements Serializable {


	public enum StatutActe {
		Honore("Honore"), NonHonore("Non honore");
		private String name = "";

		private StatutActe(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	private static final long serialVersionUID = -3413677856784892255L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Enumerated(EnumType.STRING)
	private StatutActe statut;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateRealisation;

	private String commentaire;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idActe", nullable = false)
	private Acte acte;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idUtilisateur", nullable = false)
	private Utilisateur utilisateur;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idMenage")
	private Menage menage;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idIndividu")
	private Individu individu;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idPrestationRealisee")
	private PrestationsRealisees prestationRealisee;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idBesoin")
	private Besoin besoin;

	public Acte getActe() {
		return acte;
	}

	public Besoin getBesoin() {
		return besoin;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public Date getDateRealisation() {
		return dateRealisation;
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

	public PrestationsRealisees getPrestationRealisee() {
		return prestationRealisee;
	}

	public StatutActe getStatut() {
		return statut;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setActe(Acte acte) {
		this.acte = acte;
	}

	public void setBesoin(Besoin besoin) {
		this.besoin = besoin;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public void setDateRealisation(Date dateRealisation) {
		this.dateRealisation = dateRealisation;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIndividu(Individu individu) {
		this.individu = individu;
	}

	public void setMenage(Menage menage) {
		this.menage = menage;
	}

	public void setPrestationRealisee(PrestationsRealisees prestationRealisee) {
		this.prestationRealisee = prestationRealisee;
	}

	public void setStatut(StatutActe statut) {
		this.statut = statut;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
