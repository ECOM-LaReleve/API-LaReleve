package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;

import com.lr.entity.Utilisateur;
import com.lr.local.IUtilisateurEJBLocal;
import com.lr.remote.IUtilisateurEJBRemote;

@Stateless
public class UtilisateurEJB extends BasicEJB
		implements IUtilisateurEJBLocal, IUtilisateurEJBRemote {

	@Override
	public void create(Utilisateur utilisateur) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], utilisateur=%s", em, utilisateur);
		em.persist(utilisateur);
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
	public List<Utilisateur> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]", em);
		String wQuery = String.format("select object(o) from %s as o", Utilisateur.class.getName());
		return em.createQuery(wQuery).getResultList();
	}

	@Override
	public void remove(Utilisateur utilisateur) {
		LOGGER.logDebug(this, "<DELETE>", utilisateur);
		em.remove(em.merge(utilisateur));
	}

}
