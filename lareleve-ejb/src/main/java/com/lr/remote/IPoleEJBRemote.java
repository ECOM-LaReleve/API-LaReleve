package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.Pole;

@Remote
public interface IPoleEJBRemote {

	void create(Pole pole);

	Pole find(Object id);

	List<Pole> findAll();

	void remove(Pole pole);
}
