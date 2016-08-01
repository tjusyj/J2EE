package com.syj.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//final 类不能继承
//一个数据库对应一个 SessionFactory
public final class MySessionFactory {
	
	//静态变量 内存中只有一份
	private static SessionFactory sessionFactory = null;
	
	private MySessionFactory(){
		
	}
	//静态块 只执行一次
	static {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}
	//静态方法 通过类名直接调用
	//静态方法 只能调用静态变量和静态方法
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
