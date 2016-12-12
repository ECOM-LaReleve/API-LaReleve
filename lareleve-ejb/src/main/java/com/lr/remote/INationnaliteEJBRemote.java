package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.Nationnalite;

@Remote
public interface INationnaliteEJBRemote {

	int create(Nationnalite nationnalite);

	void edit(Nationnalite nationnalite);

	Nationnalite find(Object id);

	List<Nationnalite> findAll();

	void remove(Nationnalite nationnalite);

}
