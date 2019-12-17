package com.zwh.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DataSourceConfigException extends BasicException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8938020116677464616L;
	
	private final static Log log = LogFactory.getLog(DataSourceConfigException.class);

	public DataSourceConfigException(Exception e) {
		super(e);
		// TODO Auto-generated constructor stub
		log.error("DataSourceConfig Error,please check \"/dataSource.xml\" format!");
	}
	public DataSourceConfigException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
		log.error("DataSourceConfig Error,please check \"/dataSource.xml\" format!"+s);
	}
	
	
	

}
