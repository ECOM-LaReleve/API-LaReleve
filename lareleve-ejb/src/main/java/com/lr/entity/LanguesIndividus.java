package com.lr.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name = "LanguesIndividus.findAll", query = "SELECT a FROM LanguesIndividus a"),
	@NamedQuery(name = "LanguesIndividus.findByIdIndividu", query = "SELECT a FROM LanguesIndividus a WHERE a.pk.individu.id = :id"),
	@NamedQuery(name = "LanguesIndividus.findByIdLangue", query = "SELECT a FROM LanguesIndividus a WHERE a.pk.langue.id = :id")
})
public class LanguesIndividus implements Serializable {

	public enum Level {
		Maternelle("Maternelle"), Bilingue("Bilingue"), Courant("Courant"), Faible("Faible");

		private String name = "";

		Level(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	private static final long serialVersionUID = -3413243678757562755L;

	@Id
	private LanguesIndividusID pk;

	@Enumerated(EnumType.STRING)
	private Level niveauLangue;

	public Level getNiveauLangue() {
		return niveauLangue;
	}

	public LanguesIndividusID getPk() {
		return pk;
	}

	public void setNiveauLangue(Level niveauLangue) {
		this.niveauLangue = niveauLangue;
	}

	public void setPk(LanguesIndividusID pk) {
		this.pk = pk;
	}

}
