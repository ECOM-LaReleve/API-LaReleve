package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.PrestationsRealisees;

@Remote
public interface IPrestationsRealiseesEJBRemote {

	void create(PrestationsRealisees prest);

	void edit(PrestationsRealisees prest);

	PrestationsRealisees find(Object id);

	List<PrestationsRealisees> findAll();

	List<PrestationsRealisees> findByIdIndividu(int id);

	List<PrestationsRealisees> findByIdMenage(int id);

	void remove(PrestationsRealisees prest);

}
