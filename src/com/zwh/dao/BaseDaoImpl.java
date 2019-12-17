package com.zwh.dao;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.zwh.exception.BasicException;
import com.zwh.sessionfactory.BasicSessionFactory;
import com.zwh.utils.DomainBeanUtil;


@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8272078129876323832L;

	@Override
	public int save(Class T, Object o) throws BasicException {
		// TODO Auto-generated method stub
		int code = 0;
		PreparedStatement pstmt = null;
		try{
			String tableName = DomainBeanUtil.getTableName(T);
			if(StringUtils.isNotBlank(tableName)){
			    StringBuilder sql = new StringBuilder(" INSERT INTO ").append(tableName).append(" ( "); 		 
			    Map<String,Method> map = DomainBeanUtil.getColumnGetMethod(T);
			    if(!map.isEmpty()){
			    	int index = 0;
				    for(Map.Entry<String,Method> entry:map.entrySet()){
					    	if(index<map.size()-1)sql.append(entry.getKey()).append(" , ");
					    	else sql.append(entry.getKey());
					    	index++;
				    }
			    }
			    sql.append(" ) VALUES ( ");
			    if(!map.isEmpty()){
				    for(int i=0;i<map.size();i++){
					    	if(i<map.size()-1)sql.append(" ? ").append(" , ");
					    	else sql.append(" ? ");
				    }
			    }
			    sql.append(" ) ");
			    pstmt = BasicSessionFactory.getInstance().getSession().getConnection().prepareStatement(sql.toString());
			    if(!map.isEmpty()){
			    	int index = 1;
				    for(Map.Entry<String,Method> entry:map.entrySet()){
				    	if(entry.getValue().getReturnType() == Byte.class || entry.getValue().getReturnType() == byte.class)pstmt.setByte(index++, (Byte)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));
				    	else if(entry.getValue().getReturnType() == Date.class){
				    		Date date = (Date)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes());
				    		pstmt.setTimestamp(index++, new java.sql.Timestamp(date.getTime()));
				    	}
				    	else if(entry.getValue().getReturnType() == Double.class || entry.getValue().getReturnType() == double.class)pstmt.setDouble(index++, (Double)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));
				    	else if(entry.getValue().getReturnType() == Float.class || entry.getValue().getReturnType() == float.class)pstmt.setFloat(index++, (Float)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));
				    	else if(entry.getValue().getReturnType() == Integer.class || entry.getValue().getReturnType() == int.class)pstmt.setInt(index++, (Integer)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));
				    	else if(entry.getValue().getReturnType() == Long.class || entry.getValue().getReturnType() == long.class)pstmt.setLong(index++, (Long)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));
				    	else if(entry.getValue().getReturnType() == Short.class || entry.getValue().getReturnType() == short.class)pstmt.setShort(index++, (Short)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));
				    	else if(entry.getValue().getReturnType() == String.class || entry.getValue().getReturnType() == char.class)pstmt.setString(index++, (String)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));
				    }
			    }
			   code = pstmt.executeUpdate();
			}
		}catch(Exception e){
			throw new BasicException(e);
		}finally{
			if(pstmt != null){
				try{
				   pstmt.close();
				}catch(Exception e){
					
				}
			}
		}
		return code;
	}

	@Override
	public int update(Class T, Object o) throws BasicException {
		// TODO Auto-generated method stub
		int code = 0;
		 PreparedStatement pstmt =  null;
		try{
			String tableName = DomainBeanUtil.getTableName(T);
			String idName = DomainBeanUtil.getIdName(T);
			Method idMethod = DomainBeanUtil.getIdGetMethod(T);	
			if(StringUtils.isNotBlank(tableName)){
			    StringBuilder sql = new StringBuilder(" UPDATE ").append(tableName).append(" SET "); 		 
			    Map<String,Method> map = DomainBeanUtil.getColumnGetMethod(T);
			    if(!map.isEmpty()){
			    	int index = 0;
				    for(Map.Entry<String,Method> entry:map.entrySet()){
					    	if(index<map.size()-1)sql.append(entry.getKey()).append(" = ? , ");
					    	else sql.append(entry.getKey()).append(" = ?  ");
					    	index++;
				    }
			    }
			    sql.append(" WHERE ").append(idName).append(" =  ?");
			    pstmt = BasicSessionFactory.getInstance().getSession().getConnection().prepareStatement(sql.toString());
			    if(!map.isEmpty()){
			    	int index = 1;
				    for(Map.Entry<String,Method> entry:map.entrySet()){
				    	if(entry.getValue().getReturnType() == Byte.class || entry.getValue().getReturnType() == byte.class)pstmt.setByte(index++, (Byte)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));
				    	else if(entry.getValue().getReturnType() == Date.class){
				    		Date date = (Date)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes());
				    		pstmt.setTimestamp(index++, new java.sql.Timestamp(date.getTime()));
				    	}
				    	else if(entry.getValue().getReturnType() == Double.class || entry.getValue().getReturnType() == double.class)pstmt.setDouble(index++, (Double)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));
				    	else if(entry.getValue().getReturnType() == Float.class || entry.getValue().getReturnType() == float.class)pstmt.setFloat(index++, (Float)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));
				    	else if(entry.getValue().getReturnType() == Integer.class || entry.getValue().getReturnType() == int.class)pstmt.setInt(index++, (Integer)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));
				    	else if(entry.getValue().getReturnType() == Long.class || entry.getValue().getReturnType() == long.class)pstmt.setLong(index++, (Long)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));
				    	else if(entry.getValue().getReturnType() == Short.class || entry.getValue().getReturnType() == short.class)pstmt.setShort(index++, (Short)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));
				    	else if(entry.getValue().getReturnType() == String.class || entry.getValue().getReturnType() == char.class)pstmt.setString(index++, (String)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));
				    }
			    	if(idMethod.getReturnType() == Integer.class || idMethod.getReturnType() == int.class)pstmt.setInt(index, (Integer)idMethod.invoke(o, (Object[])idMethod.getParameterTypes()));
			    	else if(idMethod.getReturnType() == Long.class || idMethod.getReturnType() == long.class)pstmt.setLong(index, (Long)idMethod.invoke(o, (Object[])idMethod.getParameterTypes()));
			    	else if(idMethod.getReturnType() == String.class || idMethod.getReturnType() == char.class)pstmt.setString(index, (String)idMethod.invoke(o, (Object[])idMethod.getParameterTypes()));
			    }
			   code = pstmt.executeUpdate();
			}
		}catch(Exception e){
			throw new BasicException(e);
		}finally{
			if(pstmt != null){
				try{
				   pstmt.close();
				}catch(Exception e){
					
				}
			}
		}
		return code;
	}

	@Override
	public int delete(Class T, Object o) throws BasicException{
		// TODO Auto-generated method stub
		int code = 0;
		PreparedStatement pstmt = null;
		try{
			String tableName = DomainBeanUtil.getTableName(T);
			String idName = DomainBeanUtil.getIdName(T);
			Method idMethod = DomainBeanUtil.getIdGetMethod(T);	
			StringBuilder sql = new StringBuilder(" DELETE FROM ");
			sql.append(tableName).append(" WHERE ").append(idName).append(" = ?");
			pstmt = BasicSessionFactory.getInstance().getSession().getConnection().prepareStatement(sql.toString());
			if(o instanceof Number || o instanceof String){
				if(o.getClass() == int.class || o.getClass() == Integer.class  )pstmt.setInt(1, (Integer)o);
				else if(o.getClass() == long.class || o.getClass() == Long.class  )pstmt.setLong(1, (Long)o);
				else if(o.getClass() == String.class || o.getClass() == char.class  )pstmt.setString(1, (String)o);
			}else{
				Object id = DomainBeanUtil.getPropertyValue(idMethod, o);
				if(id.getClass() == int.class || id.getClass() == Integer.class  )pstmt.setInt(1, (Integer)id);
				else if(id.getClass() == long.class || id.getClass() == Long.class  )pstmt.setLong(1, (Long)id);
				else if(id.getClass() == String.class || id.getClass() == char.class  )pstmt.setString(1, (String)id);
			}
			code = pstmt.executeUpdate();
		}catch(Exception e){
			throw new BasicException(e);
		}finally{
			if(pstmt != null){
				try{
				   pstmt.close();
				}catch(Exception e){
					
				}
			}
		}
		return code;
	}

	@Override
	public Object get(Class T, Object o) throws BasicException {
		// TODO Auto-generated method stub
		Object retunrObject = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try{
			retunrObject = T.newInstance();
			String tableName = DomainBeanUtil.getTableName(T);
			String idName = DomainBeanUtil.getIdName(T);
			Method idMethod = DomainBeanUtil.getIdGetMethod(T);	
			StringBuilder sql = new StringBuilder(" SELECT * FROM ");
			sql.append(tableName).append(" WHERE 1 = 1 ");
			Map<String,Method> map = DomainBeanUtil.getColumnGetMethod(T);
	        if(o instanceof Number || o instanceof String){
	        	sql.append(" AND ").append(idName).append(" = ? ");
	        }else{
				if(idMethod.invoke(o, (Object[])idMethod.getParameterTypes()) != null && idMethod.invoke(o, (Object[])idMethod.getParameterTypes()) != ""){
					sql.append(" AND ").append(idName).append(" = ? ");
				}
				if(!map.isEmpty()){
				    for(Map.Entry<String,Method> entry:map.entrySet()){
				    	    if(entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()) != null && entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()) != "")
					    	sql.append(" AND ").append(entry.getKey()).append(" = ? ");
				    }
			    }
	        }
			pstmt = BasicSessionFactory.getInstance().getSession().getConnection().prepareStatement(sql.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			if(o instanceof Number || o instanceof String){
				if(o.getClass() == int.class || o.getClass()  == Integer.class  )pstmt.setInt(1, (Integer)o);
				else if(o.getClass() == long.class || o.getClass()  == Long.class  )pstmt.setLong(1, (Long)o);
				else if(o.getClass() == String.class || o.getClass()  == char.class  )pstmt.setString(1, (String)o);
	        }else{
				int index = 1;
				if(idMethod.invoke(o, (Object[])idMethod.getParameterTypes()) != null && idMethod.invoke(o, (Object[])idMethod.getParameterTypes()) != ""){
					if(idMethod.getReturnType() == int.class || idMethod.getReturnType()  == Integer.class  )pstmt.setInt(index++, (Integer)idMethod.invoke(o, (Object[])idMethod.getParameterTypes()));
					else if(idMethod.getReturnType()  == long.class || idMethod.getReturnType()  == Long.class  )pstmt.setLong(index++, (Long)idMethod.invoke(o, (Object[])idMethod.getParameterTypes()));
					else if(idMethod.getReturnType()  == String.class || idMethod.getReturnType()  == char.class  )pstmt.setString(index++, (String)idMethod.invoke(o, (Object[])idMethod.getParameterTypes()));
				}
				if(!map.isEmpty()){
				    for(Map.Entry<String,Method> entry:map.entrySet()){
				    	    if(entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()) != null && entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()) != ""){
				    	    	if(entry.getValue().getReturnType() == Byte.class || entry.getValue().getReturnType() == byte.class)pstmt.setByte(index++, (Byte)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));
						    	else if(entry.getValue().getReturnType() == Date.class){
						    		Date date = (Date)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes());
						    		pstmt.setTimestamp(index++, new java.sql.Timestamp(date.getTime()));
						    	}
						    	else if(entry.getValue().getReturnType() == Double.class || entry.getValue().getReturnType() == double.class)pstmt.setDouble(index++, (Double)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));
						    	else if(entry.getValue().getReturnType() == Float.class || entry.getValue().getReturnType() == float.class)pstmt.setFloat(index++, (Float)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));
						    	else if(entry.getValue().getReturnType() == Integer.class || entry.getValue().getReturnType() == int.class)pstmt.setInt(index++, (Integer)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));
						    	else if(entry.getValue().getReturnType() == Long.class || entry.getValue().getReturnType() == long.class)pstmt.setLong(index++, (Long)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));
						    	else if(entry.getValue().getReturnType() == Short.class || entry.getValue().getReturnType() == short.class)pstmt.setShort(index++, (Short)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));
						    	else if(entry.getValue().getReturnType() == String.class || entry.getValue().getReturnType() == char.class)pstmt.setString(index++, (String)entry.getValue().invoke(o, (Object[])entry.getValue().getParameterTypes()));	
				    	    }
				    }
			    }	
	        }
			resultSet = pstmt.executeQuery();
			ResultSetMetaData resMadata = resultSet.getMetaData();
			int count = resMadata.getColumnCount();
			map = DomainBeanUtil.getColumnSetMethod(T);
			if(resultSet.next()) {			
				idMethod = DomainBeanUtil.getIdSetMethod(T);
				idMethod.invoke(retunrObject, resultSet.getObject(idName));
				for(int i=1;i<=count;i++){
					if(map.get(resMadata.getColumnLabel(i)) != null){										
						map.get(resMadata.getColumnLabel(i)).invoke(retunrObject, resultSet.getObject(resMadata.getColumnLabel(i)));
					}
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new BasicException(e);
		}finally{
			try{
				if(resultSet != null){
					resultSet.close();
					resultSet = null;
				}
				if(pstmt != null){
			       pstmt.close();
			       pstmt = null;
				}
			}catch(Exception e){
				
			}
	    }
		
		return retunrObject;
	}

	@Override
	public List list(Class T,String sqlStr,Object[] param) throws BasicException {
		// TODO Auto-generated method stub
		List<Object> list = new ArrayList<Object>();
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try{
			if(StringUtils.isNotBlank(sqlStr)){
				pstmt = BasicSessionFactory.getInstance().getSession().getConnection().prepareStatement(sqlStr,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);			
				if(param != null && param.length >0){
					int index = 1;
					for(Object o:param){
			    	    	if(o.getClass() == Byte.class || o.getClass() == byte.class)pstmt.setByte(index, (Byte)param[index-1]);
					    	else if(o.getClass() == Date.class){
					    		Date date = (Date)param[index];
					    		pstmt.setTimestamp(index, new java.sql.Timestamp(date.getTime()));
					    	}
					    	else if(o.getClass() == Double.class || o.getClass() == double.class)pstmt.setDouble(index, (Double)param[index-1]);
					    	else if(o.getClass() == Float.class || o.getClass() == float.class)pstmt.setFloat(index, (Float)param[index-1]);
					    	else if(o.getClass() == Integer.class || o.getClass() == int.class)pstmt.setInt(index, (Integer)param[index-1]);
					    	else if(o.getClass() == Long.class || o.getClass() == long.class)pstmt.setLong(index, (Long)param[index-1]);
					    	else if(o.getClass() == Short.class || o.getClass() == short.class)pstmt.setShort(index, (Short)param[index-1]);
					    	else if(o.getClass() == String.class || o.getClass() == char.class)pstmt.setString(index, (String)param[index-1]);	
					        index++;
					}
				}
				resultSet = pstmt.executeQuery();
				ResultSetMetaData resMadata = resultSet.getMetaData();
				int count = resMadata.getColumnCount();
				Map<String,Method> map = DomainBeanUtil.getColumnSetMethod(T);
				Method idMethod = DomainBeanUtil.getIdSetMethod(T);
				String idName = DomainBeanUtil.getIdName(T);
				while(resultSet.next()) {	
					Object retunrObject = T.newInstance();
					idMethod.invoke(retunrObject, resultSet.getObject(idName));
					for(int i=1;i<=count;i++){
						if(map.get(resMadata.getColumnLabel(i)) != null){										
							map.get(resMadata.getColumnLabel(i)).invoke(retunrObject, resultSet.getObject(resMadata.getColumnLabel(i)));
						}
					}
					list.add(retunrObject);
				}
			}
		}catch(Exception e){
			throw new BasicException(e);
		}finally{
			try{
				if(resultSet != null){
					resultSet.close();
					resultSet = null;
				}
				if(pstmt != null){
			       pstmt.close();
			       pstmt = null;
				}
			}catch(Exception e){
				
			}
	    }
		return list;
	}
	
	@Override
	public int Count(String sqlStr, Object[] param) throws BasicException {
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try{
			if(StringUtils.isNotBlank(sqlStr)){
				pstmt = BasicSessionFactory.getInstance().getSession().getConnection().prepareStatement(sqlStr,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);			
				if(param != null && param.length >0){
					int index = 1;
					for(Object o:param){
			    	    	if(o.getClass() == Byte.class || o.getClass() == byte.class)pstmt.setByte(index, (Byte)param[index-1]);
					    	else if(o.getClass() == Date.class){
					    		Date date = (Date)param[index];
					    		pstmt.setTimestamp(index, new java.sql.Timestamp(date.getTime()));
					    	}
					    	else if(o.getClass() == Double.class || o.getClass() == double.class)pstmt.setDouble(index, (Double)param[index-1]);
					    	else if(o.getClass() == Float.class || o.getClass() == float.class)pstmt.setFloat(index, (Float)param[index-1]);
					    	else if(o.getClass() == Integer.class || o.getClass() == int.class)pstmt.setInt(index, (Integer)param[index-1]);
					    	else if(o.getClass() == Long.class || o.getClass() == long.class)pstmt.setLong(index, (Long)param[index-1]);
					    	else if(o.getClass() == Short.class || o.getClass() == short.class)pstmt.setShort(index, (Short)param[index-1]);
					    	else if(o.getClass() == String.class || o.getClass() == char.class)pstmt.setString(index, (String)param[index-1]);	
					        index++;
					}
				}
				resultSet = pstmt.executeQuery();
			    if(resultSet != null){
			    	resultSet.last();
			    	count = resultSet.getRow();
			    }	
			}
		}catch(Exception e){
			throw new BasicException(e);
		}finally{
				try{
					if(resultSet != null){
						resultSet.close();
						resultSet = null;
					}
					if(pstmt != null){
				       pstmt.close();
				       pstmt = null;
					}
				}catch(Exception e){
					
				}
		}
		return count;
	}
}
