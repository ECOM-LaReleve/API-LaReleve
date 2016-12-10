package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.lr.entity.ActesRealises;
import com.lr.remote.IActesRealisesEJBRemote;

@Stateless
public class ActesRealisesEJB extends BasicEJB implements IActesRealisesEJBRemote{

	@Override
	public void create(ActesRealises acte) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], acteationRealisees=%s", em, acte);
		em.persist(acte);
	}

	@Override
	public void edit(ActesRealises acte) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], acteationRealisees=%s", em, acte);
		em.merge(acte);
	}

	@Override
	public ActesRealises find(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], id=%s", em, id);
		return em.find(ActesRealises.class, id);
	}

	@Override
	public List<ActesRealises> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]", em);
		Query query = em.createNamedQuery("ActesRealises.findAll");
		return query.getResultList();
	}

	@Override
	public List<ActesRealises> findByIdActe(Object id) {
		LOGGER.logDebug(this, "<READ BY ACTE>", "em=[%s], id=%s", em, id);
		Query query = em.createNamedQuery("ActesRealises.findByIdActe");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public List<ActesRealises> findByIdBesoin(Object id) {
		LOGGER.logDebug(this, "<READ BY BESOIN>", "em=[%s], id=%s", em, id);
		Query query = em.createNamedQuery("ActesRealises.findByIdBesoin");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public List<ActesRealises> findByIdIndividu(Object id) {
		LOGGER.logDebug(this, "<READ BY INDIVIDU>", "em=[%s], id=%s", em, id);
		Query query = em.createNamedQuery("ActesRealises.findByIdIndividu");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public List<ActesRealises> findByIdMenage(Object id) {
		LOGGER.logDebug(this, "<READ BY MENAGE>", "em=[%s], id=%s", em, id);
		Query query = em.createNamedQuery("ActesRealises.findByIdMenage");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public List<ActesRealises> findByIdPrestation(Object id) {
		LOGGER.logDebug(this, "<READ BY PRESTATION>", "em=[%s], id=%s", em, id);
		Query query = em.createNamedQuery("ActesRealises.findByIdPrestation");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public List<ActesRealises> findByIdUtilisateur(Object id) {
		LOGGER.logDebug(this, "<READ BY UTILISATEUR>", "em=[%s], id=%s", em, id);
		Query query = em.createNamedQuery("ActesRealises.findByIdUtilisateur");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public void remove(ActesRealises acte) {
		LOGGER.logDebug(this, "<DELETE>", "em=[%s], acteationRealisees=%s", em, acte);
		em.remove(em.merge(acte));
	}

}
