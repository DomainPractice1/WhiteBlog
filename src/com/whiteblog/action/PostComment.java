package com.whiteblog.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.form.CommentForm;
import com.whiteblog.service.BlogManagerImpl;
import com.whiteblog.service.NoticeManagerImpl;
import com.whiteblog.service.PostCommentService;
import com.whiteblog.service.UserManagerImpl;

public class PostComment extends ActionSupport{
	private PostCommentService postCommentService;
	private CommentForm commentform;
	private NoticeManagerImpl noticeManager;
	private BlogManagerImpl blogManager;
	public String execute(){
		try{
			postCommentService.post(commentform);
			Map<String,Object> session = ActionContext.getContext().getSession();
			Integer blogID = (Integer)session.get("blogId");	
			noticeManager.savecommentNotice(blogManager.getUByBlogID(blogID));
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

	public NoticeManagerImpl getNoticeManager() {
		return noticeManager;
	}

	public void setNoticeManager(NoticeManagerImpl noticeManager) {
		this.noticeManager = noticeManager;
	}

	public BlogManagerImpl getBlogManager() {
		return blogManager;
	}

	public void setBlogManager(BlogManagerImpl blogManager) {
		this.blogManager = blogManager;
	}

	
}
