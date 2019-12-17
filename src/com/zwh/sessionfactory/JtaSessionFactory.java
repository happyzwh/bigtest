package com.zwh.sessionfactory;

import com.zwh.transaction.JtaTransaction;


public interface JtaSessionFactory {
	   
	  public Session getSession(String DbName);
	  
	  public void removeSession();
	  
	  public JtaTransaction getJtaTransaction();

 
}
