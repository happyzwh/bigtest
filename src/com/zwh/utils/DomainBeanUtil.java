package com.zwh.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zwh.annotation.DataBase;
import com.zwh.annotation.ExtraColumn;
import com.zwh.exception.BasicException;

public class DomainBeanUtil {	
	
	public static String getTableName(Class<?> T) throws BasicException{
		String tableName = null;
		if(T != null && T.isAnnotationPresent(Table.class)){
			Table table =  (Table)T.getAnnotation(Table.class);
			if(table != null)tableName = table.name();
		}
		return tableName;
	}
	public static String getDbName(Class<?> T) throws BasicException{
		String dbName = null;
		if(T != null && T.isAnnotationPresent(DataBase.class)){
			DataBase db =  (DataBase)T.getAnnotation(DataBase.class);
			if(db != null)dbName = db.name();
		}
		return dbName;
	}
	public static String getIdName(Class<?> T) throws BasicException{
		String idName = null;
		 if(T != null){
			 Method[] methods = T.getDeclaredMethods();
			 if(methods != null && methods.length > 0){
				 for(Method method:methods){
					 if(method.isAnnotationPresent(Id.class)){
						 Column c = method.getAnnotation(Column.class);
						 if(c != null)idName =  c.name();
						 break;
					 }
				 }
			 }
		 }
		 return idName;
	}
	public static Method getIdGetMethod(Class<?> T) throws BasicException{
		 Method meth = null;
		 if(T != null){
			 Method[] methods = T.getDeclaredMethods();
			 if(methods != null && methods.length > 0){
				 for(Method method:methods){
					 if(method.isAnnotationPresent(Id.class)){
							meth =  method;
							break;
					 }
				 }
			 }
		 }
		 return meth;
	}
	public static Method getIdSetMethod(Class<?> T) throws BasicException, SecurityException, NoSuchMethodException{
		Method methretu = null; 
		Method meth = DomainBeanUtil.getIdGetMethod(T);
		if(meth != null){
			String methodName = meth.getName();
			if(methodName.indexOf("get") == 0){
				methretu = T.getDeclaredMethod("set"+methodName.substring(3, methodName.length()), meth.getReturnType());
			}else if(methodName.indexOf("is") == 0){
				methretu = T.getDeclaredMethod("set"+methodName.substring(2, methodName.length()), meth.getReturnType());
			}
		} 
		return methretu;
	}
	public static Map<String,Method> getColumnMethod(Class<?> T) throws BasicException{
		 Map<String,Method> map = new HashMap<String,Method>();
		 if(T != null){
			 Method[] methods = T.getDeclaredMethods();
			 if(methods != null && methods.length > 0){
				 for(Method method:methods){
					 if(method.isAnnotationPresent(Column.class) && !method.isAnnotationPresent(Id.class)){
							 Column c = method.getAnnotation(Column.class);
							 if(c != null)map.put(c.name(), method);
					 }
				 }
			 }
		 }
		 return map;
	}
	public static Map<String,Method> getColumnGetMethod(Class<?> T) throws BasicException, SecurityException, NoSuchMethodException{
		Map<String,Method> mapretu = new HashMap<String,Method>();
		 Map<String,Method> map = DomainBeanUtil.getColumnMethod(T);
		 if(!map.isEmpty()){
			 for(Map.Entry<String,Method> entry:map.entrySet()){
				if(entry.getValue().getAnnotation(ExtraColumn.class) != null)continue;
				Method meth =  entry.getValue(); 
				if(meth != null){
					String methodName = meth.getName();
					Method methretu = null;
					if(methodName.indexOf("get") != 0){
						if(methodName.indexOf("set") == 0){
							methretu = T.getDeclaredMethod("get"+methodName.substring(3, methodName.length()), meth.getReturnType());
						}else if(methodName.indexOf("is") == 0){
							methretu = T.getDeclaredMethod("get"+methodName.substring(2, methodName.length()), meth.getReturnType());
						}
					}else methretu = entry.getValue();
					mapretu.put(entry.getKey(), methretu);
				} 
			 }
		 }
		return mapretu;
	}
	public static Map<String,Method> getColumnSetMethod(Class<?> T) throws BasicException, SecurityException, NoSuchMethodException{
		 Map<String,Method> mapretu = new HashMap<String,Method>();
		 Map<String,Method> map = DomainBeanUtil.getColumnMethod(T);
		 if(!map.isEmpty()){
			 for(Map.Entry<String,Method> entry:map.entrySet()){
				Method meth =  entry.getValue(); 
				if(meth != null){
					String methodName = meth.getName();
					Method methretu = null;
					if(methodName.indexOf("set") != 0){
						if(methodName.indexOf("get") == 0){
							methretu = T.getDeclaredMethod("set"+methodName.substring(3, methodName.length()), meth.getReturnType());
						}else if(methodName.indexOf("is") == 0){
							methretu = T.getDeclaredMethod("set"+methodName.substring(2, methodName.length()), meth.getReturnType());
						}
					}else methretu = entry.getValue();
					mapretu.put(entry.getKey(), methretu);
				} 
			 }
		 }
		return mapretu;
	}
	public static List<String> getColumn(Class<?> T) throws BasicException, SecurityException, NoSuchMethodException{
		 List<String> list = new ArrayList<String>();
		 if(T != null){
			 Method[] methods = T.getDeclaredMethods();
			 if(methods != null && methods.length > 0){
				 for(Method method:methods){
					 if(method.isAnnotationPresent(Column.class) && !method.isAnnotationPresent(Id.class)){
						 if(method.getName().indexOf("is")== 0 || method.getName().indexOf("get") == 0){
							 Column c = method.getAnnotation(Column.class);
							 if(c != null)list.add(c.name());
						 }
					 }
				 }
			 }
		 }
		return list;
	}
	public static Object getPropertyValue(Method method,Object o) throws BasicException{
		Object value = null;
		try{
		  value = method.invoke(o, (Object[])method.getParameterTypes());
		}catch(Exception e){
			throw new BasicException(e);
		}
		return value;
	}
}
