package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.Langue;

@Remote
public interface ILangueEJBRemote {

	int create(Langue langue);

	void edit(Langue langue);

	Langue find(Object id);

	List<Langue> findAll();

	void remove(Langue langue);

}
