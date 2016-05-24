package com.whiteblog.action;

import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.form.CommentForm;
import com.whiteblog.service.PostCommentService;

public class PostComment extends ActionSupport{
	private PostCommentService postCommentService;
	private CommentForm commentform;
	
	public String execute(){
		try{
			postCommentService.post(commentform);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public PostCommentService getPostCommentService() {
		return postCommentService;
	}
	public void setPostCommentService(PostCommentService postCommentService) {
		this.postCommentService = postCommentService;
	}
	public CommentForm getCommentform() {
		return commentform;
	}
	public void setCommentform(CommentForm commentform) {
		this.commentform = commentform;
	}
}
