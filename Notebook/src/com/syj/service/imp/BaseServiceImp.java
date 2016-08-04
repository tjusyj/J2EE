package com.syj.service.imp;

import java.io.Serializable;

import com.syj.service.inter.BaseInterface;
import com.syj.util.HibernateUtil;

public abstract class BaseServiceImp implements BaseInterface{

	@Override
	public Object findById(Class clazz, Serializable id) {
		return HibernateUtil.findById(clazz, id);
	}

}
