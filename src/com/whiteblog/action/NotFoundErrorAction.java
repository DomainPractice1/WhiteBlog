package com.whiteblog.action;

import com.opensymphony.xwork2.ActionSupport;

public class NotFoundErrorAction extends ActionSupport{
	
	public String execute(){
		System.out.println("您访问的action 不存在!");
		return SUCCESS;
	}

}
