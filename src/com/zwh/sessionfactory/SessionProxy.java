package com.zwh.sessionfactory;

import java.util.Map;

import com.zwh.exception.SessionException;
import com.zwh.transaction.JtaTransaction;
import com.zwh.transaction.JtaTransactionImpl;

public interface SessionProxy {
	
	public Map<String, Session> getMap();
	
	public void setMap(Map<String, Session> map);

	public void begin();
	
	public void commit();
	
	public void rollback();
	
	public void close()  throws SessionException;
	
	public JtaSessionFactory getJtaSessionFactory();
	
	public void setJtaSessionFactory(JtaSessionFactory jtaSessionFactory);
	
	public JtaTransaction getJtaTransaction();

	public void setJtaTransaction(JtaTransaction jtaTransaction);
	
	public boolean isClose() ;

	public void setClose(boolean close);
	
}
