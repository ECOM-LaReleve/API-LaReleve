package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.Service;

@Remote
public interface IServiceEJBRemote {

	void create(Service service);

	Service find(Object id);

	List<Service> findAll();

	void remove(Service service);

}
