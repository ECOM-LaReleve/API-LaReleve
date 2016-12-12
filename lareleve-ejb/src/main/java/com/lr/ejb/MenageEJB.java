package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.lr.entity.Menage;
import com.lr.remote.IMenageEJBRemote;

@Stateless
public class MenageEJB extends BasicEJB implements IMenageEJBRemote {

	@Override
	public int create(Menage menage) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], menage=%s", em, menage);
		em.persist(menage);
		em.flush();
		return menage.getId();
	}

	@Override
	public void edit(Menage menage) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], menage=%s", em, menage);
		em.merge(menage);
	}

	@Override
	public Menage find(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], id=%s", em, id);
		//return em.find(Menage.class, id);
		Query query = em.createNamedQuery("Menage.findById");
		query.setParameter("id", id);
		Menage result = (Menage) query.getSingleResult();
		return result;
	}

	@Override
	public List<Menage> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]", em);
		Query query = em.createNamedQuery("Menage.findAll");
		List<Menage> result = query.getResultList();
		return result;
	}

	@Override
	public List<Menage> findByIdReferant(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], idReferant=%s", em, id);
		Query query = em.createNamedQuery("Menage.findByIdReferant");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public void remove(Menage m) {
		LOGGER.logDebug(this, "<DELETE>", "em=[%s], menage=%s", em, m);
		em.remove(em.merge(m));
	}

}
