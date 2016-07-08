package com.whiteblog.action;

import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.Blogtype;
import com.whiteblog.service.BlogServiceImp;
import com.whiteblog.service.BlogTypeServiceImp;

public class showBlogToModifyAciton extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BlogServiceImp blogService;    
 	private BlogTypeServiceImp blogtypeService;
	private Blog blog;
 	private Blogtype blogtype;
 	private int blogId; 	
 	
	public String execute(){	
		blog=blogService.getBlog(blogId);
		int typeId=blog.getTypeId();
		blogtype=blogtypeService.getBlogtype(typeId);	
		if(blog!=null && blogtype!=null)
			return SUCCESS;
		else 
			return ERROR;
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

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
}
