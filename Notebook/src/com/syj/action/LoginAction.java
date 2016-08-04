package com.syj.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.syj.domain.User;
import com.syj.service.imp.UserServiceImp;
import com.syj.service.inter.UserServiceInterface;
import com.syj.util.Tools;

public class LoginAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	HttpServletRequest request = ServletActionContext.getRequest();
	
	@Override
	public String execute() throws Exception {
		if(getFlag().equals("login")){
			//使用接口来调用 Service 解耦
			UserServiceInterface userServiceInterface = new UserServiceImp();
			User user = new User();
			user.setUsername(getUsername());
			user.setPassword(Tools.MD5(getPassword()));
			user = userServiceInterface.checkUser(user);
			if(user != null){
				request.getSession().setAttribute("user", user);
				return "success";
			}else{
				return "err"; 
			}
		}else if(getFlag().equals("logout")){
			request.getSession().invalidate();
			return "logout";
		}else {
			return "err";
		}
	}

	// Dispather Flag
	private String flag;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

	// FormBean
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
