package com.zwh.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zwh.aop.JtaTransactionAnno;
import com.zwh.domain.Test;
import com.zwh.domain.User;
import com.zwh.exception.BasicException;
@Service("teService")
public class TeServiceImpl implements TeService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7435751458001876180L;
	
	
	private TestService testService;
	
	private UserService userService;

	
	@Autowired
	public void setTestService(TestService testService) {
		this.testService = testService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	@JtaTransactionAnno
	public void test() throws BasicException {
		// TODO Auto-generated method stub
		
		
		 User user = new User();
		 user.setUsername("zwh");
		 user.setPassword("zwh");
		 user.setOther("other");
		 user.setCtime(new Date());
		 try{
		  userService.save(user);
		 }catch(Exception e){
//			 e.printStackTrace();
		 }
		 
		 
		 Test test = new Test();
		 test.setName("zwh");
		 test.setDes("zhangwenhui");
		 test.setAge(31);
		 test.setCreatetime(new Date());
		 test.setUpdatetime(test.getCreatetime());
//		 try{
		    testService.save(test);
//		 }catch(Exception e){
//			 e.printStackTrace();
//		 }
		
			 
			 int i = 0;
			 i = 20/i;	   
		    
		 
		 user.setUsername("zwh");
		 user.setPassword("zwh");
		 user.setOther("other");
		 user.setCtime(new Date());
		 try{
		  userService.save(user);
		 }catch(Exception e){
//			 e.printStackTrace();
		 }
		 
		  
		  


		 test.setName("zwh");
		 test.setDes("zhangwenhui");
		 test.setAge(31);
		 test.setCreatetime(new Date());
		 test.setUpdatetime(test.getCreatetime());
		 
		 testService.save(test);
		 
		 
		 

		

		
	}
	
	
	

}
