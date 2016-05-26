package com.whiteblog.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.Mail;
import com.whiteblog.entity.User;
import com.whiteblog.service.MailManagerImpl;

public class mailAction extends ActionSupport{
	
	private MailManagerImpl mailManager;
		
	public MailManagerImpl getMailManager() {
		return mailManager;
	}



	public void setMailManager(MailManagerImpl mailManager) {
		this.mailManager = mailManager;
	}



	public String execute(){
		
		Map<String,Object> session = ActionContext.getContext().getSession();
		
		User user = (User)session.get("loginUser");
		
		List<Mail> mailList = mailManager.getMailList(user.getUserId());
		
		session.put("MailList", mailList);
		
		return SUCCESS;
	}

}
