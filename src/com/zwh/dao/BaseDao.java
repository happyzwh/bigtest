package com.zwh.dao;

import java.io.Serializable;
import java.util.List;

import com.zwh.exception.BasicException;

public interface BaseDao<T> extends Serializable{
	
	public int delete(Class<T> clazz,Object o) throws BasicException;

	public int save(Class<T> clazz,Object o) throws BasicException;
	
	public int update(Class<T> clazz,Object o) throws BasicException;
	
	public T get(Class<T> clazz,Object o) throws BasicException;
	
	public List<T> list(Class<T> clazz,String sqlStr,Object[] param) throws BasicException;
	
	public int Count(String sqlStr,Object[] param) throws BasicException;

}
