package com.zwh.service;

import java.io.Serializable;

import com.zwh.domain.Test;
import com.zwh.exception.BasicException;

public interface TestService extends Serializable{
	
	public int save(Test test) throws BasicException;

}
