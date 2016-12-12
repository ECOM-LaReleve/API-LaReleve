package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.lr.entity.RessourcesMenages;
import com.lr.remote.IRessourcesMenagesEJBRemote;

@Stateless
public class RessourcesMenagesEJB extends BasicEJB implements IRessourcesMenagesEJBRemote {

	@Override
	public void create(RessourcesMenages ri) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], li=%s", em, ri);
		em.persist(ri);
	}

	@Override
	public List<RessourcesMenages> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]" , em);
		Query query = em.createNamedQuery("RessourcesMenages.findAll");
		return query.getResultList();
	}

	@Override
	public List<RessourcesMenages> findByIdMenage(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], idMenage=%s", em, id);
		Query query = em.createNamedQuery("RessourcesMenages.findByIdMenage");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public List<RessourcesMenages> findByIdRessource(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], idRessource=%s", em, id);
		Query query = em.createNamedQuery("RessourcesMenages.findByIdRessource");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public void remove(RessourcesMenages ri) {
		LOGGER.logDebug(this, "<DELETE>", ri);
		em.remove(em.merge(ri));
	}

}
