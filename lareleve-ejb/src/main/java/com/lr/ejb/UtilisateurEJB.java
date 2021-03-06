package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.lr.entity.Utilisateur;
import com.lr.local.IUtilisateurEJBLocal;
import com.lr.remote.IUtilisateurEJBRemote;

@Stateless
public class UtilisateurEJB extends BasicEJB
implements IUtilisateurEJBLocal, IUtilisateurEJBRemote {

	@Override
	public int create(Utilisateur utilisateur) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], utilisateur=%s", em, utilisateur);
		em.persist(utilisateur);
		em.flush();
		return utilisateur.getId();
	}

	@Override
	public void edit(Utilisateur utilisateur) {
		LOGGER.logDebug(this, "<UPDATE>", "em=[%s], utilisateur=%s", em, utilisateur);
		em.merge(utilisateur);
	}

	@Override
	public Utilisateur find(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], id=%s", em, id);
		return em.find(Utilisateur.class, id);
	}

	@Override
	public List findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]", em);
		Query query = em.createNamedQuery("Utilisateur.findAll");
		return query.getResultList();
	}

	@Override
	public List<Utilisateur> findByIdService(Object id) {
		LOGGER.logDebug(this, "<READ BY ID SERVICE>", "em=[%s], id=%s", em, id);
		Query query = em.createNamedQuery("Utilisateur.findByIdService");
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public Utilisateur findByUsername(String name) {
		LOGGER.logDebug(this, "<READ BY USERNAME>", "em=[%s], name=%s", em, name);
		Query query = em.createNamedQuery("Utilisateur.findByUsername");
		query.setParameter("name", name);
		return (Utilisateur) query.getSingleResult();
	}

	@Override
	public void remove(Utilisateur utilisateur) {
		LOGGER.logDebug(this, "<DELETE>", utilisateur);
		em.remove(em.merge(utilisateur));
	}

}
