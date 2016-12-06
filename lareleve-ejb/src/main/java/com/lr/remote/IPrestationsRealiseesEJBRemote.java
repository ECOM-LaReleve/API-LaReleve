package com.lr.remote;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.Individu;
import com.lr.entity.Menage;
import com.lr.entity.Prestation;
import com.lr.entity.PrestationsRealisees;
import com.lr.entity.PrestationsRealisees.StatutPrestation;
import com.lr.entity.Utilisateur;

@Remote
public interface IPrestationsRealiseesEJBRemote {

	void create(Individu individu, Prestation prestation, Utilisateur utilisateur);

	void create(Menage menage, Prestation prestation, Utilisateur utilisateur);

	List<PrestationsRealisees> findAll();

	List<PrestationsRealisees> findByIdIndividu(int id);

	List<PrestationsRealisees> findByIdMenage(int id);

	void updateCommentaire(int id, String commentaire);

	void updateDateFin(int id, Date date);

	void updateStatut(int id, StatutPrestation statut);

}
