package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.Acte;
import com.lr.entity.ActesRealises;

@Remote
public interface IActeEJBRemote {

	void addActeRealise(int id, ActesRealises acteRealise);

	void create(Acte acte);

	void edit(Acte acte);

	Acte find(Object id);

	List<Acte> findAll();

	void remove(Acte acte);

}
