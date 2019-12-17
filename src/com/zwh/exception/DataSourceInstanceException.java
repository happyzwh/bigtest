package com.zwh.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DataSourceInstanceException extends BasicException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8434955713651283907L;
	
	private final static Log log = LogFactory.getLog(DataSourceInstanceException.class);

	public DataSourceInstanceException(Exception e) {
		super(e);
		// TODO Auto-generated constructor stub
		log.error("DataSourceInstance Error!");
	}
	public DataSourceInstanceException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
		log.error("DataSourceInstance Error! "+s);
	}

}
