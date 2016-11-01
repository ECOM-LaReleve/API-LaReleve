package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lr.entity.Utilisateur;
import com.lr.local.IUtilisateurEJBLocal;
import com.lr.remote.IUtilisateurEJBRemote;

@Stateless
public class UtilisateurEJB implements IUtilisateurEJBLocal, IUtilisateurEJBRemote {

	@PersistenceContext(unitName = "LaRelevePU")
	private EntityManager em;

	@Override
	public void create(Utilisateur utilisateur) {
		em.persist(utilisateur);
	}

	@Override
	public void edit(Utilisateur utilisateur) {
		em.merge(utilisateur);
	}

	@Override
	public Utilisateur find(Object id) {
		return em.find(Utilisateur.class, id);
	}

	@Override
	public List<Utilisateur> findAll() {
		String wQuery = String.format("select object(o) from Utilisateur as o",
				Utilisateur.class.getName());
		return em.createQuery(wQuery).getResultList();
	}

	@Override
	public void remove(Utilisateur utilisateur) {
		em.remove(em.merge(utilisateur));
	}

}
