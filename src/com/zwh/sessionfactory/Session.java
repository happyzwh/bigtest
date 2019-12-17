package com.zwh.sessionfactory;

import java.io.Serializable;
import java.sql.Connection;

import com.zwh.exception.SessionException;
import com.zwh.transaction.Transaction;


public interface Session extends Serializable {
	
	public void close()  throws SessionException;
	
    public void begin();
	
	public void commit();
	
	public void rollback();
	
	public void setConnection(Connection connection);
	
	public Connection getConnection();
	
	public SessionFactory getSessionFactory();

	public void setSessionFactory(SessionFactory sessionFactory);
	
	public Transaction getTransaction();

	public void setTransaction(Transaction transaction);
	
	public boolean isClose() ;

	public void setClose(boolean close);
	

}
