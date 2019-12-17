package com.zwh.context;

import com.zwh.sessionfactory.SessionProxy;
import com.zwh.sessionfactory.SessionProxyImpl;

public class ThreadLocalSessionProxyContext implements CurrentSessionProxyContext{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5434657590959248267L;
	
	private static final ThreadLocal<SessionProxy> context = new ThreadLocal<SessionProxy>();
	
	@Override
	public SessionProxy currentSessionProxy()  {
			// TODO Auto-generated method stub
		    if(context.get() == null)context.set(new SessionProxyImpl());
			return context.get();
	}

	@Override
	public void removeSessionProxy() {
		// TODO Auto-generated method stub
		 context.remove();
	}	

}
