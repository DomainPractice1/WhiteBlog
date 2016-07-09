package com.whiteblog.action;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.form.CommentForm;
import com.whiteblog.service.BlogManagerImpl;
import com.whiteblog.service.NoticeManagerImpl;
import com.whiteblog.service.PostCommentService;
import com.whiteblog.service.UserManagerImpl;
import com.whiteblog.service.fileManagerImpl;

public class PostComment extends ActionSupport{
	private PostCommentService postCommentService;
	private CommentForm commentform;
	private NoticeManagerImpl noticeManager;
	private BlogManagerImpl blogManager;
	private List<String> filterWords;
	
	public String execute(){
		try{													
			postCommentService.post(commentform);			
			Map<String,Object> session = ActionContext.getContext().getSession();
			Integer blogID = (Integer)session.get("blogId");	
			noticeManager.savecommentNotice(blogManager.getUByBlogID(blogID),blogID);		
			blogManager.addCommentNumber(blogID);
			return SUCCESS;
		}catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}
	}
	
	public void validate() {
		this.clearErrorsAndMessages();
		boolean isContainFilterWords=FilterWords();
		if(isContainFilterWords)
			this.addActionError("评论内容包含敏感词，请重新编辑！");		
	}
	
	public boolean hasErrors(){
	    return  (hasActionErrors()||hasFieldErrors());	    
	}

	private boolean FilterWords(){
		if(filterWords==null)
			if(!fetchFilterWords())
				return true;//未成功获取敏感词，暂时不允许用户发布评论
		String commentContent=commentform.getContent();
		for(int i=0;i<filterWords.size();i++){
			if(commentContent.contains(filterWords.get(i))){				
				return true;//包含敏感词
			}
		}				
		return false;
	}

	private boolean fetchFilterWords(){
		try{			
			filterWords=fileManagerImpl.getWords();
			return true;
		}
		catch(Exception e){
			System.out.println("读取过滤词异常");
			e.printStackTrace();
			return false;
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
