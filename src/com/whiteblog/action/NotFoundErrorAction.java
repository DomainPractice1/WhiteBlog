package com.whiteblog.action;

import com.opensymphony.xwork2.ActionSupport;

public class NotFoundErrorAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4163552342722131831L;

	public String execute(){
		System.out.println("您访问的action 不存在!");
		return SUCCESS;
	}

}
