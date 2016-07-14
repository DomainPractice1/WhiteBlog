package com.whiteblog.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class logoutAction extends ActionSupport{
	
	public String execute(){
		Map<String,Object> session = ActionContext.getContext().getSession();
		session.remove("loginUser");
		//System.out.println("Logout Action on");		
		return SUCCESS;
	}

}
