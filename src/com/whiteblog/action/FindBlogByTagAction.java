package com.whiteblog.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.Blogtype;
import com.whiteblog.service.BlogManagerImpl;
import com.whiteblog.service.BlogTypeServiceImp;

public class FindBlogByTagAction {
	private BlogTypeServiceImp blogtypeService;
	private BlogManagerImpl blogManager;
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BlogTypeServiceImp getBlogtypeService() {
		return blogtypeService;
	}
	public void setBlogtypeService(BlogTypeServiceImp blogtypeService) {
		this.blogtypeService = blogtypeService;
	}
	public BlogManagerImpl getBlogManager() {
		return blogManager;
	}
	public void setBlogManager(BlogManagerImpl blogManager) {
		this.blogManager = blogManager;
	}
	
	public String findBlogs(){ 
		Blogtype bt = blogtypeService.getBlogtype(id);
		String typeName = bt.getTypename();
		List<Blogtype> btl = blogtypeService.getBlogtypeDAO().findByTypename(typeName);
		ActionContext.getContext().put("TagName", typeName);
		List<Blog> bl = blogManager.findByBlogTypeName(btl);
		ActionContext.getContext().put("resBlog", bl);
		return "success";
	}
	
	public String findBySuperTag(){
		System.out.println("super tag is " + id);
		Blogtype bt = blogtypeService.getBlogtype(id);
		String superTypename = bt.getSuperTypename();
		List<Blogtype> btl = blogtypeService.getBlogtypeDAO().findBySuperTypename(superTypename); 
		ActionContext.getContext().put("TagName", superTypename);
		List<Blog> bl = blogManager.findByBlogTypeName(btl);
		ActionContext.getContext().put("resBlog", bl);
		return "success";		
	}
	
}	
