package com.whiteblog.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.User;
import com.whiteblog.service.CollectService;

public class CollectAction extends ActionSupport {
	private CollectService collectService;
	private Integer collectionblogID;



	public Integer getCollectionblogID() {
		return collectionblogID;
	}

	public void setCollectionblogID(Integer collectionblogID) {
		this.collectionblogID = collectionblogID;
	}

	public CollectService getCollectService() {
		return collectService;
	}

	public void setCollectService(CollectService collectService) {
		this.collectService = collectService;
	}
	public String excute(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		User me = (User) session.get("loginUser");
		System.out.println("我在收藏文章");
		System.out.println("文章ID："+collectionblogID);
		System.out.println("登录用户是："+me.getUsername());
		collectService.collect(me, collectionblogID);
		return SUCCESS;
	}
	
}
