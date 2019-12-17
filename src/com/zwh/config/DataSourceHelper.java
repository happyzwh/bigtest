package com.zwh.config;

import java.util.Map;

import com.zwh.exception.DataSourceConfigException;

public class DataSourceHelper {

	public static Map<String,DataSourceItem> getDataSourceMap() throws DataSourceConfigException{
		return DataSourceConfig.getInstance().getDataSourceMap();
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws DataSourceConfigException{
		Map<String,DataSourceItem> map = DataSourceHelper.getDataSourceMap();
		if(!map.isEmpty()){
			for(Map.Entry<String,DataSourceItem> entry : map.entrySet()){
				 System.out.println("------"+entry.getKey());
				 DataSourceItem dataSourceItem = entry.getValue();
				 System.out.println("------"+dataSourceItem.getName());
				 System.out.println("------"+dataSourceItem.getDriverClassName());
				 System.out.println("------"+dataSourceItem.getPassWord());
				 System.out.println("------"+dataSourceItem.getUrl());
				 System.out.println("------"+dataSourceItem.getUserName());
				 System.out.println("------"+dataSourceItem.getInitialPoolSize());
				 System.out.println("------"+dataSourceItem.getMaxIdleTime());
				 System.out.println("------"+dataSourceItem.getMaxPoolSize());
				 System.out.println("------"+dataSourceItem.getMaxStatements());
				 System.out.println("------"+dataSourceItem.getMinPoolSize());
			}
		}
	}

}
