package com.lr.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Roles")
public class Role implements Serializable {

	public static enum RoleName {
		TS("TS"), DAE("DAE"), DG("DG"), ACCUEIL("Accueil");

		private String name = "";

		RoleName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	private static final long serialVersionUID = -8157658127538663403L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, unique = true)
	private String libelle;


	@ManyToMany()
	@JoinTable(name = "RolesUtilisateurs", joinColumns = @JoinColumn(name = "idRole", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "idUtilisateur", referencedColumnName = "id"))
	Set<Utilisateur> utilisateurs;

	public void addUtilisateurs(Utilisateur utilisateur) {
		this.utilisateurs.add(utilisateur);
	}

	public int getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}

	public Set<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	private void setId(int id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
