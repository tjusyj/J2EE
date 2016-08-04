package com.syj.util;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public final class HibernateUtil {

	private static SessionFactory sessionFactory = null;
	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	
	private HibernateUtil(){};
	
	static{
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}
	
	public static Session openSession(){
		return sessionFactory.openSession();
	}
	
	public static Session getCurrentSession(){
		Session session = threadLocal.get();
		if(session == null){
			session = sessionFactory.openSession();
			threadLocal.set(session);
		}
		return session;
	}
	
public static void closeCurrentSession(){
		
		Session s=getCurrentSession();
		
		if(s!=null&& s.isOpen() ){
			s.close();
			threadLocal.set(null);
		}
	}
	
	//这里提供一个根据id返回对象的方法
	public static Object findById(Class clazz,java.io.Serializable id){

		Session s=null;
		Transaction tx=null;
		Object obj=null;
		try {
			s=openSession();
			
			tx=s.beginTransaction();
			obj=s.load(clazz, id);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		}finally{
			
			if(s!=null&&s.isOpen()){
				s.close();
			}
			
		}
		
		return obj;
	}
	
	public static void save(Object obj){
		Session session = null;
		Transaction tx = null;
		try {
			session = openSession();
			tx = session.beginTransaction();
			session.save(obj);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
	}
	
	public static void update(String hql, String[] parameters){
		Session session = null;
		Transaction tx = null;
		Query query = null;
		try {
			session = openSession();
			tx = session.beginTransaction();
			query = session.createQuery(hql);
			if(parameters != null && parameters.length > 0)
				for(int i=0;i<parameters.length;i++){
					if(parameters[i].startsWith("byte"))
						query.setParameter(i, Byte.parseByte(parameters[i].substring(5)));
					else
						query.setParameter(i, parameters[i]);
				}
			int i = query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
	}
	
	public static List executeQueryByPage(String hql, String[] parameters, int pageNow, int pageSize){
		Session session = null;
		Transaction tx = null;
		List list = null;
		Query query = null;
		try {
			session = openSession();
			tx = session.beginTransaction();
			query = session.createQuery(hql);
			if(parameters != null && parameters.length > 0)
				for(int i=0;i<parameters.length;i++){
					if(parameters[i].startsWith("byte"))
						query.setParameter(i, Byte.parseByte(parameters[i].substring(5)));
					else
						query.setParameter(i, parameters[i]);
				}
			query.setFirstResult((pageNow-1)*pageSize);
			query.setMaxResults(pageSize);
			list = query.getResultList();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
		return list;
	}
	
	public static List executeQuery(String hql, String[] parameters){
		Session session = null;
		Transaction tx = null;
		List list = null;
		Query query = null;
		try {
			session = openSession();
			tx = session.beginTransaction();
			query = session.createQuery(hql);
			if(parameters != null && parameters.length > 0)
				for(int i=0;i<parameters.length;i++){
					if(parameters[i].startsWith("byte"))
						query.setParameter(i, Byte.parseByte(parameters[i].substring(5)));
					else if(parameters[i].startsWith("integer"))
						query.setParameter(i, Integer.parseInt(parameters[i].substring(8)));
					else
						query.setParameter(i, parameters[i]);
				}
			list = query.getResultList();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			if(session !=null && session.isOpen()){
				session.close();
			}
		}
		return list;
	}
	
}
