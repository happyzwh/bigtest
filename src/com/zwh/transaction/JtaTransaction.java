package com.zwh.transaction;

import com.zwh.sessionfactory.SessionProxy;

public interface JtaTransaction {
    
	public void setBegin(boolean begin);
	
	public boolean isBegin();

	public void begin();
	
	public void commit();
	
	public void rollback();
	
	public boolean isRolledBack();
	
	public boolean isCommitted();
	
	public boolean isActive();
	
	public SessionProxy getSessionProxy();

	public void setSessionProxy(SessionProxy sessionProxy);
	
	public int getCount();
	
	public void setCount(int count);
}
