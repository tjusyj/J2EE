package com.syj.view;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.syj.domain.Schoolstudent;
import com.syj.util.HibernateUtil;

public class TestHQL {

	public static void main(String[] args) {

		int pageSize = 2;
		/*
		 * 当只取一列数据 "select sname from Schoolstudent where sdept='计算机系'"
		 * 此时 list 直接存放 object 而非 object[] 
		 * 查看 queryAggregate()
		 */
		
		//queryAll();		// 1.检索全部属性 所有学生信息
		//queryPart();		// 2.检索部分属性 
		//singleResult();	// 3.single result	
		//queryDistinct();	// 4.distinct & between
		//queryIn();		// 5.in & not in
		//queryGroup();		// 6.group by & Aggregate 使用聚集函数必须分组
		//queryHaving();	// 7.having
		//queryAggregate();	// 8.all kinds of
		//getResultByPage(pageSize);	//分页
		//dataBinding();	//数据绑定
		
	}

	public static void dataBinding() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// 4.distinct 过滤结果 滤除重复项
			@SuppressWarnings("unchecked")
			Query<Schoolstudent> query = session.createQuery("from Schoolstudent where sdept= :sdept and sage> :sage");
			query.setParameter("sdept", "计算机系");
			query.setParameter("sage", Byte.parseByte(20+""));
			List<Schoolstudent> list = query.getResultList();
			for(Schoolstudent stu : list){
				System.out.println(stu.getSname()+stu.getSdept());
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
	}

	public static void getResultByPage(int pageSize) {
		int pageNow = 0;
		int pageNum = 0;
		int rowNum = 0;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			@SuppressWarnings("unchecked")
			Query<Schoolstudent> query = session.createQuery("from Schoolstudent");
			
			rowNum = query.getResultList().size();
			pageNum = (int) Math.ceil(rowNum / pageSize);
			System.out.println(rowNum+"-"+pageNum);
			
			for(int i=0;i<pageNum;i++){
				System.out.println("****************第"+(i+1)+"页*******************");
				List<Schoolstudent> list = query.setFirstResult(pageNow).setMaxResults(pageSize).getResultList();
				for(Schoolstudent student : list){
					System.out.println(student.getSname()+student.getSdept()+student.getSaddress());
				}
				pageNow += pageSize;
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
	}

	public static void queryAggregate() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// 4.distinct 过滤结果 滤除重复项
			
			System.out.println("每个系人数：");
			List list = session.createQuery("select count(*) from Schoolstudent group by sdept").getResultList();
			for(Object o : list){
				System.out.println(o.toString());
			}
			
			System.out.println("JAVA课程最高分最低分：");
			list = session.createQuery("select 11,max(grade),min(grade) from Schoolchoice where schoolcourse.cid=11").getResultList();
			for(int i=0;i<list.size();i++){
				Object[] objs = (Object[]) list.get(i);
				System.out.println(objs[0].toString()+"-"+objs[1].toString()+"-"+objs[2].toString());
			}
			
			System.out.println("各科不及格学生：");
			list = session.createQuery("select schoolstudent.sname,schoolcourse.cname,grade from Schoolchoice where grade < 60").getResultList();
			for(int i=0;i<list.size();i++){
				Object[] objs = (Object[]) list.get(i);
				System.out.println(objs[0].toString()+"-"+objs[1].toString()+"-"+objs[2].toString());
			}
			
			System.out.println("各科不及格学生数量：");
			list = session.createQuery("select count(*),schoolcourse.cname from Schoolchoice where grade < 60 group by schoolcourse.cname").getResultList();
			for(int i=0;i<list.size();i++){
				Object[] objs = (Object[]) list.get(i);
				System.out.println(objs[0].toString()+"-"+objs[1].toString());
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
	}

	public static void queryHaving() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// 4.distinct 过滤结果 滤除重复项
			List list = session.createQuery("select count(*),sdept from Schoolstudent where ssex='F' group by sdept having count(*) < 200").getResultList();
			for(int i=0;i<list.size();i++){
				Object[] objs = (Object[]) list.get(i);
				System.out.println(objs[0].toString()+objs[1].toString());
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
	}

	public static void queryGroup() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// 4.distinct 过滤结果 滤除重复项
			List list = session.createQuery("select avg(sage),sdept from Schoolstudent group by sdept").getResultList();
			for(int i=0;i<list.size();i++){
				Object[] objs = (Object[]) list.get(i);
				System.out.println(objs[0].toString()+objs[1].toString());
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
	}

	public static void queryIn() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// 4.distinct 过滤结果 滤除重复项
			List list = session.createQuery("select sage,ssex,sname from Schoolstudent where "
					+ "sdept in ('计算机系','外语系')").getResultList();
			for(int i=0;i<list.size();i++){
				Object[] objs = (Object[]) list.get(i);
				System.out.println(objs[0].toString()+objs[1].toString()+objs[2].toString());
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
	}

	public static void queryDistinct() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// 4.distinct 过滤结果 滤除重复项
			List list = session.createQuery("select distinct sage,ssex,sname from Schoolstudent where "
					+ "sage between 20 and 22").getResultList();
			for(int i=0;i<list.size();i++){
				Object[] objs = (Object[]) list.get(i);
				System.out.println(objs[0].toString()+objs[1].toString()+objs[2].toString());
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
	}

	public static void singleResult() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// 3.uniqueResult 旧方法过时 使用getSingleResult()
			Schoolstudent student = (Schoolstudent) session.createQuery("from Schoolstudent where sid='20050003'").getSingleResult();
			if(student != null){
				System.out.println(student.getSid()+student.getSname()+student.getSdept());
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
	}

	public static void queryPart() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// 2.检索部分属性 
			List list = session.createQuery("select sname,sdept from Schoolstudent").getResultList();
			//由于只取出来了部分属性, 不足以构成一个完整的 Schoolstudent 对象
			
			// for Enhancement
			for(int i = 0 ; i <list.size() ; i++){
				Object [] objs = (Object []) list.get(i);
				System.out.println(objs[0].toString()+objs[1].toString());
			}
			System.out.println("-----------------------------");
			// Iterator
			Iterator iterator = list.iterator();
			while (iterator.hasNext()) {
				Object[] objs =(Object[]) iterator.next();
				System.out.println(objs[0].toString()+objs[1].toString());
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
	}

	public static void queryAll() {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getCurrentSession();
			tx = session.beginTransaction();
			// 1.检索全部属性 所有学生信息
			@SuppressWarnings("unchecked")
			List<Schoolstudent> list = session.createQuery("from Schoolstudent").getResultList();
			// for Enhancement
			for(Schoolstudent s : list){
				System.out.println(s.getSname()+s.getSaddress());
			}
			System.out.println("-----------------------------");
			// Iterator
			Iterator<Schoolstudent> iterator = list.iterator();
			while (iterator.hasNext()) {
				Schoolstudent s =iterator.next();
				System.out.println(s.getSname()+s.getSaddress());
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			if(session != null && session.isOpen()){
				session.close();
			}
		}
	}

}
