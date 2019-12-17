package com.zwh.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.zwh.domain.User;
import com.zwh.exception.BasicException;
@Repository("userDao")
public class UserDaoImpl extends BaseJtaDaoImpl<User> implements UserDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2192951978166484392L;

	@Override
	public int count(String sqlStr, Object[] param) throws BasicException {
		// TODO Auto-generated method stub
		return this.Count(sqlStr, param);
	}

	@Override
	public int delete(Object o) throws BasicException {
		// TODO Auto-generated method stub
		return this.delete(User.class, o);
	}

	@Override
	public User get(Object o) throws BasicException {
		// TODO Auto-generated method stub
		return (User)this.get(User.class, o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> list(String sqlStr, Object[] param) throws BasicException {
		// TODO Auto-generated method stub
		return this.list(User.class, sqlStr, param);
	}

	@Override
	public int save(User user) throws BasicException {
		// TODO Auto-generated method stub
		int code = 0;
		try{
			code = this.save(User.class, user);
		}catch(Exception e){
			throw new BasicException(e);
		}
		return code;
	}

	@Override
	public int update(User user) throws BasicException {
		// TODO Auto-generated method stub
		return this.update(user);
	}

	
	
}
