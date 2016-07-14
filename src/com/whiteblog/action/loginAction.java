package com.whiteblog.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.User;
import com.whiteblog.form.UserForm;
import com.whiteblog.service.BlogTypeServiceImp;
import com.whiteblog.service.EncryptServiceImpl;
import com.whiteblog.service.UserManagerImpl;
import com.whiteblog.service.fileManagerImpl;

public class loginAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8628585232251444052L;
	
	private UserManagerImpl usermanager;
	
	private UserForm userform;
	
	private boolean useCookie;
	
	private BlogTypeServiceImp blogtypeService;
		
	/*存放经session的加密用的函数*/
	public Map<Integer, String> encryptMap = new HashMap<Integer, String>();
	public Map<String, Integer> decryptMap = new HashMap<String, Integer>();
	
	public boolean isUseCookie(){
		return this.useCookie;
	}
	
	public boolean getUseCookie() {
		return useCookie;
	}

	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}

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
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		System.out.println("cookie " + useCookie);
		
		if(usermanager.checklogin(userform).equals("user")){
			
			Map<String,Object> session = ActionContext.getContext().getSession();
			
			User loginUser = usermanager.findUser(userform.getUsername());
			
			session.put("loginUser",loginUser);
			
			String p = org.apache.struts2.ServletActionContext.getServletContext().getRealPath("/");
			
			fileManagerImpl.readTxtFile(p+"/WEB-INF/classes/words.txt");
			
			/*增加用户的Cookie,同时对Cookie加密MD5模式*/
			
			
			String password = EncryptServiceImpl.convert(userform.getPassword());
			
			String s = new String (userform.getUsername() + "@" + password); 
			
			try {
				s = URLEncoder.encode(s, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Cookie cookie = new Cookie("userCookie", s);
				
			cookie.setMaxAge(7*24*60*60); /*存活时间1一个星期*/
				
			response.addCookie(cookie);
				
			return "user";
			
		}else if(usermanager.checklogin(userform).equals("admin")){
			
			Map<String,Object> session = ActionContext.getContext().getSession();
			
			User loginUser = usermanager.findUser(userform.getUsername());
			
			String password = EncryptServiceImpl.convert(userform.getPassword());
			
			String s = new String (userform.getUsername() + "@" + password); 
			
			Cookie cookie = new Cookie("userCookie", s);
			
			cookie.setMaxAge(7*24*60*60); /*存活时间1一个星期*/
				
			response.addCookie(cookie);
			
			session.put("loginUser",loginUser);
			
			return "admin";
			
		}else{
			return ERROR;
		}
	
	}
	
	public String cookieDetection(){
		Map<String, Object>session = (Map<String, Object>)ActionContext.getContext().getSession();
		User u = (User)session.get("loginUser");
		if(u != null)
			return SUCCESS;
		return LOGIN; 
	}
	
}
