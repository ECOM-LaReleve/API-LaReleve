package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.Logement;

@Remote
public interface ILogementEJBRemote {

	int create(Logement logement);

	void edit(Logement logement);

	Logement find(Object id);

	List<Logement> findAll();

	void remove(Logement logement);

}
