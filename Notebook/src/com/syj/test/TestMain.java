package com.syj.test;

import com.syj.util.Tools;

public class TestMain {

	public static void main(String[] args) {
		//HibernateUtil.openSession();

		String code = Tools.MD5("123");
		System.out.println(code);
	}

}
