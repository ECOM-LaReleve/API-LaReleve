package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.ActesRealises;
import com.lr.entity.ActesRealises.StatutActe;

@Remote
public interface IActesRealisesEJBRemote {

	void create(ActesRealises acteRealise);

	void edit(ActesRealises acteRealise);

	ActesRealises find(Object id);

	List<ActesRealises> findAll();

	void remove(ActesRealises acteRealise);

	void updateCommentaire(int id, String commentaire);

	void updateStatut(int id, StatutActe statut);
}
