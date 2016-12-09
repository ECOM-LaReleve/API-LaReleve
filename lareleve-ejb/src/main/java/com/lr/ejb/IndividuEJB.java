package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.lr.entity.Individu;
import com.lr.entity.Menage;
import com.lr.remote.IIndividuEJBRemote;

@Stateless
public class IndividuEJB extends BasicEJB implements IIndividuEJBRemote{

	@Override
	public void create(Individu individu) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], individu=%s", em, individu);
		em.persist(individu);
	}

	@Override
	public void edit(Individu individu) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], individu=%s", em, individu);
		em.merge(individu);
	}

	@Override
	public Individu find(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], id=%s", em, id);
		Query query = em.createNamedQuery("Individu.findById");
		query.setParameter("id", id);
		Individu result = (Individu) query.getSingleResult();
		return result;
	}

	@Override
	public List<Individu> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]", em);
		Query query = em.createNamedQuery("Individu.findAll");
		List<Individu> result = query.getResultList();
		return result;
	}

	@Override
	public List<Individu> findIndividusByIdMenage(Object id) {
		LOGGER.logDebug(this, "<READ BY ID MENAGE>", "em=[%s], idMenage=%s", em, id);
		Query query = em.createNamedQuery("Individu.findIndividusByIdMenage");
		query.setParameter("id", id);
		List<Individu> result = query.getResultList();
		return result;

	}

	@Override
	public List<Menage> findMenageByNameIndividu(String name) {
		LOGGER.logDebug(this, "<READ MENAGE BY NAME INDIVIDU>", "em=[%s], nomUsage=%s", em, name);
		Query query = em.createNamedQuery("Individu.findMenageByNameIndividu");
		query.setParameter("name", name);
		List <Menage> result = query.getResultList();
		return result;

	}

	@Override
	public void remove(Individu individu) {
		LOGGER.logDebug(this, "<DELETE>", "em=[%s], individu=%s", em, individu);
		em.remove(em.merge(individu));
	}

}
