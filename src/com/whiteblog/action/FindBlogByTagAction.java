package com.whiteblog.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.Blogtype;
import com.whiteblog.entity.Supertype;
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
		List<Blog> bl = blogManager.findByBlogTypeName(btl);
		Supertype s = superTypeService.getSupertypeDAO().findById(btl.get(0).getSupertypeId());
		List<Blogtype> tmpBtl = new ArrayList<Blogtype>();
		tmpBtl.add(btl.get(0));
		ActionContext.getContext().put("resBlog", bl);
		ActionContext.getContext().put("thisSupertype", s);
		ActionContext.getContext().put("theseSubtype", tmpBtl);
		return "success";
	}
	
	public String findBySuperTag(){
		List<Blogtype> btl = blogtypeService.getBlogtypeDAO().findBySupertypeId(id);
		List<Blog> bl = blogManager.findByBlogTypeName(btl);
		ActionContext.getContext().put("resBlog", bl);
		Supertype s = superTypeService.getSupertypeDAO().findById(id);
		ActionContext.getContext().put("thisSupertype", s);
		ActionContext.getContext().put("theseSubtype", btl);
		return "success";		
	}
	
}	
