package com.whiteblog.action;


import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.User;
import com.whiteblog.service.UserManagerImpl;

public class UserAction extends ActionSupport {
	
	private UserManagerImpl userManager;
	
	private String selectUser;
	
	public UserManagerImpl getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManagerImpl userManager) {
		this.userManager = userManager;
	}
	

	public String getSelectUser() {
		return selectUser;
	}

	public void setSelectUser(String selectUser) {
		this.selectUser = selectUser;
	}

	public String deleteUser(){
		Map<String,Object> session = ActionContext.getContext().getSession();	
		System.out.println(selectUser);
		
		userManager.deleteUserById(Integer.parseInt(selectUser));	
		List<User> allUser = userManager.getAllUser();	
		session.put("allUser",allUser);
		return SUCCESS;
	}
	
	public String getAllUser(){	
		Map<String,Object> session = ActionContext.getContext().getSession();
		List<User> allUser = userManager.getAllUser();
		session.put("allUser",allUser);
		return SUCCESS;	
	}
	
}
