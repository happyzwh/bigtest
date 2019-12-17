package com.zwh.sessionfactory;

import java.util.HashMap;
import java.util.Map;

import com.zwh.exception.SessionException;
import com.zwh.transaction.JtaTransaction;
import com.zwh.transaction.JtaTransactionImpl;

public class SessionProxyImpl implements SessionProxy{

		private Map<String,Session> map = new HashMap<String,Session>();
		
		private JtaSessionFactory jtaSessionFactory;
		
		private JtaTransaction jtaTransaction;
		
		private boolean close;
		
		public SessionProxyImpl() {
			try{
				jtaTransaction = new JtaTransactionImpl();
				jtaTransaction.setSessionProxy(this);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	
		public Map<String, Session> getMap() {
			return map;
		}
	
		public void setMap(Map<String, Session> map) {
			this.map = map;
		}


		@Override
	    public void begin() {
			// TODO Auto-generated method stub
//				if(map != null && !map.isEmpty()){
//					for(Map.Entry<String, Session> entry:map.entrySet()){
//						if(!entry.getValue().getTransaction().isBegin())
//						entry.getValue().begin();
//					}
//				}
			}

		@Override
		public void commit() {
			// TODO Auto-generated method stub
				if(map != null && !map.isEmpty()){
					for(Map.Entry<String, Session> entry:map.entrySet()){
						if(entry.getValue().getTransaction().isActive() && !entry.getValue().getTransaction().isCommitted())    
						entry.getValue().commit();
					}
				}
		}

		@Override
		public void rollback() {
			// TODO Auto-generated method stub
				if(map != null && !map.isEmpty()){
					for(Map.Entry<String, Session> entry:map.entrySet()){
						if(entry.getValue().getTransaction().isActive() && !entry.getValue().getTransaction().isRolledBack())
						entry.getValue().rollback();
					}
				}
			}
		
		@Override
		public void close()  throws SessionException{
			// TODO Auto-generated method stub
			try{
				if(map != null && !map.isEmpty()){
					for(Map.Entry<String, Session> entry:map.entrySet()){
						entry.getValue().close();
					}
				}
				this.jtaTransaction = null;
			    if(jtaSessionFactory != null)jtaSessionFactory.removeSession();
			    this.setClose(true);
			}catch(Exception e){
				throw new  SessionException("Session close() error!");
			}
		}
		@Override
		public JtaSessionFactory getJtaSessionFactory(){
			return jtaSessionFactory;
		}
		@Override
		public void setJtaSessionFactory(JtaSessionFactory jtaSessionFactory){
		   this.jtaSessionFactory = jtaSessionFactory;
		}

		public JtaTransaction getJtaTransaction() {
			return jtaTransaction;
		}

		public void setJtaTransaction(JtaTransaction jtaTransaction) {
			this.jtaTransaction = jtaTransaction;
		}

		public boolean isClose() {
			return close;
		}

		public void setClose(boolean close) {
			this.close = close;
		}
}
