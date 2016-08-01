package com.syj.view;

import java.util.List;

import com.syj.domain.Schoolcourse;
import com.syj.domain.Schoolstudent;
import com.syj.util.HibernateUtil;

public class TestUtilHQL {
	public static void main(String[] args) {
		//testQueryUtil();
		//viewByPage();
		//saveUtil();
		//updateUtil(); & delete
		String hql = "select schoolstudent.sname,schoolcourse.cname from Schoolchoice where schoolcourse.cid=21";
		List list = HibernateUtil.executeQuery(hql, null);
		for (int i=0; i<list.size(); i++){
			Object[] objs = (Object[]) list.get(i);
			System.out.println(objs[0].toString()+objs[1].toString());
		}
	}

	public static void updateUtil() {
		String hql = "update Schoolstudent set sage=sage+1 where sdept=?";
		String[] parameters = {"计算机系"};
		HibernateUtil.update(hql, parameters);
	}

	public static void saveUtil() {
		Schoolcourse sc =new Schoolcourse();
		sc.setCid((byte)61);
		sc.setCname("Servlet");
		sc.setCcredit((byte)4);
		HibernateUtil.save(sc);
	}

	public static void viewByPage() {
		int rowNum = 0;
		int pageSize = 2;
		String hql = "from Schoolstudent";
		rowNum = HibernateUtil.executeQuery(hql, null).size();
		for(int i=0;i<((rowNum-1)/pageSize+1);i++){
			List<Schoolstudent> list = HibernateUtil.executeQueryByPage(hql, null, i+1, pageSize);
			System.out.println("********************第"+(i+1)+"页*******************");
			for(Schoolstudent s : list){
				System.out.println(s.getSname()+s.getSdept());
			}
		}
	}

	public static void testQueryUtil() {
		List<Schoolstudent> list = HibernateUtil.executeQuery("from Schoolstudent where sdept=? and sage>?", new String[] {"计算机系","byte:20"});
		for(Schoolstudent s : list){
			System.out.println(s.getSname()+s.getSdept());
		}
	}
}
