package com.whiteblog.service;

import java.util.ArrayList;
import java.util.List;

import com.whiteblog.dao.BlogDAO;
import com.whiteblog.dao.LikeitDAO;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.Likeit;

public class LikeService {
	private LikeitDAO likeitDAO;
	private BlogDAO blogDAO;
	public final static String SUCCESS = "success";
	public final static String ERROR = "error";  
	
	public BlogDAO getBlogDAO() {
		return blogDAO;
	}

	public void setBlogDAO(BlogDAO blogDAO) {
		this.blogDAO = blogDAO;
	}
	
	public LikeitDAO getLikeitDAO() {
		return likeitDAO;
	}

	public void setLikeitDAO(LikeitDAO likeitDAO) {
		this.likeitDAO = likeitDAO;
	}
	
	public String checkClickLike(int blogId, int userId){
		List<Likeit> list = new ArrayList<Likeit>();
		list = this.likeitDAO.findByBlogId(blogId);
		for(Likeit ll : list){
			/*在likeit表里表，说明点过赞了*/
			if(ll.getBlogId() == blogId && ll.getUserId() == userId)
				return SUCCESS;
		}
		return ERROR;
	}
	
	public void saveLike(int blogId, int userId){
		Likeit l = new Likeit();
		l.setBlogId(blogId);
		l.setUserId(userId);
		Blog b = this.blogDAO.findById(blogId);
		int n = b.getLikenumber();
		b.setLikenumber(n + 1);
		this.blogDAO.attachDirty(b);
		this.likeitDAO.save(l);
	}
	
	public void deleteLike(int blogId, int userId){
		List<Likeit> list = this.likeitDAO.findByBlogId(blogId);
		Likeit l = new Likeit();
		for(Likeit ll : list){
			if(userId == ll.getUserId()){
				l = ll;
				break;
			}
		}
		Blog b = this.blogDAO.findById(blogId);
		int n = b.getLikenumber();
		if(n < 1)
			n = 1;
		b.setLikenumber(n - 1);
		this.blogDAO.attachDirty(b);
		this.likeitDAO.delete(l);
	}
	
}
