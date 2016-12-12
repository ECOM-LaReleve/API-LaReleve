package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.lr.entity.LanguesIndividus;
import com.lr.remote.ILanguesIndividusEJBRemote;

@Stateless
public class LanguesIndividusEJB extends BasicEJB implements ILanguesIndividusEJBRemote{

	@Override
	public void create(LanguesIndividus li) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], li=%s", em, li);
		em.persist(li);
	}

	@Override
	public List<LanguesIndividus> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]" , em);
		Query query = em.createNamedQuery("LanguesIndividus.findAll");
		return query.getResultList();
	}

	@Override
	public List<LanguesIndividus> findByIdIndividu(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], idIndividu=%s", em, id);
		Query query = em.createNamedQuery("LanguesIndividus.findByIdIndividu");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public List<LanguesIndividus> findByIdLangue(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], idLangue=%s", em, id);
		Query query = em.createNamedQuery("LanguesIndividus.findByIdLangue");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public void remove(LanguesIndividus li) {
		LOGGER.logDebug(this, "<DELETE>", li);
		em.remove(em.merge(li));
	}

}
