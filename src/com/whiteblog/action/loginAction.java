package com.whiteblog.action;

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

import java.io.*;

import javax.servlet.http.HttpServletRequest;


public class loginAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8628585232251444052L;
	
	private UserManagerImpl usermanager;
	
	private UserForm userform;
	
	private boolean useCookie;
	
	private BlogTypeServiceImp blogtypeService;
	
	private String code;//验证码
    
	private InputStream result;	
	private String password;
	private String username;
	private String job;
	private int provinceId;
	private int cityId;
	private int hobbyId;	
	/*存放经session的加密用的函数*/
	public Map<Integer, String> encryptMap = new HashMap<Integer, String>();
	public Map<String, Integer> decryptMap = new HashMap<String, Integer>();
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getHobbyId() {
		return hobbyId;
	}

	public void setHobbyId(int hobbyId) {
		this.hobbyId = hobbyId;
	}

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
	public String testVerification() throws Exception{
		String randomStr=(String) ActionContext.getContext().getSession().get("randomStr");
		HttpServletRequest request = ServletActionContext.getRequest();        
		code = request.getParameter("code");
		  if(code.trim().equalsIgnoreCase(randomStr)){		  
			    result=new ByteArrayInputStream("1".getBytes("UTF-8"));	
			    //return SUCCESS;
		  }else{
			   //验证码错误
			   result=new ByteArrayInputStream("0".getBytes("UTF-8"));
			   //return ERROR;	
		  }
		  return "result";
	}
	public String execute(){		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		System.out.println("cookie " + useCookie);
		
		if(usermanager.checklogin(userform).equals("user")){
			
			Map<String,Object> session = ActionContext.getContext().getSession();
			
			User loginUser = usermanager.findUser(userform.getUsername());
			
			session.put("loginUser",loginUser);
			
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
			Map<String,Object> session = ActionContext.getContext().getSession();
			Map<String,Integer> logFailUserList=(HashMap<String,Integer>) session.get("failUserList");
			if(logFailUserList==null){
				logFailUserList=new HashMap<String,Integer>();
				logFailUserList.put(userform.getUsername(), 1);							
			}
			else{
				boolean isWrongBefore=false;
				for(Map.Entry<String, Integer> entry:logFailUserList.entrySet()){    
					if(userform.getUsername().equals((entry.getKey()))){
						isWrongBefore=true;
						if(entry.getValue()<3){
							entry.setValue(entry.getValue()+1);							
						}
						else{
							//今天禁止该用户登录,调用cookie														
						}
					}				       					
				}   
				if(!isWrongBefore){
					logFailUserList.put(userform.getUsername(), 1);					
				}
			}
			session.put("failUserList", logFailUserList);
			return ERROR;
		}
	
	}
	public String ModifyInformation(){
		User u = usermanager.findUser(username);
		if(u == null)
			return ERROR;
		u.setUsername(username);
		u.setPassword(EncryptServiceImpl.convert(password));
		u.setCityId(cityId);
		u.setProvinceId(provinceId);
		usermanager.getUserdao().attachDirty(u);
		return SUCCESS; 
	}	
	public String cookieDetection(){
		Map<String, Object>session = (Map<String, Object>)ActionContext.getContext().getSession();
		User u = (User)session.get("loginUser");
		if(u != null)
			return SUCCESS;
		return LOGIN; 
	}
	public InputStream getResult() {
		return result;
	}

	public void setResult(InputStream result) {
		this.result = result;
	}
}
