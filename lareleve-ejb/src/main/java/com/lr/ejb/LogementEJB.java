package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.lr.entity.Logement;
import com.lr.remote.ILogementEJBRemote;

@Stateless
public class LogementEJB extends BasicEJB implements ILogementEJBRemote{

	@Override
	public int create(Logement logement) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], logement=%s", em, logement);
		em.persist(logement);
		em.flush();
		return logement.getId();
	}

	@Override
	public void edit(Logement logement) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], logement=%s", em, logement);
		em.merge(logement);
	}

	@Override
	public Logement find(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], id=%s", em, id);
		Query query = em.createNamedQuery("Logement.findById");
		query.setParameter("id", id);
		return (Logement) query.getSingleResult();
	}

	@Override
	public List<Logement> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]" , em);
		Query query = em.createNamedQuery("Logement.findAll");
		return query.getResultList();
	}

	@Override
	public void remove(Logement logement) {
		LOGGER.logDebug(this, "<DELETE>", logement);
		em.remove(em.merge(logement));
	}

}
