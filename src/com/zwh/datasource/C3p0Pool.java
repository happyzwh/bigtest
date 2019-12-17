package com.zwh.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zwh.config.DataSourceItem;
import com.zwh.exception.DataSourceInstanceException;

public class C3p0Pool implements DataSource{
	
	    
	    private final static Log log = LogFactory.getLog(C3p0Pool.class);
	
		private  DataSource dataSource;
		
		ComboPooledDataSource cpds = new ComboPooledDataSource();

		public C3p0Pool() throws DataSourceInstanceException{ 
			if (dataSource == null) { 
				   initDataSource();
		    } 
		} 
        
		public C3p0Pool(DataSourceItem dataSourceItem) throws DataSourceInstanceException{
			try{
			   cpds.setDriverClass(dataSourceItem.getDriverClassName());
			   cpds.setJdbcUrl(dataSourceItem.getUrl());
			   cpds.setUser(dataSourceItem.getUserName());
			   cpds.setPassword(dataSourceItem.getPassWord());
			   
			   cpds.setMaxPoolSize(dataSourceItem.getMaxPoolSize() != null?dataSourceItem.getMaxPoolSize():2);
               cpds.setMinPoolSize(dataSourceItem.getMinPoolSize() != null?dataSourceItem.getMinPoolSize():1);
               cpds.setInitialPoolSize(dataSourceItem.getIdleConnectionTestPeriod() != null?dataSourceItem.getInitialPoolSize():1);
               cpds.setAcquireRetryDelay(dataSourceItem.getAcquireRetryDelay() != null?dataSourceItem.getAcquireRetryDelay():60);
               cpds.setMaxStatements(dataSourceItem.getMaxStatements() != null?dataSourceItem.getMaxStatements():5);
               cpds.setMaxStatementsPerConnection(dataSourceItem.getMaxStatementsPerConnection() != null?dataSourceItem.getMaxStatementsPerConnection():5);
               cpds.setMaxIdleTime(dataSourceItem.getMaxIdleTime() != null?dataSourceItem.getMaxIdleTime():1000);
               cpds.setAcquireIncrement(dataSourceItem.getAcquireIncrement() != null?dataSourceItem.getAcquireIncrement():1);
               cpds.setIdleConnectionTestPeriod(dataSourceItem.getIdleConnectionTestPeriod() != null?dataSourceItem.getIdleConnectionTestPeriod():60);
               cpds.setAcquireRetryAttempts(dataSourceItem.getAcquireRetryAttempts() != null?dataSourceItem.getAcquireRetryAttempts():3);
               cpds.setAutoCommitOnClose(dataSourceItem.isAutoCommitOnClose());
			   
			}catch(Exception e){
				log.equals("------C3p0Pool init error");
				throw new  DataSourceInstanceException("------C3p0Pool init error");
			}
			dataSource = cpds; 
		}
		
		
		private  void initDataSource()  throws DataSourceInstanceException{ 
			Configuration config = null;
			try{
			    config = new PropertiesConfiguration("/jdbc.properties");
			    
			    cpds.setDriverClass(config.getString("dataSource.driverClassName"));
				cpds.setJdbcUrl(config.getString("dataSource.url"));
				cpds.setUser(config.getString("dataSource.username"));
				cpds.setPassword(config.getString("dataSource.password"));
			    
                if(StringUtils.isNotBlank(config.getString("c3p0.maxPoolSize"))) {
                	cpds.setMaxPoolSize(Integer.valueOf(config.getString("c3p0.maxPoolSize")));
				}
                if(StringUtils.isNotBlank(config.getString("c3p0.minPoolSize"))) {
                	cpds.setMinPoolSize(Integer.valueOf(config.getString("c3p0.minPoolSize")));
				}
                if(StringUtils.isNotBlank(config.getString("c3p0.initialPoolSize"))) {
                	cpds.setInitialPoolSize(Integer.valueOf(config.getString("c3p0.initialPoolSize")));
				}
                if(StringUtils.isNotBlank(config.getString("c3p0.acquireRetryDelay"))) {
                	cpds.setAcquireRetryDelay(Integer.valueOf(config.getString("c3p0.acquireRetryDelay")));
				}
                if(StringUtils.isNotBlank(config.getString("c3p0.maxStatements"))) {
                	cpds.setMaxStatements(Integer.valueOf(config.getString("c3p0.maxStatements")));
				}
                if(StringUtils.isNotBlank(config.getString("c3p0.maxStatementsPerConnection"))) {
                	cpds.setMaxStatementsPerConnection(Integer.valueOf(config.getString("c3p0.maxStatementsPerConnection")));
				}
                if(StringUtils.isNotBlank(config.getString("c3p0.maxIdleTime"))) {
                	cpds.setMaxIdleTime(Integer.valueOf(config.getString("c3p0.maxIdleTime")));
				}
                if(StringUtils.isNotBlank(config.getString("c3p0.acquireIncrement"))) {
                	cpds.setAcquireIncrement(Integer.valueOf(config.getString("c3p0.acquireIncrement")));
				}
                if(StringUtils.isNotBlank(config.getString("c3p0.idleConnectionTestPeriod"))) {
                	cpds.setIdleConnectionTestPeriod(Integer.valueOf(config.getString("c3p0.idleConnectionTestPeriod")));
				}
                if(StringUtils.isNotBlank(config.getString("c3p0.acquireRetryAttempts"))) {
                	cpds.setAcquireRetryAttempts(Integer.valueOf(config.getString("c3p0.acquireRetryAttempts")));
				}
                if(StringUtils.isNotBlank(config.getString("c3p0.autoCommitOnClose"))) {
                	cpds.setAutoCommitOnClose(Boolean.valueOf(config.getString("c3p0.autoCommitOnClose")));
				}
			}catch(Exception e){
				log.equals("------C3p0Pool init error");
				throw new  DataSourceInstanceException("------C3p0Pool init error");
			}
			
			dataSource = cpds; 
		}

		public  DataSource getDataSource() {
			return dataSource;
		}

		public  void setDataSource(DataSource dataSource) {
			this.dataSource = dataSource;
		}

		@Override
		public Connection getConnection() throws SQLException {
			// TODO Auto-generated method stub
			Connection  conn = null;
			try { 
			    conn = dataSource.getConnection(); 
			} catch (SQLException e) { 
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			} 
			return conn; 
		}

		@Override
		public Connection getConnection(String username, String password)
				throws SQLException {
			// TODO Auto-generated method stub
			return dataSource.getConnection(username, password);
		}

		@Override
		public PrintWriter getLogWriter() throws SQLException {
			// TODO Auto-generated method stub
			return dataSource.getLogWriter();
		}

		@Override
		public int getLoginTimeout() throws SQLException {
			// TODO Auto-generated method stub
			return dataSource.getLoginTimeout();
		}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
		public void setLogWriter(PrintWriter out) throws SQLException {
			// TODO Auto-generated method stub
			dataSource.setLogWriter(out);
		}

		@Override
		public void setLoginTimeout(int seconds) throws SQLException {
			// TODO Auto-generated method stub
			dataSource.setLoginTimeout(seconds);
		}

		@Override
		public boolean isWrapperFor(Class<?> iface) throws SQLException {
			// TODO Auto-generated method stub
			return dataSource.isWrapperFor(iface);
		}

		@Override
		public <T> T unwrap(Class<T> iface) throws SQLException {
			// TODO Auto-generated method stub
			return dataSource.unwrap(iface);
		}
	}

