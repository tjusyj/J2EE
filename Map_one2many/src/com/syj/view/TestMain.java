package com.syj.view;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.syj.domain.Department;
import com.syj.domain.Student;
import com.syj.util.HibernateUtil;

public class TestMain {

	public static void main(String[] args) {
		
		//查找一个部门所有学生
			//1. 使用 m2o
			//2. 增加 set o2m
				//search_o2m_set();
		//配置 set 后,使用级联添加 
		//需要增加配置: <set name="students" cascade="save-update">
		cascade_add();
		
	}

	public static void cascade_add() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.openSession();
			tx = session.beginTransaction();
			
			Department department = new Department();
			department.setName("研发部");
			Student s1 = new Student();
			s1.setName("国王");
			Student s2 = new Student();
			s2.setName("浩南");
			Set<Student> set = new HashSet<Student>();
			set.add(s1);
			set.add(s2);
			department.setStudents(set);
			session.save(department);
			
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

	public static void search_o2m_set() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.openSession();
			tx = session.beginTransaction();
			
			Department department = session.get(Department.class, 1);
			Set<Student> students = department.getStudents();
			for(Student s:students)
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
}
