package com.zwh.exception;
public class BasicException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1191625226934635737L;
	
	public BasicException(Exception e) {
		super(e);
	}
	
	public BasicException(String s){
		super(s);
	}
  
}
