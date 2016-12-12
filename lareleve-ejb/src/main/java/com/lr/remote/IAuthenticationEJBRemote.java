package com.lr.remote;

import java.util.List;

import javax.ejb.Remote;

import com.lr.entity.Role.RoleName;

@Remote
public interface IAuthenticationEJBRemote {

	public boolean checkCredentials(String username, String hashedPassword);

	public List<RoleName> getRoles(String username);

}
