package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.lr.entity.Besoin;
import com.lr.remote.IBesoinEJBRemote;

@Stateless
public class BesoinEJB extends BasicEJB implements IBesoinEJBRemote{

	@Override
	public int create(Besoin besoin) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], besoin=%s", em, besoin);
		em.persist(besoin);
		em.flush();
		return besoin.getId();
	}

	@Override
	public void edit(Besoin besoin) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], besoin=%s", em, besoin);
		em.merge(besoin);
	}
	@Override
	public Besoin find(Object id) {
		LOGGER.logDebug(this, "<READ BY ID>", "em=[%s], id=%s", em, id);
		Query query = em.createNamedQuery("Besoin.findById");
		query.setParameter("id", id);
		return (Besoin) query.getSingleResult();
	}

	@Override
	public List<Besoin> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]" , em);
		Query query = em.createNamedQuery("Besoin.findAll");
		return query.getResultList();
	}

	@Override
	public Besoin findByLibelle(String lib) {
		LOGGER.logDebug(this, "<READ BY LIBELLE>", "em=[%s], libelle=%s", em, lib);
		Query query = em.createNamedQuery("Besoin.findByLibelle");
		query.setParameter("libelle", lib);
		return (Besoin) query.getSingleResult();
	}

	@Override
	public void remove(Besoin besoin) {
		LOGGER.logDebug(this, "<DELETE>", besoin);
		em.remove(em.merge(besoin));
	}

}
