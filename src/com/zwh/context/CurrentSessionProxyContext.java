package com.zwh.context;

import java.io.Serializable;

import com.zwh.sessionfactory.SessionProxy;

public interface CurrentSessionProxyContext extends Serializable{

	public SessionProxy currentSessionProxy();
	
	public void removeSessionProxy();
	
}
