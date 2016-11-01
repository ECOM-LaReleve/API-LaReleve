package com.lr.local;

import java.util.List;

import javax.ejb.Local;

import com.lr.entity.Utilisateur;

@Local
public interface IUtilisateurEJBLocal {

	void create(Utilisateur utilisateur);

	void edit(Utilisateur utilisateur);

	Utilisateur find(Object id);

	List<Utilisateur> findAll();

	void remove(Utilisateur utilisateur);

}