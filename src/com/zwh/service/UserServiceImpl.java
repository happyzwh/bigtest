package com.zwh.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zwh.aop.JtaTransactionAnno;
import com.zwh.dao.UserDao;
import com.zwh.domain.Test;
import com.zwh.domain.User;
import com.zwh.exception.BasicException;
@Service("userService")
public class UserServiceImpl implements UserService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7435751458001876180L;
	
	private UserDao userDao;
	
	private TestService testService;

	@Override
	@JtaTransactionAnno
	public int save(User user) throws BasicException {
		// TODO Auto-generated method stub
		int code = 0;
		try{
			code = this.userDao.save(user);
			int i = 0;
			 i = 20/i;
		}catch(Exception e){
			throw new BasicException(e);
		}
		return code;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
	@Autowired
	public void setTestService(TestService testService) {
		this.testService = testService;
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
		 
		 this.save(user);
		 
//		 int i = 0;
//		 i = 20/i;
		 
		 Test test = new Test();
		 test.setName("zwh");
		 test.setDes("zhangwenhui");
		 test.setAge(31);
		 test.setCreatetime(new Date());
		 test.setUpdatetime(test.getCreatetime());
		 
		 testService.save(test);

		
	}
	
	

}
