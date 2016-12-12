package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.lr.entity.Nationnalite;
import com.lr.remote.INationnaliteEJBRemote;

@Stateless
public class NationnaliteEJB extends BasicEJB implements INationnaliteEJBRemote{

	@Override
	public int create(Nationnalite nationnalite) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], nationnalite=%s", em, nationnalite);
		em.persist(nationnalite);
		em.flush();
		return nationnalite.getId();
	}

	@Override
	public void edit(Nationnalite nationnalite) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], nationnalite=%s", em, nationnalite);
		em.merge(nationnalite);
	}

	@Override
	public Nationnalite find(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], id=%s", em, id);
		Query query = em.createNamedQuery("Nationnalite.findById");
		query.setParameter("id", id);
		return (Nationnalite) query.getSingleResult();
	}

	@Override
	public List<Nationnalite> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]" , em);
		Query query = em.createNamedQuery("Nationnalite.findAll");
		return query.getResultList();
	}

	@Override
	public void remove(Nationnalite nationnalite) {
		LOGGER.logDebug(this, "<DELETE>", nationnalite);
		em.remove(em.merge(nationnalite));
	}

}

