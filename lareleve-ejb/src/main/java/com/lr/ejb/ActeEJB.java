package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.lr.entity.Acte;
import com.lr.remote.IActeEJBRemote;

@Stateless
public class ActeEJB extends BasicEJB implements IActeEJBRemote{

	@Override
	public int create(Acte acte) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], acte=%s", em, acte);
		em.persist(acte);
		em.flush();
		return acte.getId();
	}

	@Override
	public void edit(Acte acte) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], acte=%s", em, acte);
		em.merge(acte);
	}

	@Override
	public Acte find(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], id=%s", em, id);
		Query query = em.createNamedQuery("Acte.findById");
		query.setParameter("id", id);
		return (Acte) query.getSingleResult();
	}

	@Override
	public List<Acte> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]" , em);
		Query query = em.createNamedQuery("Acte.findAll");
		return query.getResultList();
	}

	@Override
	public void remove(Acte acte) {
		LOGGER.logDebug(this, "<DELETE>", acte);
		em.remove(em.merge(acte));
	}

}
