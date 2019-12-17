package com.zwh.transaction;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JdbcTransactionImpl implements Transaction{
	
   /**
	 * 
	 */
	private static final long serialVersionUID = 2957835411449444459L;
	
	private final static Log logger = LogFactory.getLog(JdbcTransactionImpl.class); 
    
	private Connection connection;
	
	private boolean begin;
	
	private boolean rolledBack;
	
	private boolean committed;
	
	private boolean active;

	private int count = 0;
	
	@Override
	public void begin() {
		// TODO Auto-generated method stub
		if(this.connection != null){
				this.closeAutoCommite();   

			    this.setBegin(true);
		    	this.setActive(true);
		    	this.setCommitted(false);   
		    	this.setRolledBack(false);
		    	logger.info("---事务已开始!");
			
		}
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub
		if(this.connection != null){
			try{
			   this.connection.commit();
			   this.setCommitted(true);
			   this.setBegin(false);
			   this.setActive(false);
			   this.openAutoCommite();
			   logger.info("---事务已提交!");
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return this.active;
	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub
		if(this.connection != null){
			try{
			   this.connection.rollback();
			   this.setRolledBack(true);
			   this.setBegin(false);
			   this.setActive(false);
			   this.openAutoCommite();
			   logger.info("---事务已回滚!");
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean isBegin() {
		// TODO Auto-generated method stub
		return begin;
	}

	@Override
	public void setBegin(boolean begin) {
		this.begin = begin;
	}

	@Override
	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return this.committed;
	}

	@Override
	public boolean isRolledBack() {
		// TODO Auto-generated method stub
		return this.rolledBack;
	}

	@Override
	public void setActive(boolean active) {
		// TODO Auto-generated method stub
		this.active = active;
	}

	@Override
	public void setCommitted(Boolean committed) {
		// TODO Auto-generated method stub
		this.committed = committed;
	}

	@Override
	public void setRolledBack(Boolean rolledBack) {
		// TODO Auto-generated method stub
		this.rolledBack = rolledBack;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.count;
	}

	@Override
	public void setCount(int count) {
		// TODO Auto-generated method stub
		this.count = count;
	}
	
	@Override
	public void closeAutoCommite(){
		try{
			if(this.connection.getAutoCommit()){ 
				this.connection.setAutoCommit(false);
			}  
		}catch(Exception e){
			
		}
	}
	@Override
	public void openAutoCommite(){
		try{
			if(!this.connection.getAutoCommit()){ 
				this.connection.setAutoCommit(true);
			}  
		}catch(Exception e){
			
		}
	}
	
}
