package com.lr.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ActesRealises implements Serializable {

	public enum StatutActe {
		/* TODO */
	}

	private static final long serialVersionUID = -3413677856784892255L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private int seqActe;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatutActe statut;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateRealisation;

	private String commentaire;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idActe", nullable = false)
	private Acte acte;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUtilisateur", nullable = false)
	private Utilisateur utilisateur;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idMenage")
	private Menage menage;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idIndividu")
	private Individu individu;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPrestationRealisee")
	private PrestationsRealisees prestationRealisee;

	@ManyToOne(fetch = FetchType.LAZY)
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

	public int getSeqActe() {
		return seqActe;
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

	private void setId(int id) {
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

	public void setSeqActe(int seqActe) {
		this.seqActe = seqActe;
	}

	public void setStatut(StatutActe statut) {
		this.statut = statut;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
