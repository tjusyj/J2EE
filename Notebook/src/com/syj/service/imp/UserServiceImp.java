package com.syj.service.imp;

import java.util.List;

import com.syj.domain.User;
import com.syj.service.inter.UserServiceInterface;
import com.syj.util.HibernateUtil;

public class UserServiceImp extends BaseServiceImp implements UserServiceInterface{

	public User checkUser(User u){
		
		String hql = "from User where username=? and password=?";
		String parameters[] = {u.getUsername(),u.getPassword()};
		@SuppressWarnings("unchecked")
		List<User> list = HibernateUtil.executeQueryOpenInView(hql, parameters);
		if(list.size()==1)
			return list.get(0);
		else
			return null;
	}
	
	public List<User> getUsers(){
		String hql = "from User";
		@SuppressWarnings("unchecked")
		List<User> list = HibernateUtil.executeQueryOpenInView(hql,null);
		return list;
	}
}
