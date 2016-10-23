package com.lr.local;

import java.util.List;

import javax.ejb.Local;

import com.lr.entity.User;

@Local
public interface IUserEJBLocal {

	void create(User personne);

	void edit(User personne);

	User find(Object id);

	List<User> findAll();

	void remove(User personne);

}