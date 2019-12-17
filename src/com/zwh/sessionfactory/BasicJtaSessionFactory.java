package com.zwh.sessionfactory;

import java.util.Map;

import javax.sql.DataSource;

import com.zwh.context.CurrentSessionProxyContext;
import com.zwh.context.ThreadLocalSessionProxyContext;
import com.zwh.datasource.BasicJtaDataSourceFactory;
import com.zwh.transaction.JdbcTransactionImpl;
import com.zwh.transaction.JtaTransaction;

public class BasicJtaSessionFactory implements JtaSessionFactory{
	
	private CurrentSessionProxyContext threadLocalSessionProxyContext;
	
	private static BasicJtaSessionFactory instance;
	
	public BasicJtaSessionFactory(){
		threadLocalSessionProxyContext = new ThreadLocalSessionProxyContext();
	}

	public static BasicJtaSessionFactory getInstance() {
		if(instance == null)instance = new BasicJtaSessionFactory();
		return instance;
	}

	public static void setInstance(BasicJtaSessionFactory instance) {
		BasicJtaSessionFactory.instance = instance;
	}

	@Override
	public Session getSession(String DbName) {
		// TODO Auto-generated method stub
		Session session = null;
		try{
			 SessionProxy sessionProxy = threadLocalSessionProxyContext.currentSessionProxy();
			 if(sessionProxy.getJtaSessionFactory() == null)sessionProxy.setJtaSessionFactory(this);
			 session = sessionProxy.getMap().get(DbName);
			 if(session == null){
				 session = new SessionImpl();
				 Map<String,DataSource> map = BasicJtaDataSourceFactory.getInstance().getDataSource();
				 session.setConnection(map.get(DbName).getConnection());
				 session.setTransaction(new JdbcTransactionImpl());
				 session.getTransaction().setConnection(session.getConnection());
				 sessionProxy.getMap().put(DbName, session);
			 }
			 if(sessionProxy.getJtaTransaction().isActive())session.begin();
		}catch(Exception e){
			e.printStackTrace();
		}
		return session;
	}


	@Override
	public void removeSession() {
		// TODO Auto-generated method stub
		threadLocalSessionProxyContext.removeSessionProxy();
	}

	public JtaTransaction getJtaTransaction() {
		return threadLocalSessionProxyContext.currentSessionProxy().getJtaTransaction();
	}

       
}
