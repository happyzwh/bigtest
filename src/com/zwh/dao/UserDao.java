package com.zwh.dao;

import java.io.Serializable;
import java.util.List;

import com.zwh.domain.User;
import com.zwh.exception.BasicException;

public interface UserDao extends Serializable{
	
	public int save(User user)  throws BasicException;
	
	public int update(User user)  throws BasicException;
	
	public int delete(Object o)  throws BasicException;
	
	public User get(Object o)  throws BasicException;
	
	public List<User> list(String sqlStr,Object[] param)  throws BasicException;
	
	public int count(String sqlStr,Object[] param)  throws BasicException;

}
