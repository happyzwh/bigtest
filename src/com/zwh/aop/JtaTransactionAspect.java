package com.zwh.aop;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.zwh.sessionfactory.BasicJtaSessionFactory;
import com.zwh.transaction.JtaTransaction;

@Aspect  
@Component 
public class JtaTransactionAspect {
	private final static Log logger = LogFactory.getLog(TransactionAspect.class); 
	 /** 
    * 定义一个切入点,在方法含@Anno注解位置切入 
    */  
   @Pointcut("@annotation(com.zwh.aop.JtaTransactionAnno)")  
//    @Pointcut("execution(* com.zwh.service.*(..))")  
   public void aopPoint(){  
   
   }  
 
   /** 
    * @param jp 
    * @return 
    */  
   @Around("aopPoint()")  
   public Object execAop(ProceedingJoinPoint jp) throws Throwable{  
       Object retVal = null;
       long start = System.currentTimeMillis();  
       JtaTransaction jtaTransaction = null;
       try{
    	   jtaTransaction = BasicJtaSessionFactory.getInstance().getJtaTransaction();
	       if(jtaTransaction != null){ 
	        	jtaTransaction.setCount(jtaTransaction.getCount()+1);
	        	if(!jtaTransaction.isBegin() || !jtaTransaction.isActive()){
	        	    jtaTransaction.begin();
                }
	       }
	          
	       retVal = jp.proceed();  
	        
	       if(jtaTransaction != null){
		       jtaTransaction.setCount(jtaTransaction.getCount()-1);
		       if(jtaTransaction.getCount() == 0 && jtaTransaction.isActive()){
		           jtaTransaction.commit();
               }
	       }
	       
       }catch(Exception e){
	       	if(jtaTransaction != null && jtaTransaction.isActive()){ 
	       	    jtaTransaction.setCount(jtaTransaction.getCount()-1);
	       		jtaTransaction.rollback();
	       	}
       }finally{
//    	   if(jtaTransaction != null && !jtaTransaction.getSessionProxy().isClose()){
//		       	 if(jtaTransaction.getCount() == 0 && jtaTransaction.isBegin()) 
//		       		 jtaTransaction.getSessionProxy().close();
//    	   }
       	   if (logger.isInfoEnabled()) {  
                  logger.info("spend time : ---" + (System.currentTimeMillis()-start) + "ms");  
              } 
       }
    
       return retVal;  
   }  
 
   @SuppressWarnings("unused")
	private Method getMethod(JoinPoint jp) throws NoSuchMethodException  
   {  
       Signature sig = jp.getSignature();  
       MethodSignature msig = (MethodSignature) sig;  
       return getClass(jp).getMethod(msig.getName(), msig.getParameterTypes());  
   }  
     
   private Class<? extends Object> getClass(JoinPoint jp) throws NoSuchMethodException{  
       return jp.getTarget().getClass();  
   }  
}
