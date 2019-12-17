package com.zwh.datasource;

import java.util.Map;

import javax.sql.DataSource;

public interface JtaDataSourceFactory {
	
	public DataSource getDataSource(String dataSourceName);

	public Map<String,DataSource>  getDataSource();

	public void setDataSource(Map<String,DataSource> map);
	
}
