package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.NationnalitesIndividus;

@Remote
public interface INationnalitesIndividusEJBRemote {

	void create(NationnalitesIndividus ni);

	List<NationnalitesIndividus> findAll();

	List<NationnalitesIndividus> findByIdIndividu(Object id);

	List<NationnalitesIndividus> findByIdNationnalite(Object id);

	void remove(NationnalitesIndividus ni);

}
