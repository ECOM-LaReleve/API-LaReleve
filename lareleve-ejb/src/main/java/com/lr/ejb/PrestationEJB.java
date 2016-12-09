package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.lr.entity.Prestation;
import com.lr.remote.IPrestationEJBRemote;

@Stateless
public class PrestationEJB extends BasicEJB implements IPrestationEJBRemote{

	@Override
	public void create(Prestation prest) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], prestation=%s", em, prest);
		em.persist(prest);
	}

	@Override
	public Prestation find(Object id) {
		LOGGER.logDebug(this, "<READ BY ID>", "em=[%s], id=%s", em, id);
		Query query = em.createNamedQuery("Prestation.findById");
		query.setParameter("id", id);
		return (Prestation) query.getSingleResult();
	}

	@Override
	public List<Prestation> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]" , em);
		Query query = em.createNamedQuery("Prestation.findAll");
		return query.getResultList();
	}

	@Override
	public List<Prestation> findByBesoinId(Object id) {
		LOGGER.logDebug(this, "<READ BY BESOIN ID>", "em=[%s], id=%s", em, id);
		Query query = em.createNamedQuery("Prestation.findByBesoinId");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public void remove(Prestation prest) {
		LOGGER.logDebug(this, "<DELETE>", prest);
		em.remove(em.merge(prest));
	}

}
