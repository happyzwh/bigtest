package com.zwh.sessionfactory;

import java.sql.Connection;

import com.zwh.exception.SessionException;
import com.zwh.transaction.Transaction;

public class SessionImpl implements Session{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 516476802666574852L;

	private Connection connection;
	
	private SessionFactory sessionFactory;
	
	private Transaction transaction;
	
	private boolean close;
	
	public SessionImpl(){
		
	}
	

	@Override
	public void close() throws SessionException{
		// TODO Auto-generated method stub
		try{
		    if(connection != null)connection.close();
		    if(transaction != null)transaction = null;
		    if(sessionFactory != null)sessionFactory.removeSession();
		    this.setClose(true);
		}catch(Exception e){
			throw new SessionException("Session close() error!");
		}
	}
	
	@Override
	public void begin() {
		// TODO Auto-generated method stub
		this.transaction.begin();
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub
		this.transaction.commit();
	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub
		this.transaction.rollback();
	}
	
	@Override
	public void setConnection(Connection connection) {
		// TODO Auto-generated method stub
		this.connection = connection;
	}

	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return this.connection;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}


	@Override
	public boolean isClose() {
		// TODO Auto-generated method stub
		return close;
	}


	@Override
	public void setClose(boolean close) {
		// TODO Auto-generated method stub
		this.close = close;
	}
	
}
