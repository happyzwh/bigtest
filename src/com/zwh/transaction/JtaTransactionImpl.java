package com.zwh.transaction;

import com.zwh.sessionfactory.SessionProxy;

public class JtaTransactionImpl implements JtaTransaction{
	
	private SessionProxy sessionProxy;

	private boolean begin;
	
	private boolean active;
	
	private boolean rolledBack;
	
	private boolean committed;
	
	private int count = 0;
	
	//开始任一个连接即表示已处开始状态
	@Override
	public void begin() {
		// TODO Auto-generated method stub
//		if(sessionProxy != null){
//				sessionProxy.begin();
//				if(!this.isBegin())this.setBegin(true);
//				if(!this.isActive())this.setActive(true);
//		}
		this.setBegin(true);
		this.setActive(true);
		this.setCommitted(false);
		this.setRolledBack(false);
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub
		if(sessionProxy != null){
			sessionProxy.commit();
			this.setCommitted(true);
			this.setBegin(false);
			this.setActive(false);
		}
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return this.active;
	}

	@Override
	public boolean isBegin() {
		// TODO Auto-generated method stub
		return this.begin;
	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub
		if(sessionProxy != null){
			sessionProxy.rollback();
			this.setRolledBack(true);
			this.setBegin(false);
			this.setActive(false);
		}
	}

	@Override
	public void setBegin(boolean begin) {
		// TODO Auto-generated method stub
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
	public SessionProxy getSessionProxy() {
		// TODO Auto-generated method stub
		return sessionProxy;
	}

	@Override
	public void setSessionProxy(SessionProxy sessionProxy) {
		// TODO Auto-generated method stub
		this.sessionProxy = sessionProxy;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setCommitted(boolean committed) {
		this.committed = committed;
	}

	public void setRolledBack(boolean rolledBack) {
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

}
