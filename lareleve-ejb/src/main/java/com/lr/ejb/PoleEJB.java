package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.lr.entity.Pole;
import com.lr.remote.IPoleEJBRemote;

@Stateless
public class PoleEJB extends BasicEJB implements IPoleEJBRemote {

	@Override
	public int create(Pole pole) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], pole=%s", em, pole);
		em.persist(pole);
		em.flush();
		return pole.getId();
	}

	@Override
	public void edit(Pole pole) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], pole=%s", em, pole);
		em.merge(pole);
	}

	@Override
	public Pole find(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], id=%s", em, id);
		Query query = em.createNamedQuery("Pole.findById");
		query.setParameter("id", id);
		return (Pole) query.getSingleResult();
	}

	@Override
	public List<Pole> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]" , em);
		Query query = em.createNamedQuery("Pole.findAll");
		return query.getResultList();
	}

	@Override
	public void remove(Pole pole) {
		LOGGER.logDebug(this, "<DELETE>", pole);
		em.remove(em.merge(pole));
	}

}
