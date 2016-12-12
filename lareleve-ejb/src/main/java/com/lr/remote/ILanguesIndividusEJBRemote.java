package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.LanguesIndividus;

@Remote
public interface ILanguesIndividusEJBRemote {

	void create(LanguesIndividus li);

	List<LanguesIndividus> findAll();

	List<LanguesIndividus> findByIdIndividu(Object id);

	List<LanguesIndividus> findByIdLangue(Object id);

	void remove(LanguesIndividus li);
}
