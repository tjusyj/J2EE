package com.syj.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.syj.util.HibernateUtil;

public class ServletFilter extends HttpServlet implements Filter {
	private static final long serialVersionUID = 1L;
       
    public ServletFilter() {
        super();
    }

    public void init(FilterConfig arg0) throws javax.servlet.ServletException { 
         // TODO Auto-generated method stub
    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws java.io.IOException, javax.servlet.ServletException { 
    	// 获取线程绑定session
    	Session session = null;
    	Transaction tx = null;
    	try {
    		session = HibernateUtil.getCurrentSession();
    		tx = session.beginTransaction();
    		//////////////////////////////////////////////////////////////////
        	arg2.doFilter(arg0, arg1);///////doFilter start the request &  ///
        	/////////////////////////////////finish until request send back///
        	//////////////////////////////////////////////////////////////////
        	tx.commit();
		} catch (Exception e) {
			if( tx != null)
				tx.rollback();
			throw new RuntimeException(e.getMessage());
		} finally {
			HibernateUtil.closeCurrentSession();
		}
    	
    }

}
