package com.lr.ejb;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import com.lr.entity.Individu;
import com.lr.entity.Menage;
import com.lr.entity.Prestation;
import com.lr.entity.PrestationsRealisees;
import com.lr.entity.PrestationsRealisees.StatutPrestation;
import com.lr.entity.Utilisateur;
import com.lr.remote.IPrestationsRealiseesEJBRemote;

public class PrestationsRealiseesEJB extends BasicEJB implements IPrestationsRealiseesEJBRemote{

	@Override
	public void create(Individu individu, Prestation prestation, Utilisateur utilisateur) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], individu=%s, prestation=%s, utilisateur=%s", em, individu, prestation, utilisateur);
		PrestationsRealisees prest = new PrestationsRealisees();
		em.getTransaction( ).begin( );
		prest.setIndividu(individu);
		prest.setPrestation(prestation);
		prest.setUtilisateur(utilisateur);
		prest.setDateCreation(Date.from(Instant.now()));
		prest.setStatut(StatutPrestation.EnCours);
		em.persist(prest);
		em.getTransaction( ).commit( );
	}

	@Override
	public void create(Menage menage, Prestation prestation, Utilisateur utilisateur) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], menage=%s, prestation=%s, utilisateur=%s", em, menage, prestation, utilisateur);
		PrestationsRealisees prest = new PrestationsRealisees();
		em.getTransaction( ).begin( );
		prest.setMenage(menage);
		prest.setPrestation(prestation);
		prest.setUtilisateur(utilisateur);
		prest.setDateCreation(Date.from(Instant.now()));
		prest.setStatut(StatutPrestation.EnCours);
		em.persist(prest);
		em.getTransaction( ).commit( );
	}

	@Override
	public List<PrestationsRealisees> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]", em);
		return em.createNamedQuery("PrestationsRealisees.findAll").getResultList();
	}

	@Override
	public List<PrestationsRealisees> findByIdIndividu(int id) {
		LOGGER.logDebug(this, "<READ BY INDIVIDU>", "em=[%s], id=%s", em, id);
		return em.createNamedQuery("PrestationsRealisees.findByIdIndividu").getResultList();
	}

	@Override
	public List<PrestationsRealisees> findByIdMenage(int id) {
		LOGGER.logDebug(this, "<READ BY MENAGE>", "em=[%s], id=%s", em, id);
		return em.createNamedQuery("PrestationsRealisees.findByIdMenage").getResultList();
	}

	@Override
	public void updateCommentaire(int id, String commentaire) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], id=%s, commentaire=%s", em, id, commentaire);
		PrestationsRealisees prest = em.find(PrestationsRealisees.class, id);
		em.getTransaction( ).begin( );
		prest.setCommentaire(commentaire);
		em.getTransaction( ).commit( );
	}

	@Override
	public void updateDateFin(int id, Date date) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], id=%s, date=%s", em, id, date);
		PrestationsRealisees prest = em.find(PrestationsRealisees.class, id);
		em.getTransaction( ).begin( );
		prest.setDateFin(date);
		em.getTransaction( ).commit( );
	}

	@Override
	public void updateStatut(int id, StatutPrestation statut) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], id=%s, statut=%s", em, id, statut);
		PrestationsRealisees prest = em.find(PrestationsRealisees.class, id);
		em.getTransaction( ).begin( );
		prest.setStatut(statut);
		em.getTransaction( ).commit( );
	}

}
