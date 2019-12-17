package com.zwh.service;

import java.io.Serializable;

import com.zwh.domain.User;
import com.zwh.exception.BasicException;

public interface UserService extends Serializable{

	public int save(User user) throws BasicException;
	
	public void test()throws BasicException;
	
}
