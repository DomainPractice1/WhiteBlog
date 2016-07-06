package com.whiteblog.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.whiteblog.entity.User;
import com.whiteblog.service.LikeService;

public class LikeAction {
	private int id;
	private String res;
	private LikeService likeService;
	public final static String SUCCESS = "success";
	public final static String ERROR = "error";	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRes() {
		return res;
	}
	public void setRes(String res) {
		this.res = res;
	}
	public LikeService getLikeService() {
		return likeService;
	}
	public void setLikeService(LikeService likeService) {
		this.likeService = likeService;
	}
	
	public String clickAction(){
		Map<String, Object> session = (Map<String, Object>)ActionContext.getContext().getSession();
		User loginUser = (User)session.get("loginUser");
		/*如果返回SUCCESS说明在点赞记录里面找到了此用户点赞的记录*/
		if(this.likeService.checkClickLike(id, loginUser.getUserId()).equals(SUCCESS)){
			this.likeService.deleteLike(id, loginUser.getUserId());
			System.out.println("{ClickAction id delete}");
			return ERROR;
		}
		this.likeService.saveLike(id, loginUser.getUserId());
		System.out.println("{ClickAction id save like}");
		return SUCCESS;
	}
	
}
