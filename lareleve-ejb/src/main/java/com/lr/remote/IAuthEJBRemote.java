package com.lr.remote;

import javax.ejb.Remote;

@Remote
public interface IAuthEJBRemote {

	boolean connect(String aUsername, String aHashedPassword);
}