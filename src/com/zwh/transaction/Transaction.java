package com.zwh.transaction;

import java.io.Serializable;
import java.sql.Connection;

public interface Transaction extends Serializable {
	
	public void setBegin(boolean begin);
	
	public boolean isBegin();

	public void begin();
	
	public void commit();
	
	public void rollback();
	
	public boolean isRolledBack();
	
	public void setRolledBack(Boolean rolledBack);
	
	public void setCommitted(Boolean committed);
	
	public boolean isCommitted();
	
	public boolean isActive();
	
	public void setActive(boolean active);
	
	public Connection getConnection();

	public void setConnection(Connection connection);
	
	public int getCount();
	
	public void setCount(int count);
	
	public void closeAutoCommite();
	
	public void openAutoCommite();
}
