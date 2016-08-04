package com.syj.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.syj.domain.Boarduser;
import com.syj.service.UserService;

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		validate();
		Boarduser u = new Boarduser();
		u.setName(getUsername());
		u.setPassword(getPassword());
		UserService userService = new UserService();
		u = userService.checkUser(u);
		request.setAttribute("user", u);
		if(u != null){
			setLoginErr("");
			return "success";
		}
		else{
			setLoginErr("Login Error! ");
			return "err";
		}	
	}

	@Override
	public void validate() {
		if("".equals(getUsername())){
            addFieldError("name", "UserName can't be empty");
        }
		if("".equals(getPassword())){
            addFieldError("name", "UserName can't be empty");
        }
	}

	//Pojo
	private String username;
	private String password;
	private String loginErr;
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
	public String getLoginErr() {
		return loginErr;
	}
	public void setLoginErr(String loginErr) {
		this.loginErr = loginErr;
	}
	
}
