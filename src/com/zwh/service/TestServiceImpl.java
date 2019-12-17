package com.zwh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zwh.aop.JtaTransactionAnno;
import com.zwh.dao.TestDao;
import com.zwh.domain.Test;
import com.zwh.exception.BasicException;
@Service("testService")
public class TestServiceImpl implements TestService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 609558883522644670L;
	
	private TestDao testDao;

	@Override
	@JtaTransactionAnno
	public int save(Test test) throws BasicException {
		// TODO Auto-generated method stub
		int code = 0;
		try{
			code = this.testDao.save(test);
//			int i = 0;
//			 i = 20/i;
		}catch(Exception e){
			throw new BasicException(e);
		}
		return code;
	}
    
	@Autowired
	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}

	
}
