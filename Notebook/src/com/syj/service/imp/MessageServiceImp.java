package com.syj.service.imp;

import java.util.List;

import com.syj.domain.Message;
import com.syj.domain.User;
import com.syj.service.inter.MessageServiceInterface;
import com.syj.util.HibernateUtil;

public class MessageServiceImp extends BaseServiceImp implements MessageServiceInterface{

	@Override
	public List<Message> showMessage(User u) {
		String hql = "from Message where receiver.id=? or receiver.id=0";
		String[] parameters = {"integer:"+u.getId()};
		@SuppressWarnings("unchecked")
		List<Message> messages = HibernateUtil.executeQueryOpenInView(hql, parameters);
		return messages;
	}

	@Override
	public void addMessage(Message m) {
		HibernateUtil.save(m);
	}
	

}
