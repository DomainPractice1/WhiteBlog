package com.whiteblog.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whiteblog.dao.CookieDAO;
import com.whiteblog.entity.User;

public class cookieFilter implements Filter{

	protected FilterConfig config;
	private CookieDAO cookieDao = new CookieDAO();
	
	public void init(FilterConfig config){
		this.config = config;
	}
	
	public void destroy(){
		this.config = null;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		Cookie [] cookies = request.getCookies();
		String [] info ; 
		if(cookies != null){
			for(Cookie c: cookies){
				/*URLDecoder.decode(c.getName(), "utf-8");*/
				String cookieValue = URLDecoder.decode(c.getValue(), "utf-8");
				info = cookieValue.split("@");
				if(info.length == 2){
					String username = info[0];
					String password = info[1]; 
					if(username.equals("Saygoodbye"))
						break;
					User u = new User ();
					try{
						/*cookieDao.connectDB();*/
						u = cookieDao.findAll(username, password);
						if(u != null){
							u.setUsername(username);
							u.setPassword(password);
							request.getSession().setAttribute("loginUser", u);
						}//if
					}catch(Exception e){
						//System.out.println(e);
						continue;
					}  //catch
						
				}//if
			}//for
		} //if
		chain.doFilter(request, response);
	}//doFilter
	
	
	
}
