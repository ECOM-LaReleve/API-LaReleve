package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.lr.entity.User;
import com.lr.local.IUserEJBLocal;
import com.lr.remote.IUserEJBRemote;

@Stateless
public class UserEJB implements IUserEJBLocal, IUserEJBRemote {

	@PersistenceContext(unitName = "LaRelevePU")
	private EntityManager em;

	@Override
	public void create(User user) {
		em.persist(user);
	}

	@Override
	public void edit(User user) {
		em.merge(user);
	}

	@Override
	public User find(Object id) {
		return em.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		return em.createQuery("select object(o) from Users as o").getResultList();
	}

	@Override
	public void remove(User user) {
		em.remove(em.merge(user));
	}

}
