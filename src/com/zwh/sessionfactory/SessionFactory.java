package com.zwh.sessionfactory;

import com.zwh.exception.DataSourceInstanceException;


public interface SessionFactory {
	
   public Session getSession() throws DataSourceInstanceException;
   
   public void removeSession();
	
}
