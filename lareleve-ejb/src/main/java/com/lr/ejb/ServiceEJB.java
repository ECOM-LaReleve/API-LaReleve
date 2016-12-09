package com.lr.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.lr.entity.Service;
import com.lr.remote.IServiceEJBRemote;

@Stateless
public class ServiceEJB extends BasicEJB implements IServiceEJBRemote {

	@Override
	public void create(Service service) {
		LOGGER.logDebug(this, "<CREATE>", "em=[%s], service=%s", em, service);
		em.persist(service);
	}

	@Override
	public Service find(Object id) {
		LOGGER.logDebug(this, "<READ>", "em=[%s], id=%s", em, id);
		Query query = em.createNamedQuery("Service.findById");
		query.setParameter("id", id);
		return (Service) query.getSingleResult();
	}

	@Override
	public List<Service> findAll() {
		LOGGER.logDebug(this, "<READ ALL>", "em=[%s]" , em);
		Query query = em.createNamedQuery("Service.findAll");
		return query.getResultList();
	}

	@Override
	public void remove(Service service) {
		LOGGER.logDebug(this, "<DELETE>", service);
		em.remove(em.merge(service));
	}

}
