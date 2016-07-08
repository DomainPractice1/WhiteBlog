package com.whiteblog.service;

import java.util.List;

import com.whiteblog.dao.BlogDAO;
import com.whiteblog.dao.CommentDAO;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.Comment;

public class ShowCommentListService {
	private CommentDAO commentDAO;
	
	public List<Comment> findByBlogId(int blogId){
		return commentDAO.findByBlogId(blogId);
	}
	
	public CommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}
}
