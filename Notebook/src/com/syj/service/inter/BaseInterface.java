package com.syj.service.inter;

import java.io.Serializable;

public interface BaseInterface {

	//通用方法声明
	//常用的基本数据类型String,Integer,Boolean...包装类都实现了 Serializable 类
	public Object findById(Class clazz,Serializable id);
}
