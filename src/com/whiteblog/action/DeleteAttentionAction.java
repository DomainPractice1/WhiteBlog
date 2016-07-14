package com.whiteblog.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.User;
import com.whiteblog.service.DeleteAttentionService;

public class DeleteAttentionAction extends ActionSupport {
	private DeleteAttentionService deleteAttentionService;
    private Integer userID;
	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public DeleteAttentionService getDeleteAttentionService() {
		return deleteAttentionService;
	}

	public void setDeleteAttentionService(
			DeleteAttentionService deleteAttentionService) {
		this.deleteAttentionService = deleteAttentionService;
	}
	public String excute(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		User me = (User) session.get("loginUser");
		deleteAttentionService.deleteAttention(userID,me);
		return SUCCESS;
	}
}
