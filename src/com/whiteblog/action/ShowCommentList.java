package com.whiteblog.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.Comment;
import com.whiteblog.service.ShowCommentListService;

public class ShowCommentList extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2365125848209540412L;
	private List<Comment> commentList;
	private ShowCommentListService showCommentListService;
	
	public String execute(){
		Map<String,Object> session = ActionContext.getContext().getSession();	
		System.out.println("Appear in ShowCommentListAction");
		System.out.println("[ShowCommentListAction] session.contains(blogId)?:"+session.containsKey("blogId"));
		int blogId = (Integer) session.get("blogId");
		System.out.println("[ShowCommentListAction] blogId:"+blogId);
		commentList = showCommentListService.findByBlogId(blogId);
		System.out.println("[ShowCommentListAction] commentList.size():"+commentList.size());
		session.put("commentList", commentList);
		return SUCCESS;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public ShowCommentListService getShowCommentListService() {
		return showCommentListService;
	}

	public void setShowCommentListService(
			ShowCommentListService showCommentListService) {
		this.showCommentListService = showCommentListService;
	}
	
}
