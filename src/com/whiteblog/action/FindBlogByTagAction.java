package com.whiteblog.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.Blogtype;
import com.whiteblog.service.BlogManagerImpl;
import com.whiteblog.service.BlogTypeServiceImp;
import com.whiteblog.service.SuperTypeService;

public class FindBlogByTagAction {
	private BlogTypeServiceImp blogtypeService;
	private BlogManagerImpl blogManager;
	private SuperTypeService superTypeService;
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public SuperTypeService getSuperTypeService() {
		return superTypeService;
	}
	public void setSuperTypeService(SuperTypeService superTypeService) {
		this.superTypeService = superTypeService;
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
		String superTypename = superTypeService.getSupertypeDAO().findById(bt.getSupertypeId()).getSupertypeName();
		List<Blogtype> btl = blogtypeService.getBlogtypeDAO().findBySupertypeId(bt.getSupertypeId());
		ActionContext.getContext().put("TagName", superTypename);
		List<Blog> bl = blogManager.findByBlogTypeName(btl);
		ActionContext.getContext().put("resBlog", bl);
		return "success";		
	}
	
}	
