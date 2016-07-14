package com.whiteblog.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.dao.UserDAO;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.Supertype;
import com.whiteblog.entity.User;
import com.whiteblog.service.PayAttentionService;
import com.whiteblog.service.ShowUserdetailService;

public class PayAttentionAction extends ActionSupport {
	private PayAttentionService payAttentionService;
	private Integer AttentionID;
	private Integer attentionUserid;
    private UserDAO userDAO;
    private ShowUserdetailService showUserdetailService;
    
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public ShowUserdetailService getShowUserdetailService() {
		return showUserdetailService;
	}

	public void setShowUserdetailService(ShowUserdetailService showUserdetailService) {
		this.showUserdetailService = showUserdetailService;
	}

	public Integer getAttentionUserid() {
		return attentionUserid;
	}

	public void setAttentionUserid(Integer attentionUserid) {
		this.attentionUserid = attentionUserid;
	}

	public Integer getAttentionID() {
		return AttentionID;
	}

	public void setAttentionID(Integer attentionID) {
		AttentionID = attentionID;
	}

	public PayAttentionService getPayAttentionService() {
		return payAttentionService;
	}

	public void setPayAttentionService(PayAttentionService payAttentionService) {
		this.payAttentionService = payAttentionService;
	}
    public String excute(){
    	Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("loginUser");
		System.out.println("我在关注用户");
		System.out.println("用户ID："+AttentionID);
		System.out.println("登录用户是："+user.getUsername());
		payAttentionService.payattention(user, AttentionID);
        System.out.println("我在查看别人的信息");
		
		
    	return SUCCESS;
    }
}
