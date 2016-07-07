package com.whiteblog.service;

import java.util.List;

import com.whiteblog.dao.BlogDAO;
import com.whiteblog.entity.Blog;

public class ShowBlogListService {
	private BlogDAO blogDAO;
	
	public List<Blog> findByUserId(int userId){
		return blogDAO.findByUserId(userId);
	}
	public List<Blog> getAllBlog(){
		return blogDAO.findByFilterwords(1);
	}
	public BlogDAO getBlogDAO() {
		return blogDAO;
	}
	public List<Blog> getuncheckBlog(){
		return blogDAO.findByFilterwords(0);
	}
	public void setBlogDAO(BlogDAO blogDAO) {
		this.blogDAO = blogDAO;
	}
	
	
	
	
}
