package com.syj.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.syj.domain.Boarduser;

public class UserService {

	public Boarduser checkUser(Boarduser u){
		
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<Boarduser> list = session.createQuery("from Boarduser where name='"+u.getName()+"' and password='"+u.getPassword()+"'").getResultList();
		if(list.size()==1)
			u = list.get(0);
		else
			u = null;
		
		/*
		String sql = "select * from boarduser where name=? and password=?";
		String parameters[] = {u.getName(),u.getPassword()};
		ResultSet rs = SqlHelper.executeQuery(sql, parameters);
		try{
			if(rs.next()){
				b = true;
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}*/
		return u;
	}
}
