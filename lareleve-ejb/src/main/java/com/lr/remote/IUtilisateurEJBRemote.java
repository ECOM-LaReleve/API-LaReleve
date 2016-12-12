package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.Utilisateur;

@Remote
public interface IUtilisateurEJBRemote {

	int create(Utilisateur utilisateur);

	void edit(Utilisateur utilisateur);

	Utilisateur find(Object id);

	List<Utilisateur> findAll();

	List<Utilisateur> findByIdService(Object id);

	Utilisateur findByUsername(String name);

	void remove(Utilisateur utilisateur);

}