package com.lr.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class LanguesIndividusID implements Serializable{

	private static final long serialVersionUID = -3413243678876782755L;


	@ManyToOne
	@Column(name="idLangue")
	private Langue langue;

	@ManyToOne
	@Column(name="idIndividu")
	private Individu individu;


	public Individu getIndividu() {
		return individu;
	}

	public Langue getLangue() {
		return langue;
	}

	public void setIndividu(Individu individu) {
		this.individu = individu;
	}

	public void setLangue(Langue langue) {
		this.langue = langue;
	}

}
