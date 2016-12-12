package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.Besoin;

@Remote
public interface IBesoinEJBRemote {

	int create(Besoin besoin);

	void edit(Besoin besoin);

	Besoin find(Object id);

	List<Besoin> findAll();

	Besoin findByLibelle(String lib);

	void remove(Besoin besoin);

}
