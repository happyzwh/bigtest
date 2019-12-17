package com.zwh.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zwh.service.TeService;
import com.zwh.sessionfactory.BasicJtaSessionFactory;
import com.zwh.transaction.JtaTransaction;

public class JtaTest {

	public static void main(String[] args){
		
			 ClassPathXmlApplicationContext beanApp = new ClassPathXmlApplicationContext("applicationContext.xml");
			 TeService teService =  (TeService)beanApp.getBean("teService");
		
			 try{
				 teService.test();
			 }catch(Exception e){
			 }
			 
			 System.out.println("---------------------");
			 
			 try{
				 teService.test();
			 }catch(Exception e){
			 }
		     try{
				 JtaTransaction jtaTransaction = BasicJtaSessionFactory.getInstance().getJtaTransaction();
				 if(jtaTransaction != null && jtaTransaction.getSessionProxy() != null){
			       	 jtaTransaction.getSessionProxy().close();
			       	System.out.println("---------------------close");
			     }
			 }catch(Exception e){
			 }
		
	}
	
}
