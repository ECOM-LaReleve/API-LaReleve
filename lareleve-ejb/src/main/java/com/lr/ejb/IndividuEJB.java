package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;

import com.lr.entity.ActesRealises;
import com.lr.entity.Individu;
import com.lr.entity.LanguesIndividus;
import com.lr.entity.Nationnalite;
import com.lr.entity.PrestationsRealisees;
import com.lr.entity.RessourcesIndividus;
import com.lr.remote.IIndividuEJBRemote;

@Stateless
public class IndividuEJB extends BasicEJB implements IIndividuEJBRemote {

	@Override
	public void addActesRealises(int id, ActesRealises acteRealise) {
		Individu i = em.find(Individu.class, id);
		i.addActesRealises(acteRealise);
	}

	@Override
	public void addLanguesIndividus(int id, LanguesIndividus langueIndividu) {
		Individu i = em.find(Individu.class, id);
		i.addLanguesIndividus(langueIndividu);
	}

	@Override
	public void addNationnalite(int id, Nationnalite nationnalite) {

		Individu i = em.find(Individu.class, id);
		i.addNationnalites(nationnalite);
	}

	@Override
	public void addPrestationsRealisees(int id, PrestationsRealisees prestationRealisee) {

		Individu i = em.find(Individu.class, id);
		i.addPrestationsRealisees(prestationRealisee);
	}

	@Override
	public void addRessourcesIndividus(int id, RessourcesIndividus ressourceIndividu) {

		Individu i = em.find(Individu.class, id);
		i.addRessourcesIndividus(ressourceIndividu);
	}

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
		return em.find(Individu.class, id);
	}

	@Override
	public List<Individu> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]", em);
		return em.createNamedQuery("Individu.findAll").getResultList();
	}

	@Override
	public void remove(Individu individu) {
		LOGGER.logDebug(this, "<DELETE>", individu);
		em.remove(em.merge(individu));
	}

}
