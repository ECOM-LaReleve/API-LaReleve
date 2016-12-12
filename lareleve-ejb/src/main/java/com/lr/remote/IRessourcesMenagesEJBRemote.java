package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.RessourcesMenages;

@Remote
public interface IRessourcesMenagesEJBRemote {

	void create(RessourcesMenages ri);

	List<RessourcesMenages> findAll();

	List<RessourcesMenages> findByIdMenage(Object id);

	List<RessourcesMenages> findByIdRessource(Object id);

	void remove(RessourcesMenages ri);
}
