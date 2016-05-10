package com.whiteblog.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.Notice;
import com.whiteblog.service.NoticeManagerImpl;

public class noticeAction extends ActionSupport {
	
	private NoticeManagerImpl noticeManager;

	private List<Notice> noticeList;
	
	private String checkResult;
	
	public void setNoticeList(List<Notice> noticeList) {
		this.noticeList = noticeList;
	}

	
	public List<Notice> getNoticeList() {
		return noticeList;
	}


	public NoticeManagerImpl getNoticeManager() {
		return noticeManager;
	}

	public void setNoticeManager(NoticeManagerImpl noticeManager) {
		this.noticeManager = noticeManager;
	}

	public String getCheckResult() {
		return checkResult;
	}


	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}


	public String execute(){
		
		Map<String,Object> session = ActionContext.getContext().getSession();
		
		Integer userID = (Integer)session.get("loginUser");
		
		noticeList = new ArrayList<Notice>();
		
		noticeList = noticeManager.getNoticeList(userID);
		
		return SUCCESS;
	}
	
	public String checkNew(){
		
		Map<String,Object> session = ActionContext.getContext().getSession();
		
		Integer userID = (Integer)session.get("loginUser");
				
		checkResult = noticeManager.checkNew(userID);
		
		return SUCCESS;
	}
	

}
