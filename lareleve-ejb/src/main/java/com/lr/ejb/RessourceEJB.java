package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.lr.entity.Ressource;
import com.lr.remote.IRessourceEJBRemote;

@Stateless
public class RessourceEJB extends BasicEJB implements IRessourceEJBRemote{

	@Override
	public void create(Ressource ressource) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], ressource=%s", em, ressource);
		em.persist(ressource);
	}

	@Override
	public void edit(Ressource ressource) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], ressource=%s", em, ressource);
		em.merge(ressource);
	}

	@Override
	public Ressource find(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], id=%s", em, id);
		Query query = em.createNamedQuery("Ressource.findById");
		query.setParameter("id", id);
		return (Ressource) query.getSingleResult();
	}

	@Override
	public List<Ressource> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]" , em);
		Query query = em.createNamedQuery("Ressource.findAll");
		return query.getResultList();
	}

	@Override
	public void remove(Ressource ressource) {
		LOGGER.logDebug(this, "<DELETE>", ressource);
		em.remove(em.merge(ressource));
	}

}
