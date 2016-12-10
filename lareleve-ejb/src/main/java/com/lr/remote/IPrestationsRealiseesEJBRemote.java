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

	List<PrestationsRealisees> findByIdIndividu(Object id);

	List<PrestationsRealisees> findByIdMenage(Object id);

	List<PrestationsRealisees> findByIdPrestation(Object id);

	List<PrestationsRealisees> findByIdUtilisateur(Object id);

	void remove(PrestationsRealisees prest);

}
