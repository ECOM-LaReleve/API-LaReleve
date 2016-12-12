package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.Ressource;

@Remote
public interface IRessourceEJBRemote {

	int create(Ressource res);

	void edit(Ressource res);

	Ressource find(Object id);

	List<Ressource> findAll();

	void remove(Ressource res);

}
