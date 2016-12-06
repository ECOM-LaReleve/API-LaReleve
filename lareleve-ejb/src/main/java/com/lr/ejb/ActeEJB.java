package com.lr.ejb;

import java.util.List;

import com.lr.entity.Acte;
import com.lr.entity.ActesRealises;
import com.lr.remote.IActeEJBRemote;

public class ActeEJB extends BasicEJB implements IActeEJBRemote {

	@Override
	public void addActeRealise(int id, ActesRealises acteRealise) {
		Acte A = em.find(Acte.class, id);
		A.addActesRealises(acteRealise);
	}

	@Override
	public void create(Acte acte) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], acte=%s", em, acte);
		em.persist(acte);
	}

	@Override
	public void edit(Acte acte) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], acte=%s", em, acte);
		em.merge(acte);
	}

	@Override
	public Acte find(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], id=%s", em, id);
		return em.find(Acte.class, id);
	}

	@Override
	public List<Acte> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]", em);
		String wQuery = String.format("select object(o) from %s as o", Acte.class.getName());
		return em.createQuery(wQuery).getResultList();
	}

	@Override
	public void remove(Acte acte) {
		LOGGER.logDebug(this, "<DELETE>", acte);
		em.remove(em.merge(acte));
	}

}
