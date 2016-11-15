package com.lr.entity;

import java.io.Serializable;
import java.sql.Timestamp;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Individus")
public class Individu implements Serializable{


	public enum StatutEntreeFrance{
		/*TODO*/
	}


	public enum StatutMatrimonial{
		/*TODO*/
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
	private Timestamp dateEntreeFr;

	@Enumerated(EnumType.STRING)
	private StatutEntreeFrance statutFr;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "idMenage")
	private Menage menage;


	public Timestamp getDateEntreeFr() {
		return dateEntreeFr;
	}

	public int getId() {
		return id;
	}

	public Menage getMenage() {
		return menage;
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

	public void setDateEntreeFr(Timestamp dateEntreeFr) {
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

	public void setTel(int tel) {
		this.tel = tel;
	}

	public void setVilleNaissance(String villeNaissance) {
		this.villeNaissance = villeNaissance;
	}



}
