package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.Acte;

@Remote
public interface IActeEJBRemote {

	void create(Acte acte);

	Acte find(Object id);

	List<Acte> findAll();

	void remove(Acte acte);

}
