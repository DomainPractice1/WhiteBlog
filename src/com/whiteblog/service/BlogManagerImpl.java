package com.whiteblog.service;

import java.util.ArrayList;
import java.util.List;

import com.whiteblog.dao.BlogDAO;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.Blogtype;

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
	/**
	 * 根据类型的名字查找相应的Blog并返回
	 * @param btl
	 * @return
	 */
	public List<Blog> findByBlogTypeName(List<Blogtype> btl){
		List<Blog> res = new ArrayList<Blog>();
		for(Blogtype bt : btl){
			List<Blog> temp = this.getBlogDao().findByTypeId(bt.getTypeId());
			for(Blog b : temp)
				res.add(b);
		}
		return res;
	}
	
}
