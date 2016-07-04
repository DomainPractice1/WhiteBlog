package com.whiteblog.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.User;
import com.whiteblog.form.UserForm;
import com.whiteblog.service.UserManagerImpl;
import com.whiteblog.service.fileManagerImpl;

public class loginAction extends ActionSupport{
	
	private UserManagerImpl usermanager;
	
	private UserForm userform;
	

	public UserForm getUserform() {
		return userform;
	}

	public void setUserform(UserForm userform) {
		this.userform = userform;
	}

	public UserManagerImpl getUsermanager() {
		return usermanager;
	}

	public void setUsermanager(UserManagerImpl usermanager) {
		this.usermanager = usermanager;
	}
	
	public String execute(){
		
		if(usermanager.checklogin(userform).equals("user")){
			
			Map<String,Object> session = ActionContext.getContext().getSession();
			
			User loginUser = usermanager.findUser(userform.getUsername());
			
			session.put("loginUser",loginUser);
			
			String p = org.apache.struts2.ServletActionContext.getServletContext().getRealPath("/");
			
			fileManagerImpl.readTxtFile(p+"/WEB-INF/classes/words.txt");
			
			return "user";
			
		}else if(usermanager.checklogin(userform).equals("admin")){
			
			Map<String,Object> session = ActionContext.getContext().getSession();
			
			User loginUser = usermanager.findUser(userform.getUsername());
			
			session.put("loginUser",loginUser);
			
			return "admin";
			
		}else{
			return ERROR;
		}
	
	}
	
}
