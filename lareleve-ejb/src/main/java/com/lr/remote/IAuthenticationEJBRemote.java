package com.lr.remote;

import javax.ejb.Remote;

@Remote
public interface IAuthenticationEJBRemote {

	String login(String username, String hashedPassword);

	void logout(String token);
}
