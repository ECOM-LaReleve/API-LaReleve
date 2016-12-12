package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.lr.entity.RessourcesIndividus;
import com.lr.remote.IRessourcesIndividusEJBRemote;

@Stateless
public class RessourcesIndividusEJB extends BasicEJB implements IRessourcesIndividusEJBRemote{

	@Override
	public void create(RessourcesIndividus ri) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], li=%s", em, ri);
		em.persist(ri);
	}

	@Override
	public List<RessourcesIndividus> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]" , em);
		Query query = em.createNamedQuery("RessourcesIndividus.findAll");
		return query.getResultList();
	}

	@Override
	public List<RessourcesIndividus> findByIdIndividu(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], idIndividu=%s", em, id);
		Query query = em.createNamedQuery("RessourcesIndividus.findByIdIndividu");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public List<RessourcesIndividus> findByIdRessource(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], idRessource=%s", em, id);
		Query query = em.createNamedQuery("RessourcesIndividus.findByIdRessource");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public void remove(RessourcesIndividus ri) {
		LOGGER.logDebug(this, "<DELETE>", ri);
		em.remove(em.merge(ri));
	}

}
