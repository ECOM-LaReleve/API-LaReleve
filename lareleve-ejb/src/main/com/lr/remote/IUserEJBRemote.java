package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.User;

@Remote
public interface IUserEJBRemote {

	void create(User personne);

	void edit(User personne);

	User find(Object id);

	List<User> findAll();

	void remove(User personne);

}