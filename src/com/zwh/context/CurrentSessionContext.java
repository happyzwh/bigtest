package com.zwh.context;

import java.io.Serializable;

import com.zwh.sessionfactory.Session;

public interface CurrentSessionContext extends Serializable{
	
	public Session currentSession();
	
	public void removeCurrentSession();

}
