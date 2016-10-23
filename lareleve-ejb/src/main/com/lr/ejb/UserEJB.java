package com.lr.ejb;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;

import com.lr.entity.User;
import com.lr.local.IUserEJBLocal;
import com.lr.remote.IUserEJBRemote;

@Stateless
public class UserEJB implements IUserEJBLocal, IUserEJBRemote {

	@Override
	public void create(User user) {
	}

	@Override
	public void edit(User user) {
	}

	@Override
	public User find(Object id) {
		return new User();
	}

	@Override
	public List<User> findAll() {
		return new LinkedList<>();
	}

	@Override
	public void remove(User user) {
	}

}