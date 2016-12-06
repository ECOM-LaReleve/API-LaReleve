package com.lr.ejb;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import com.lr.entity.Individu;
import com.lr.entity.Logement;
import com.lr.entity.Menage;
import com.lr.entity.Utilisateur;
import com.lr.remote.IMenageEJBRemote;

public class MenageEJB extends BasicEJB implements IMenageEJBRemote{

	@Override
	public void create(Individu chefMenage) {
		LOGGER.logDebug(this, "<TRY-TO-CREATE>", "em=[%s], chefMenage=%s", em, chefMenage);
		if(chefMenage.getChefMenage()==null) {
			Menage menage = new Menage( );
			LOGGER.logDebug(this, "<CREATE>", "em=[%s], menage=%s", em, menage);
			em.getTransaction( ).begin( );
			menage.setChefMenage(chefMenage);
			em.persist(menage);
			em.getTransaction( ).commit( );
		}else{
			LOGGER.logDebug(this, "<ECHEC-CREATE>");
		}
	}

	@Override
	public void create(Individu chefMenage, String adresse) {
		LOGGER.logDebug(this, "<TRY-TO-CREATE>", "em=[%s], chefMenage=%s, adresse=%s", em, chefMenage, adresse);
		if(chefMenage.getChefMenage()==null) {
			Menage menage = new Menage( );
			LOGGER.logDebug(this, "<CREATE>", "em=[%s], menage=%s", em, menage);
			em.getTransaction( ).begin( );
			menage.setChefMenage(chefMenage);
			menage.setAdresseActuelle(adresse);
			em.persist(menage);
			em.getTransaction( ).commit( );
		}else{
			LOGGER.logDebug(this, "<ECHEC-CREATE>");
		}
	}

	@Override
	public void delete(int id) {
		LOGGER.logDebug(this, "<DELETE>", "em=[%s], id=%s", em, id);
		Menage menage = em.find(Menage.class, id);
		em.getTransaction().begin();
		em.remove(menage);
		em.getTransaction().commit();
	}

	@Override
	public Menage find(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], id=%s", em, id);
		return em.find(Menage.class, id);
	}

	@Override
	public List<Menage> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]", em);
		return em.createNamedQuery("Menage.findAll").getResultList();
	}

	@Override
	public List<Menage> findByNameChefMenage(String name) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], chefMenage=%s", em, name);
		Query query = em.createNamedQuery("Menage.findByNameChefMenage");
		query.setParameter("name", name);
		return query.getResultList();
	}

	@Override
	public void updateAdresseActuelle(int id, String adresse) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], id=%s, adresseActuelle=%s", em, id, adresse);
		Menage menage = em.find(Menage.class, id);
		em.getTransaction( ).begin( );
		menage.setAdresseActuelle(adresse);
		em.getTransaction( ).commit( );
	}

	@Override
	public void updateAdresseSortie(int id, String adresse) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], id=%s, adresseSortie=%s", em, id, adresse);
		Menage menage = em.find(Menage.class, id);
		em.getTransaction( ).begin( );
		menage.setAdresseSortie(adresse);
		em.getTransaction( ).commit( );
	}

	@Override
	public void updateChefMenage(int id, Individu chefMenage) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], id=%s, chefMenage=%s", em, id, chefMenage);
		Menage menage = em.find(Menage.class, id);
		em.getTransaction( ).begin( );
		menage.setChefMenage(chefMenage);
		em.getTransaction( ).commit( );
	}

	@Override
	public void updateDateEntree(int id, Date date) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], id=%s, dateEntree=%s", em, id, date);
		Menage menage = em.find(Menage.class, id);
		em.getTransaction( ).begin( );
		menage.setDateEntree(date);
		em.getTransaction( ).commit( );
	}

	@Override
	public void updateDateSortie(int id, Date date) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], id=%s, dateSortie=%s", em, id, date);
		Menage menage = em.find(Menage.class, id);
		em.getTransaction( ).begin( );
		menage.setDateSortie(date);
		em.getTransaction( ).commit( );
	}

	@Override
	public void updateLogement(int id, Logement logement) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], id=%s, logement=%s", em, id, logement);
		Menage menage = em.find(Menage.class, id);
		em.getTransaction( ).begin( );
		menage.setLogement(logement);
		em.getTransaction( ).commit( );
	}

	@Override
	public void updateReferant(int id, Utilisateur referant) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], id=%s, referant=%s", em, id, referant);
		Menage menage = em.find(Menage.class, id);
		em.getTransaction( ).begin( );
		menage.setReferant(referant);
		em.getTransaction( ).commit( );
	}

}
