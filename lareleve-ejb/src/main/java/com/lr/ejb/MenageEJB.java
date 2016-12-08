package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.lr.entity.Menage;
import com.lr.remote.IMenageEJBRemote;

@Stateless
public class MenageEJB extends BasicEJB implements IMenageEJBRemote {

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
	public List<Menage> findByNameChefMenage(String name) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], chefMenage=%s", em, name);
		Query query = em.createNamedQuery("Menage.findByNameChefMenage");
		query.setParameter("name", name);
		return query.getResultList();
	}

	@Override
	public void remove(Menage m) {
		LOGGER.logDebug(this, "<DELETE>", "em=[%s], menage=%s", em, m);
		LOGGER.logDebug(this, "<DELETE WHO>", m.getId() + " " +m.getAdresseActuelle());
		em.remove(m);
	}

}
