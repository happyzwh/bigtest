package com.zwh.datasource;
import javax.sql.DataSource;

import com.zwh.exception.DataSourceInstanceException;
public interface DataSourceFactory {

	public DataSource getDataSource() throws DataSourceInstanceException;

	public void setDataSource(DataSource dataSource);
}
