package com.whiteblog.service;
import com.whiteblog.dao.BlogDAO;
import com.whiteblog.entity.Blog;
public class BlogContentManageImpl {
	private BlogDAO  blogDAO;

	public BlogDAO getBlogDAO() {
		return blogDAO;
	}

	public void setBlogDAO(BlogDAO blogDAO) {
		this.blogDAO = blogDAO;
	}
	public Blog findById(java.lang.Integer id){
		return blogDAO.findById(id);
	}
	public void setupdateBlog(Blog blog){
		blogDAO.attachDirty(blog);
	}
	public void deleteBlog(Blog blog){
		blogDAO.delete(blog);
	}
}
