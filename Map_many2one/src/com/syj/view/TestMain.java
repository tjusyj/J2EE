package com.syj.view;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.syj.domain.Department;
import com.syj.domain.Student;
import com.syj.util.HibernateUtil;

public class TestMain {

	public static void main(String[] args) {
		
		//insertData();
		//lazyProblem();
		
		//查找一个部门所有学生
		//1. 使用 m2o
		//2. 增加 set o2m
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.openSession();
			tx = session.beginTransaction();
			
			String hql = "from Student where department.id=1";
			@SuppressWarnings("unchecked")
			List<Student> list = HibernateUtil.executeQuery(hql, null);
			for(Student s:list)
				System.out.println(s.getName()+s.getDepartment().getName());
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
		
	}

	public static void lazyProblem() {
		/*
		 * 懒加载问题
		 * 1. 代理初始化
		 * 	Hibernate.initialize(student.getDepartment());
		 * 2. 关闭懒加载
		 * 	<class name="Department" lazy="true">
		 * 3. fetch type 由 select 改为 join
		 * 	<many-to-one name="department" class="Department" fetch="join">
		 */
		Student student = getStudent();
		System.out.println(student.getName());
		System.out.println(student.getName()+student.getDepartment().getName());
	}

	public static Student getStudent() {
		Session session = null;
		Transaction tx = null;
		Student student = null;
		try {
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			
			student = session.get(Student.class,1);
			Hibernate.initialize(student.getDepartment());
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
		return student;
	}

	public static void insertData() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			
			Department department = new Department();
			department.setName("IT部");
			Student student = new Student();
			student.setName("沈元君");
			student.setDepartment(department);
			session.save(department);
			session.save(student);
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
	}

}
