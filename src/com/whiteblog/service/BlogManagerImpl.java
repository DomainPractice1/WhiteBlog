package com.whiteblog.service;

import com.whiteblog.dao.BlogDAO;

public class BlogManagerImpl {
	private BlogDAO blogDao;

	public BlogDAO getBlogDao() {
		return blogDao;
	}

	public void setBlogDao(BlogDAO blogDao) {
		this.blogDao = blogDao;
	}
	public Integer getUByBlogID(Integer blogID){
		return blogDao.findById(blogID).getUserId();
	}
}
