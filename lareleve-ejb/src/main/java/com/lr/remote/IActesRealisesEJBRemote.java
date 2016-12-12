package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.ActesRealises;

@Remote
public interface IActesRealisesEJBRemote {

	int create(ActesRealises acte);

	void edit(ActesRealises acte);

	ActesRealises find(Object id);

	List<ActesRealises> findAll();

	List<ActesRealises> findByIdActe(Object id);

	List<ActesRealises> findByIdBesoin(Object id);

	List<ActesRealises> findByIdIndividu(Object id);

	List<ActesRealises> findByIdMenage(Object id);

	List<ActesRealises> findByIdPrestation(Object id);

	List<ActesRealises> findByIdUtilisateur(Object id);

	void remove(ActesRealises acte);

}
