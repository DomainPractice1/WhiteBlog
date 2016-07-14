package com.whiteblog.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.dao.CookieDAO;

public class logoutAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4609919450393569111L;

	public String execute(){
		Map<String,Object> session = ActionContext.getContext().getSession();
		/*拦截器用的数据库连接*/
		CookieDAO.disconnectDB();
		session.remove("loginUser");
		//System.out.println("Logout Action on");		
		return SUCCESS;
	}

}
