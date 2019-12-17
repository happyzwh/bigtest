package com.zwh.dao;

import java.util.List;

import com.zwh.domain.Test;
import com.zwh.exception.BasicException;


public interface TestDao {
	
	public int save(Test test)  throws BasicException;
	
	public int update(Test test)  throws BasicException;
	
	public int delete(Object o)  throws BasicException;
	
	public Test get(Object o)  throws BasicException;
	
	public List<Test> list(String sqlStr,Object[] param)  throws BasicException;
	
	public int count(String sqlStr,Object[] param)  throws BasicException;
	
}
