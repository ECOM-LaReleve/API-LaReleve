package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.Menage;

@Remote
public interface IMenageEJBRemote {

	int create(Menage menage);

	void edit(Menage menage);

	Menage find(Object id);

	List<Menage> findAll();

	List<Menage> findByIdReferant(Object id);

	void remove(Menage menage);


}
