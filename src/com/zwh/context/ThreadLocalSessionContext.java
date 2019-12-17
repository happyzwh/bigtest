package com.zwh.context;

import com.zwh.sessionfactory.Session;
import com.zwh.sessionfactory.SessionImpl;

public class ThreadLocalSessionContext implements CurrentSessionContext{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1987268133673379881L;
	private static final ThreadLocal<Session> context = new ThreadLocal<Session>();
	
	@Override
	public Session currentSession() {
		// TODO Auto-generated method stub
		Session session = context.get();
		if(session == null){
			session = new SessionImpl();
			context.set(session);
		}
		return session;
	}

	@Override
	public void removeCurrentSession() {
		// TODO Auto-generated method stub
		context.remove();
	}
}
