package com.whiteblog.action;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.Blogtype;
import com.whiteblog.entity.User;
import com.whiteblog.form.UserForm;
import com.whiteblog.service.BlogTypeServiceImp;
import com.whiteblog.service.UserManagerImpl;
import com.whiteblog.service.fileManagerImpl;

public class loginAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8628585232251444052L;
	
	private UserManagerImpl usermanager;
	
	private UserForm userform;
	
	private BlogTypeServiceImp blogtypeService;
		
	/*存放经session的加密用的函数*/
	public Map<Integer, String> encryptMap = new HashMap<Integer, String>();
	public Map<String, Integer> decryptMap = new HashMap<String, Integer>();
	
	public BlogTypeServiceImp getBlogtypeService() {
		return blogtypeService;
	}

	public void setBlogtypeService(BlogTypeServiceImp blogtypeService) {
		this.blogtypeService = blogtypeService;
	}

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
