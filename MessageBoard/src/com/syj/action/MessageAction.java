package com.syj.action;

import com.opensymphony.xwork2.ActionSupport;

public class MessageAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() throws Exception {
		if(flag.equals("gotoPublish"))
			return "gotoPublish";
		else if(flag.equals("gotoPublish"))
			return "";
		else
			return "";
	}
	
	private String flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
