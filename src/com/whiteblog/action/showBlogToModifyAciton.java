package com.whiteblog.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.Blogtype;
import com.whiteblog.entity.User;
import com.whiteblog.service.BlogServiceImp;
import com.whiteblog.service.BlogTypeServiceImp;
import com.whiteblog.service.EncryptServiceImpl;

public class showBlogToModifyAciton extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BlogServiceImp blogService;    
 	private BlogTypeServiceImp blogtypeService;
	private Blog blog;
 	private Blogtype blogtype;
 	private Integer blogId;
 	private String sBlogId;
	private String strBlogId;
 	
	public String execute(){	 
		setStrBlogId(strBlogId);
		if(!EncryptServiceImpl.isNumeric(strBlogId))
			return ERROR;
		blogId = Integer.parseInt(strBlogId); 
		Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
		User u = (User)session.get("loginUser");
		Blog b = blogService.getBlogDAO().findById(blogId);
		System.out.println("u : " + u.getUserId() + " b : " + b.getUserId());
		if(!u.getUserId().equals(b.getUserId()))
			return ERROR;
		blog=blogService.getBlog(blogId);
		int typeId=blog.getTypeId();
		blogtype=blogtypeService.getBlogtype(typeId);	
		if(blog!=null && blogtype!=null)
			return SUCCESS;
		else 
			return ERROR;
	}
	
	public String getStrBlogId() {
		return strBlogId;
	}

	public void setStrBlogId(String strBlogId) {
		this.strBlogId = strBlogId;
	}

	public String getsBlogId() {
		return sBlogId;
	}

	public void setsBlogId(String sBlogId) {
		this.sBlogId = sBlogId;
	}

	public BlogServiceImp getBlogService() {
		return blogService;
	}

	public void setBlogService(BlogServiceImp blogService) {
		this.blogService = blogService;
	}

	public BlogTypeServiceImp getBlogtypeService() {
		return blogtypeService;
	}

	public void setBlogtypeService(BlogTypeServiceImp blogtypeService) {
		this.blogtypeService = blogtypeService;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public Blogtype getBlogtype() {
		return blogtype;
	}

	public void setBlogtype(Blogtype blogtype) {
		this.blogtype = blogtype;
	}

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}
}
