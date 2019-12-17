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

import com.zwh.sessionfactory.BasicSessionFactory;
import com.zwh.transaction.Transaction;
@Aspect  
@Component 
public class TransactionAspect {
	
	private final static Log logger = LogFactory.getLog(TransactionAspect.class); 
	 /** 
     * 定义一个切入点,在方法含@Anno注解位置切入 
     */  
    @Pointcut("@annotation(com.zwh.aop.TransactionAnno)")  
//     @Pointcut("execution(* com.zwh.service.*(..))")  
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
        
//        Method method = getMethod(jp);  
//        TransactionAnno anno = method.getAnnotation(TransactionAnno.class);  
//        String methodName = method.getName();  
        Transaction transaction = null;
        try{
        	transaction = BasicSessionFactory.getInstance().getSession().getTransaction();
	        if(transaction != null) {
	        	transaction.setCount(transaction.getCount()+1);
	        	if(!transaction.isBegin() || !transaction.isActive()){
	        	    transaction.begin();
                }
	        }
	        	
	          
	        retVal = jp.proceed();  
	       
	        if(transaction != null) {
		        transaction.setCount(transaction.getCount()-1);
		        if(transaction.getCount()==0 && transaction.isActive()) {
		            transaction.commit();
                }
	        }
	       
        }catch(Exception e){
        	if(transaction != null && transaction.isActive()){
        		 transaction.setCount(transaction.getCount()-1);
        		 transaction.rollback();
        	}
        	e.printStackTrace();
        }finally{
        	 
//			        	   BasicSessionFactory.getInstance().getSession().close();

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
