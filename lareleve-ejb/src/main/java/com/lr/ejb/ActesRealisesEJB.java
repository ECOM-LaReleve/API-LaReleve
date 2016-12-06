package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;

import com.lr.entity.ActesRealises;
import com.lr.entity.ActesRealises.StatutActe;
import com.lr.remote.IActesRealisesEJBRemote;

@Stateless
public class ActesRealisesEJB extends BasicEJB implements IActesRealisesEJBRemote {

	@Override
	public void create(ActesRealises acteRealise) {

		/*
		 * if (acteRealise.getBesoin() == null && acteRealise.getPrestationRealisee() != null) {
		 * acteRealise.setBesoin(acteRealise.getPrestationRealisee().getPrestation().getBesoin()); }
		 */
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], acteRealise=%s", em, acteRealise);
		em.persist(acteRealise);
	}

	@Override
	public void edit(ActesRealises acteRealise) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], acteRealise=%s", em, acteRealise);
		em.merge(acteRealise);
	}

	@Override
	public ActesRealises find(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], id=%s", em, id);
		return em.find(ActesRealises.class, id);
	}

	@Override
	public List<ActesRealises> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]", em);
		return em.createNamedQuery("Individu.findAll").getResultList();
	}

	@Override
	public void remove(ActesRealises acteRealise) {
		LOGGER.logDebug(this, "<DELETE>", acteRealise);
		em.remove(em.merge(acteRealise));
	}

	@Override
	public void updateCommentaire(int id, String commentaire) {
		ActesRealises AR = em.find(ActesRealises.class, id);
		AR.setCommentaire(commentaire);

	}

	@Override
	public void updateStatut(int id, StatutActe statut) {
		ActesRealises AR = em.find(ActesRealises.class, id);
		AR.setStatut(statut);

	}

}
