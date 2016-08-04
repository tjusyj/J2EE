package com.syj.view;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.syj.domain.Idcard;
import com.syj.domain.Person;
import com.syj.util.HibernateUtil;

public class TestMain {

	public static void main(String[] args) {
		
		/*
		 * o2o
		 * 基于主键
		 * 基于外键 需要加 unique many-2-one的特例
		 */
		
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtil.getCurrentSession();
			tx=s.beginTransaction();
			
			Person p = new Person();
			Idcard idcard = new Idcard();
			p.setId(1);
			p.setName("元君");
			idcard.setValidate(new Date());
			idcard.setPerson(p);
			s.save(p);
			s.save(idcard);
			
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
