package com.lr.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class LanguesIndividus implements Serializable {


	public enum Level{
		/*TODO*/
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
