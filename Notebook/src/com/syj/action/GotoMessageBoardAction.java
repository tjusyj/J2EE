package com.syj.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.syj.domain.Message;
import com.syj.domain.User;
import com.syj.service.imp.MessageServiceImp;
import com.syj.service.inter.MessageServiceInterface;


public class GotoMessageBoardAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	HttpServletRequest request = ServletActionContext.getRequest();
	
	@Override
	public String execute() throws Exception {
		MessageServiceInterface messageServiceInterface = new MessageServiceImp();
		User u = (User) request.getSession().getAttribute("user");
		List<Message> messages = messageServiceInterface.showMessage(u);
		request.setAttribute("messages", messages);
		return "success";
	}

}
