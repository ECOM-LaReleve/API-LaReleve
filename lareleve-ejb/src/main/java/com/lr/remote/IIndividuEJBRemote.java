package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.ActesRealises;
import com.lr.entity.Individu;
import com.lr.entity.LanguesIndividus;
import com.lr.entity.Nationnalite;
import com.lr.entity.PrestationsRealisees;
import com.lr.entity.RessourcesIndividus;

@Remote
public interface IIndividuEJBRemote {

	void addActesRealises(int id, ActesRealises acteRealise);

	void addLanguesIndividus(int id, LanguesIndividus langueIndividu);

	void addNationnalite(int id, Nationnalite nationnalite);

	void addPrestationsRealisees(int id, PrestationsRealisees prestationRealisee);

	void addRessourcesIndividus(int id, RessourcesIndividus ressourceIndividu);

	void create(Individu individu);

	void edit(Individu individu);

	Individu find(Object id);

	List<Individu> findAll();

	void remove(Individu individu);

}
