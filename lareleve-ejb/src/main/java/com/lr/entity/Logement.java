package com.lr.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Logements")
@NamedQueries({
	@NamedQuery(name = "Logement.findAll", query = "SELECT l FROM Logement l"),
	@NamedQuery(name = "Logement.findById", query = "SELECT l FROM Logement l WHERE l.id = :id")
})
public class Logement implements Serializable {

	public enum StatutLogement {
		Passif("Passif"), Actif("Actif");

		private String name = "";

		StatutLogement(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public enum TypeLogement {
		Appartement("Appartement"), Maison("Maison");

		private String name = "";

		TypeLogement(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	private static final long serialVersionUID = -7647927882656722276L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int idPOHI;

	private int idGestimmLogement;

	private int idGestimmMenages;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatutLogement statut;

	@Column(nullable = false)
	private String adresse;

	private int etage;

	private String digicode;

	private String direction;

	@Enumerated(EnumType.STRING)
	private TypeLogement type;

	private int superficie;

	private int loyer;

	private int charges;

	//@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "logement")
	//private Collection<Menage> menages;

	//public void addMenages(Menage menage) {
	//	menage.setLogement(this);
	//	this.menages.add(menage);
	//}

	public String getAdresse() {
		return adresse;
	}

	public int getCharges() {
		return charges;
	}

	public String getDigicode() {
		return digicode;
	}

	public String getDirection() {
		return direction;
	}

	public int getEtage() {
		return etage;
	}

	public int getId() {
		return id;
	}

	public int getIdGestimmLogement() {
		return idGestimmLogement;
	}

	public int getIdGestimmMenages() {
		return idGestimmMenages;
	}

	public int getIdPOHI() {
		return idPOHI;
	}

	public int getLoyer() {
		return loyer;
	}

	//public Collection<Menage> getMenages() {
	//	return menages;
	//}

	public StatutLogement getStatut() {
		return statut;
	}

	public int getSuperficie() {
		return superficie;
	}

	public TypeLogement getType() {
		return type;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public void setCharges(int charges) {
		this.charges = charges;
	}

	public void setDigicode(String digicode) {
		this.digicode = digicode;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public void setEtage(int etage) {
		this.etage = etage;
	}

	private void setId(int id) {
		this.id = id;
	}

	public void setIdGestimmLogement(int idGestimmLogement) {
		this.idGestimmLogement = idGestimmLogement;
	}

	public void setIdGestimmMenages(int idGestimmMenages) {
		this.idGestimmMenages = idGestimmMenages;
	}

	public void setIdPOHI(int idPOHI) {
		this.idPOHI = idPOHI;
	}

	public void setLoyer(int loyer) {
		this.loyer = loyer;
	}

	public void setStatut(StatutLogement statut) {
		this.statut = statut;
	}

	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}

	public void setType(TypeLogement type) {
		this.type = type;
	}

}
