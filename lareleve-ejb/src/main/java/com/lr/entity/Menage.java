package com.lr.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Menages")
public class Menage implements Serializable{


	public enum Statut{
		/*TODO*/
	}


	private static final long serialVersionUID = 4987314229184230756L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable=false, unique=true)
	@Enumerated(EnumType.STRING)
	private Statut statut;

	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp dateEntree;

	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp dateSortie;

	private String adresseSortie;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "idReferant")
	private Utilisateur referant;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "idLogement")
	private Logement logement;

	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="idMenage")
	private Collection<Individu> individus;


	public void addIndividus(Individu individu) {
		individu.setMenage(this);
		this.individus.add(individu);
	}

	public String getAdresseSortie() {
		return adresseSortie;
	}

	public Timestamp getDateEntree() {
		return dateEntree;
	}

	public Timestamp getDateSortie() {
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

	public Utilisateur getReferant() {
		return referant;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setAdresseSortie(String adresseSortie) {
		this.adresseSortie = adresseSortie;
	}

	public void setDateEntree(Timestamp dateEntree) {
		this.dateEntree = dateEntree;
	}

	public void setDateSortie(Timestamp dateSortie) {
		this.dateSortie = dateSortie;
	}

	private void setId(int id) {
		this.id = id;
	}

	public void setLogement(Logement logement) {
		this.logement = logement;
	}

	public void setReferant(Utilisateur referant) {
		this.referant = referant;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}


}
