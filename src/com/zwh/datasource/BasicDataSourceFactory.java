package com.zwh.datasource;

import javax.sql.DataSource;

import com.zwh.exception.DataSourceInstanceException;

public class BasicDataSourceFactory implements DataSourceFactory{
	
	private static BasicDataSourceFactory instance;
	
	private DataSource dataSource;
	
	public BasicDataSourceFactory(){

	}

	public static  BasicDataSourceFactory getInstance() {
		if(instance == null)instance = new BasicDataSourceFactory();
		return instance;
	}

	public static void setInstance(BasicDataSourceFactory instance) {
		BasicDataSourceFactory.instance = instance;
	}

	@Override
	public DataSource getDataSource() throws DataSourceInstanceException {
		// TODO Auto-generated method stub
		if(dataSource == null){
			dataSource = new C3p0Pool();
		}
		return dataSource;
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		
	}

	
}
