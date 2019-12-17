package com.zwh.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.zwh.exception.DataSourceConfigException;

public class DataSourceConfig {
	
	private static DataSourceConfig instance;
	
	private Map<String,DataSourceItem> dataSourceMap = new HashMap<String,DataSourceItem>();

	public DataSourceConfig() throws DataSourceConfigException{
		init();
	}
	
	@SuppressWarnings("unchecked")
	private void init() throws DataSourceConfigException{
		try{
			  SAXReader saxReader = new SAXReader();
			  Document doc = saxReader.read(this.getClass().getResourceAsStream("/dataSource.xml"));
		     if(doc != null){
		    	 Element root = doc.getRootElement();
		    	 if(root != null){
		    		List<Element> list =  root.elements();
		    		if(list != null && list.size() > 0){
		    			DataSourceItem dataSourceItem = null;
		    			for(Element el : list){
		    				dataSourceItem  = new DataSourceItem();
		    				String name = null;
		    				if(el.attributeValue("name") != null){
		    					 name = (String)el.attributeValue("name");
		    					 dataSourceItem.setName(name);
		    					 dataSourceMap.put(name, dataSourceItem);
		    				}
		    				List<Element> slist =  el.elements();
		    				if(slist != null && slist.size() > 0){
		    					for(Element sel : slist){
		    						if(sel.getName().equals("driverClassName") && sel.getTextTrim() != "") {
		    							dataSourceItem.setDriverClassName((String)sel.getTextTrim());
									}
		    						else if(sel.getName().equals("url") && sel.getTextTrim() != "") {
		    							dataSourceItem.setUrl((String)sel.getTextTrim());
									}
		    						else if(sel.getName().equals("userName") && sel.getTextTrim() != "") {
		    							dataSourceItem.setUserName((String)sel.getTextTrim());
									}
		    						else if(sel.getName().equals("passWord") && sel.getTextTrim() != "") {
		    							dataSourceItem.setPassWord((String)sel.getTextTrim());
									}
		    						else if(sel.getName().equals("maxPoolSize") && sel.getTextTrim() != "") {
		    							dataSourceItem.setMaxPoolSize(Integer.valueOf(sel.getTextTrim()));
									}
		    						else if(sel.getName().equals("minPoolSize") && sel.getTextTrim() != "") {
		    							dataSourceItem.setMinPoolSize(Integer.valueOf(sel.getTextTrim()));
									}
		    						else if(sel.getName().equals("initialPoolSize") && sel.getTextTrim() != "") {
		    							dataSourceItem.setInitialPoolSize(Integer.valueOf(sel.getTextTrim()));
									}
		    						else if(sel.getName().equals("acquireRetryDelay") && sel.getTextTrim() != "") {
		    							dataSourceItem.setAcquireRetryDelay(Integer.valueOf(sel.getTextTrim()));
									}
		    						else if(sel.getName().equals("maxStatements") && sel.getTextTrim() != "") {
		    							dataSourceItem.setMaxStatements(Integer.valueOf(sel.getTextTrim()));
									}
		    						else if(sel.getName().equals("maxStatementsPerConnection") && sel.getTextTrim() != "") {
		    							dataSourceItem.setMaxStatementsPerConnection(Integer.valueOf(sel.getTextTrim()));
									}
		    						else if(sel.getName().equals("maxIdleTime") && sel.getTextTrim() != "") {
		    							dataSourceItem.setMaxIdleTime(Integer.valueOf(sel.getTextTrim()));
									}
		    						else if(sel.getName().equals("acquireIncrement") && sel.getTextTrim() != "") {
		    							dataSourceItem.setAcquireIncrement(Integer.valueOf(sel.getTextTrim()));
									}
		    						else if(sel.getName().equals("idleConnectionTestPeriod") && sel.getTextTrim() != "") {
		    							dataSourceItem.setIdleConnectionTestPeriod(Integer.valueOf(sel.getTextTrim()));
									}
		    						else if(sel.getName().equals("autoCommitOnClose") && sel.getTextTrim() != "") {
		    							dataSourceItem.setAutoCommitOnClose(Boolean.valueOf(sel.getTextTrim()));
									}
		    						else if(sel.getName().equals("acquireRetryAttempts") && sel.getTextTrim() != "") {
		    							dataSourceItem.setAcquireRetryAttempts(Integer.valueOf(sel.getTextTrim()));
									}
		    					}
		    				}
		    			}
		    		}
		    	 }
		     }
		}catch(Exception e){
			throw new  DataSourceConfigException(e);
		}
	}
	
	public static DataSourceConfig getInstance() throws DataSourceConfigException {
		if(instance == null)instance = new DataSourceConfig();
		return instance;
	}

	public Map<String, DataSourceItem> getDataSourceMap() {
		return dataSourceMap;
	}

	public void setDataSourceMap(Map<String, DataSourceItem> dataSourceMap) {
		this.dataSourceMap = dataSourceMap;
	}

}
