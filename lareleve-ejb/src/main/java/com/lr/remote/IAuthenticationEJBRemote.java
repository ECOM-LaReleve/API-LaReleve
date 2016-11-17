package com.lr.remote;

import javax.ejb.Remote;

@Remote
public interface IAuthenticationEJBRemote {

	boolean checkCredentials(String username, String hashedPassword);
}
