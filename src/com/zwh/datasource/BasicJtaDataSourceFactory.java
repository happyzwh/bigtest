package com.zwh.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.zwh.config.DataSourceHelper;
import com.zwh.config.DataSourceItem;
import com.zwh.exception.DataSourceConfigException;
import com.zwh.exception.DataSourceInstanceException;

public class BasicJtaDataSourceFactory implements JtaDataSourceFactory{
	
	private static BasicJtaDataSourceFactory instance;
	
	private Map<String,DataSource> dataSources = new HashMap<String,DataSource>();
	
	public BasicJtaDataSourceFactory() throws DataSourceConfigException,DataSourceInstanceException{
		   init();
	}
	
	private void init() throws DataSourceConfigException,DataSourceInstanceException{
		Map<String,DataSourceItem> map = DataSourceHelper.getDataSourceMap();
		if(map != null && !map.isEmpty()){
			for(Map.Entry<String,DataSourceItem> entry:map.entrySet()){
				DataSource dataSource = new C3p0Pool(entry.getValue());
				dataSources.put(entry.getKey(), dataSource);
			}
		}
	}
	
	@Override
	public DataSource getDataSource(String dataSourceName) {
		// TODO Auto-generated method stub
			return dataSources.get(dataSourceName);
	}
	
	@Override
	public Map<String, DataSource> getDataSource() {
		// TODO Auto-generated method stub
		return dataSources;
	}
	
	@Override
	public void setDataSource(Map<String, DataSource> dataSources) {
		// TODO Auto-generated method stub
		this.dataSources = dataSources;
	}

	public static BasicJtaDataSourceFactory getInstance() throws DataSourceConfigException ,DataSourceInstanceException{
		if(instance == null)instance = new BasicJtaDataSourceFactory();
		return instance;
	}

	public static void setInstance(BasicJtaDataSourceFactory instance) {
		BasicJtaDataSourceFactory.instance = instance;
	}

	
}
