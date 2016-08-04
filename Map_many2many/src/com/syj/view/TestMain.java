package com.syj.view;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.syj.domain.Course;
import com.syj.domain.Choose;
import com.syj.domain.Student;
import com.syj.util.HibernateUtil;

public class TestMain {

	public static void main(String[] args) {
		
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtil.getCurrentSession();
			tx=s.beginTransaction();
			
			Student student = new Student();
			student.setName("沈元君");
			Course course = new Course();
			course.setName("C++");
			Choose choice = new Choose();
			choice.setStudent(student);
			choice.setCourse(course);
			choice.setGrade(99);
			
			s.save(student);
			s.save(course);
			s.save(choice);
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			if (s != null && s.isOpen())
				s.close();
		}
	}

}
