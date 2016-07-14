package com.whiteblog.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.dao.CookieDAO;

public class logoutAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4609919450393569111L;

	public String execute(){
		HttpServletResponse response = ServletActionContext.getResponse();
		Map<String,Object> session = ActionContext.getContext().getSession(); 
		String s = new String ("Saygoodbye" + "@" + "hello");  
		try {
			s = URLEncoder.encode(s, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Cookie cookie = new Cookie("userCookie", s); 
		cookie.setMaxAge(1); /*存活时间1s，毕竟要消除cookie*/ 
		response.addCookie(cookie);
		/*拦截器用的数据库连接*/ 
		CookieDAO.disconnectDB();
		session.remove("loginUser");
		System.out.println("Logout Action on");		
		return SUCCESS;
	}

}
