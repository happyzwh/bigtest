package com.zwh.sessionfactory;

import java.sql.SQLException;

import com.zwh.context.CurrentSessionContext;
import com.zwh.context.ThreadLocalSessionContext;
import com.zwh.datasource.BasicDataSourceFactory;
import com.zwh.datasource.DataSourceFactory;
import com.zwh.exception.DataSourceInstanceException;
import com.zwh.transaction.JdbcTransactionImpl;

public class BasicSessionFactory implements SessionFactory{

	private CurrentSessionContext threadLocalSessionContext;
	
	private static BasicSessionFactory instance;
	
	private DataSourceFactory dataSourceFactory;
	
	public BasicSessionFactory(){
		threadLocalSessionContext = new ThreadLocalSessionContext();
		dataSourceFactory = BasicDataSourceFactory.getInstance();
	}
	
	
	public static BasicSessionFactory getInstance() {
		if(instance == null)instance = new BasicSessionFactory();
		return instance;
	}

	public static void setInstance(BasicSessionFactory instance) {
		BasicSessionFactory.instance = instance;
	}

	public CurrentSessionContext getThreadLocalSessionContext() {
		return threadLocalSessionContext;
	}


	public void setThreadLocalSessionContext(
			ThreadLocalSessionContext threadLocalSessionContext) {
		this.threadLocalSessionContext = threadLocalSessionContext;
	}


	@Override
	public Session getSession() throws DataSourceInstanceException {
		// TODO Auto-generated method stub
		Session session = null;
		try{
			session = threadLocalSessionContext.currentSession();
			if(session.getConnection() == null)
		       session.setConnection(dataSourceFactory.getDataSource().getConnection());
			if(session.getTransaction() == null){
				session.setTransaction(new JdbcTransactionImpl());
				session.getTransaction().setConnection(session.getConnection());
			}
			if(session.getSessionFactory() == null)
		        session.setSessionFactory(this);
			if(session.getTransaction().isActive())session.begin();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return session;
	}


	@Override
	public void removeSession() {
		// TODO Auto-generated method stub
		threadLocalSessionContext.removeCurrentSession();
	}

}
