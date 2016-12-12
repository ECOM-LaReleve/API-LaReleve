package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.lr.entity.NationnalitesIndividus;
import com.lr.remote.INationnalitesIndividusEJBRemote;

@Stateless
public class NationnalitesIndividusEJB extends BasicEJB implements INationnalitesIndividusEJBRemote{

	@Override
	public void create(NationnalitesIndividus ni) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], li=%s", em, ni);
		em.persist(ni);
	}

	@Override
	public List<NationnalitesIndividus> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]" , em);
		Query query = em.createNamedQuery("NationnalitesIndividus.findAll");
		return query.getResultList();
	}

	@Override
	public List<NationnalitesIndividus> findByIdIndividu(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], idIndividu=%s", em, id);
		Query query = em.createNamedQuery("NationnalitesIndividus.findByIdIndividu");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public List<NationnalitesIndividus> findByIdNationnalite(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], idNationnalite=%s", em, id);
		Query query = em.createNamedQuery("NationnalitesIndividus.findByIdNationnalite");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public void remove(NationnalitesIndividus ni) {
		LOGGER.logDebug(this, "<DELETE>", ni);
		em.remove(em.merge(ni));
	}

}
