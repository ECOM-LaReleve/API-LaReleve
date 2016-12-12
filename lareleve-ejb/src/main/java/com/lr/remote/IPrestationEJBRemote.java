package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.Prestation;

@Remote
public interface IPrestationEJBRemote {

	void create(Prestation prest);

	void edit(Prestation prest);

	Prestation find(Object id);

	List<Prestation> findAll();

	List<Prestation> findByBesoinId(Object id);

	void remove(Prestation prest);

}
