package com.syj.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.syj.domain.Message;
import com.syj.domain.User;
import com.syj.service.imp.MessageServiceImp;
import com.syj.service.imp.UserServiceImp;
import com.syj.service.inter.MessageServiceInterface;
import com.syj.service.inter.UserServiceInterface;

public class MessageAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = ServletActionContext.getRequest();
	
	@Override
	public String execute() throws Exception {
		if(getFlag().equals("gotoAddMessage")){
			UserServiceInterface us = new UserServiceImp();
			List<User> list = us.getUsers();
			request.setAttribute("users", list);
			return "gotoAddMessage";
		}else if(getFlag().equals("addMessage")){
			MessageServiceInterface ms = new MessageServiceImp();
			Message m = new Message();
			User sender = (User) request.getSession().getAttribute("user");
			User receiver = new User();
			Integer ReceiveId = Integer.parseInt(getAdd_receiverId());
			receiver.setId(ReceiveId);
			m.setSender(sender);
			m.setReceiver(receiver);
			m.setContent(getAdd_content());
			m.setTime(new Date());
			ms.addMessage(m);
			return "addSuccess";
		}else{
			return "logout";
		}
		
	}
	
	// Dispatcher Flag
	private String flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	// Form Bean
	private String add_content;
	private String add_receiverId;

	public String getAdd_content() {
		return add_content;
	}
	public void setAdd_content(String add_content) {
		this.add_content = add_content;
	}
	public String getAdd_receiverId() {
		return add_receiverId;
	}
	public void setAdd_receiverId(String add_receiverId) {
		this.add_receiverId = add_receiverId;
	}
	
}
