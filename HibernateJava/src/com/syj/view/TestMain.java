package com.syj.view;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.syj.domain.Employee;
import com.syj.util.MySessionFactory;

public class TestMain {

	public static void main(String[] args) {
		//addEmployee();
		//updateEmployee();
		//deleteEmployee();

	}

	public static void deleteEmployee() {
		Session session = MySessionFactory.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Employee e = session.load(Employee.class, 4);
		session.delete(e);
		transaction.commit();
		session.close();
	}

	public static void updateEmployee() {
		Session session = MySessionFactory.getSessionFactory().openSession();
		Transaction tranSanction = session.beginTransaction();
		Employee e = session.load(Employee.class, 2);
		e.setName("Elcy729");
		session.save(e);
		tranSanction.commit();
		session.close();
	}

	public static void addEmployee() {
		//Use Hibernate CRUD
		//1.Create Configuration - read cfg.xml & initialization
		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		//2.SessionFactory
		SessionFactory sessionFactory = config.buildSessionFactory();
		//3.Session
		Session session = sessionFactory.openSession();
		//4.Transaction - Hibernate must use Transaction
		Transaction tranSanction = session.beginTransaction();
		//4.add Emplyee
		Employee e = new Employee();
		e.setName("syj");
		e.setEmail("syj@qq.com");
		e.setHiredate(new Date());
		//5.insert & save
		session.save(e);
		//6.Transaction Commit.
		tranSanction.commit();
		session.close();
	}

}
