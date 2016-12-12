package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.Individu;
import com.lr.entity.Menage;

@Remote
public interface IIndividuEJBRemote {

	void create(Individu individu);

	void edit(Individu individu);

	Individu find(Object id);

	List<Individu> findAll();

	List<Individu> findIndividusByIdMenage(Object id);

	List<Menage> findMenageByNameIndividu(String name);

	void remove(Individu individu);

}
