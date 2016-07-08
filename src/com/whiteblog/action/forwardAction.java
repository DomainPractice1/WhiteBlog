package com.whiteblog.action;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.User;
import com.whiteblog.service.BlogServiceImp;

public class forwardAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BlogServiceImp blogService;
	private Blog blog;
	private int id;
	
	public String execute() throws IllegalAccessException, InvocationTargetException{
		Blog formerBlog=blogService.getBlog(blog.getBlogId());
		
		Blog newBlog=new Blog();
		BeanUtils.copyProperties(newBlog,formerBlog);		
		newBlog.setBlogId(null);
		newBlog.setTitle("[转发]"+blog.getTitle());		
		newBlog.setCommentnumber(0);
		newBlog.setForwardnumber(0);
		newBlog.setLikenumber(0);
		newBlog.setViewnumber(0);
		newBlog.setStatus(1);//转发
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		User me=(User)session.get("loginUser");
		int userID=me.getUserId();
		String userName=me.getUsername();		
		newBlog.setUserId(userID);
		newBlog.setUsername(userName);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		newBlog.setTime(df.format(new Date()));// new Date()为获取当前系统时间
		blogService.getBlogDAO().save(newBlog);
				
		List<Blog> blogs= blogService.getBlogDAO().findAll();
		int blogID=0;
		if(!blogs.isEmpty()){
			int size=blogs.size();
			blogID=blogs.get(size-1).getBlogId();
		}
		else{				
			return ERROR;
		}
		id=blogID;
			
		formerBlog.setForwardnumber(formerBlog.getForwardnumber()+1);
		blogService.getBlogDAO().attachDirty(formerBlog);
		return SUCCESS;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public BlogServiceImp getBlogService() {
		return blogService;
	}
	public void setBlogService(BlogServiceImp blogService) {
		this.blogService = blogService;
	}
}
