package com.whiteblog.action;

import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.form.UserForm;
import com.whiteblog.service.UserManagerImpl;

public class registerAction extends ActionSupport{
	
	private UserManagerImpl usermanager;
	
	private UserForm userform;
	
	public UserManagerImpl getUsermanager() {
		return usermanager;
	}
	public void setUsermanager(UserManagerImpl usermanager) {
		this.usermanager = usermanager;
	}
	public UserForm getUserform() {
		return userform;
	}
	public void setUserform(UserForm userform) {
		this.userform = userform;
	}

	public String execute(){
		
		try{
			usermanager.register(userform);
			
			return SUCCESS;
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			return ERROR;
			
		}
	}
	
}
