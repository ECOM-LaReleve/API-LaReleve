package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.lr.entity.Langue;
import com.lr.remote.ILangueEJBRemote;

@Stateless
public class LangueEJB extends BasicEJB implements ILangueEJBRemote{

	@Override
	public void create(Langue langue) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], langue=%s", em, langue);
		em.persist(langue);
	}

	@Override
	public void edit(Langue langue) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], langue=%s", em, langue);
		em.merge(langue);
	}

	@Override
	public Langue find(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], id=%s", em, id);
		Query query = em.createNamedQuery("Langue.findById");
		query.setParameter("id", id);
		return (Langue) query.getSingleResult();
	}

	@Override
	public List<Langue> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]" , em);
		Query query = em.createNamedQuery("Langue.findAll");
		return query.getResultList();
	}

	@Override
	public void remove(Langue langue) {
		LOGGER.logDebug(this, "<DELETE>", langue);
		em.remove(em.merge(langue));
	}

}

