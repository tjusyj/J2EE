package com.syj.view;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.syj.domain.Employee;
import com.syj.util.HibernateUtil;
import com.syj.util.MySessionFactory;


public class TestMain {

	public static void main(String[] args) {
		//addEmployee();
		//updateEmployee();
		//deleteEmployee();
		//updateEmployeeRollback();
		//queryHQL();
		//criteriaHQL();
		
		//Session session = HibernateUtil.getCurrentSession();
			
	}

	public static void criteriaHQL() {
		Session session = HibernateUtil.getCurrentSession();
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Employee.class).setMaxResults(4);
		@SuppressWarnings("unchecked")
		List<Employee> list = criteria.add(Restrictions.like("name", "%syj")).list();
		for(Employee e : list){
			System.out.println(e.getName()+" "+e.getEmail());
		}
	}

	public static void queryHQL() {
		Session session = HibernateUtil.getCurrentSession();
		Transaction tranSanction = null;
		try {
			tranSanction = session.beginTransaction();
			//HQL 操作的是对象 - 是 Employee 而非表名 employee
			@SuppressWarnings("unchecked")
			Query<Employee> query = session.createQuery("from Employee where name='Elcy729'");
			List<Employee> list = query.getResultList();
			for(Employee e : list){
				System.out.println(e.getName()+" "+e.getEmail());
			}
			tranSanction.commit();
		} catch (Exception e) {
			if(tranSanction != null){
				tranSanction.rollback();
			}
			throw new RuntimeException(e.getMessage());
		}finally {
			if(session != null && session.isOpen())
			session.close();
		}
	}

	public static void updateEmployeeRollback() {
		Session session = MySessionFactory.getSessionFactory().openSession();
		Transaction tranSanction = null;
		try {
			tranSanction = session.beginTransaction();
			Employee e = session.load(Employee.class, 2);
			e.setName("Elcy_rollback");
			//error
			//int i = 9/0;
			
			tranSanction.commit();
		} catch (Exception e) {
			if(tranSanction != null){
				tranSanction.rollback();
			}
			throw new RuntimeException(e.getMessage());
		}finally {
			if(session != null && session.isOpen())
			session.close();
		}
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
