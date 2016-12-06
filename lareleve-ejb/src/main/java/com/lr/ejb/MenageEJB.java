package com.lr.ejb;

import java.util.List;

import javax.persistence.Query;

import com.lr.entity.Menage;
import com.lr.remote.IMenageEJBRemote;

public class MenageEJB extends BasicEJB implements IMenageEJBRemote{

	@Override
	public void create(Menage m) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], menage=%s", em, m);
		em.persist(m);
	}

	@Override
	public void edit(Menage menage) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], menage=%s", em, menage);
		em.merge(menage);
	}

	@Override
	public Menage find(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], id=%s", em, id);
		return em.find(Menage.class, id);
	}

	@Override
	public List<Menage> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]", em);
		return em.createNamedQuery("Menage.findAll").getResultList();
	}

	@Override
	public List<Menage> findByNameChefMenage(String name) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], chefMenage=%s", em, name);
		Query query = em.createNamedQuery("Menage.findByNameChefMenage");
		query.setParameter("name", name);
		return query.getResultList();
	}

	@Override
	public void remove(Menage m) {
		LOGGER.logDebug(this, "<DELETE>", "em=[%s], menage=%s", em, m);
		em.remove(m);
	}

}
