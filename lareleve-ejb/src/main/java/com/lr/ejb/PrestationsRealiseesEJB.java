package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.lr.entity.PrestationsRealisees;
import com.lr.remote.IPrestationsRealiseesEJBRemote;
@Stateless
public class PrestationsRealiseesEJB extends BasicEJB implements IPrestationsRealiseesEJBRemote{

	@Override
	public void create(PrestationsRealisees prest) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], prestationRealisees=%s", em, prest);
		em.persist(prest);
	}

	@Override
	public void edit(PrestationsRealisees prest) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], prestationRealisees=%s", em, prest);
		em.merge(prest);
	}

	@Override
	public PrestationsRealisees find(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], id=%s", em, id);
		return em.find(PrestationsRealisees.class, id);
	}

	@Override
	public List<PrestationsRealisees> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]", em);
		Query query = em.createNamedQuery("PrestationsRealisees.findAll");
		return query.getResultList();
	}

	@Override
	public List<PrestationsRealisees> findByIdIndividu(int id) {
		LOGGER.logDebug(this, "<READ BY INDIVIDU>", "em=[%s], id=%s", em, id);
		Query query = em.createNamedQuery("PrestationsRealisees.findByIdIndividu");
		return query.getResultList();
	}

	@Override
	public List<PrestationsRealisees> findByIdMenage(int id) {
		LOGGER.logDebug(this, "<READ BY MENAGE>", "em=[%s], id=%s", em, id);
		Query query = em.createNamedQuery("PrestationsRealisees.findByIdMenage");
		return query.getResultList();
	}

	@Override
	public void remove(PrestationsRealisees prest) {
		LOGGER.logDebug(this, "<DELETE>", "em=[%s], prestationRealisees=%s", em, prest);
		em.remove(em.merge(prest));
	}

}
