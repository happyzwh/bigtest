package com.zwh.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zwh.domain.Test;
import com.zwh.exception.BasicException;
@Repository("testDao")
public class TestDaoImpl extends BaseJtaDaoImpl<Test> implements TestDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6035710435460418395L;

	@Override
	public int save(Test test) throws BasicException {
		// TODO Auto-generated method stub
		int code = 0;
		try{
			code = this.save(Test.class, test);
		}catch(Exception e){
			throw new BasicException(e);
		}
		return code;
	}

	@Override
	public int update(Test test) throws BasicException {
		// TODO Auto-generated method stub
		return this.update(Test.class, test);
	}

	@Override
	public int delete(Object o) throws BasicException {
		// TODO Auto-generated method stub
		return this.delete(Test.class, o);
	}

	@Override
	public Test get(Object o) throws BasicException {
		// TODO Auto-generated method stub
		return (Test)this.get(Test.class, o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Test> list(String sqlStr, Object[] param) throws BasicException {
		// TODO Auto-generated method stub
		return this.list(Test.class, sqlStr, param);
	}

	@Override
	public int count(String sqlStr, Object[] param) throws BasicException {
		// TODO Auto-generated method stub
		return this.Count(sqlStr, param);
	}
	
}
