package com.lr.remote;

import java.sql.Date;
import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.Individu;
import com.lr.entity.Logement;
import com.lr.entity.Menage;
import com.lr.entity.Utilisateur;

@Remote
public interface IMenageEJBRemote {

	void create(Individu chefMenage);

	void create(Individu chefMenage, String adresse);

	void delete(int id);

	Menage find(Object id);

	List<Menage> findAll();

	List<Menage> findByNameChefMenage(String name);

	void updateAdresseActuelle(int id, String adresse);

	void updateAdresseSortie(int id, String adresse);

	void updateChefMenage(int id, Individu chefMenage);

	void updateDateEntree(int id, Date date);

	void updateDateSortie(int id, Date date);

	void updateLogement(int id, Logement logement);

	void updateReferant(int id, Utilisateur referant);


}
