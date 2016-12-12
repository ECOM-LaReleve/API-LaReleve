package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.RessourcesIndividus;

@Remote
public interface IRessourcesIndividusEJBRemote {

	void create(RessourcesIndividus ri);

	List<RessourcesIndividus> findAll();

	List<RessourcesIndividus> findByIdIndividu(Object id);

	List<RessourcesIndividus> findByIdRessource(Object id);

	void remove(RessourcesIndividus ri);
}
