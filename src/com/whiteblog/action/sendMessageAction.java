package com.whiteblog.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map; 

import com.opensymphony.xwork2.ActionContext;
import com.whiteblog.entity.Mail; 
import com.whiteblog.form.BlogContentForm; 
import com.whiteblog.service.BlogManagerImpl;
import com.whiteblog.service.NoticeManagerImpl;
import com.whiteblog.service.SendMessageManage;

public class sendMessageAction {
	private SendMessageManage sendMessageManage;
	private NoticeManagerImpl noticeManager;
	private BlogManagerImpl blogManager;
	public final String SUCCESS = "success";
	public final String FAIL = "fail";
	public String idtmp;
	private Integer id;
	private String mesContent;	
	public String getIdtmp() {
		return idtmp;
	}

	public void setIdtmp(String idtmp) {
		this.idtmp = idtmp;
	}

	public Integer getId() {
		return id;
	}

	public BlogManagerImpl getBlogManager() {
		return blogManager;
	}

	public void setBlogManager(BlogManagerImpl blogManager) {
		this.blogManager = blogManager;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMesContent() {
		return mesContent;
	}

	public void setMesContent(String mesContent) {
		this.mesContent = mesContent;
	}

	public SendMessageManage getSendMessageManage() {
		return sendMessageManage;
	}

	public void setSendMessageManage(SendMessageManage sendMessageManage) {
		this.sendMessageManage = sendMessageManage;
	}
	
	public NoticeManagerImpl getNoticeManager() {
		return noticeManager;
	}

	public void setNoticeManager(NoticeManagerImpl noticeManager) {
		this.noticeManager = noticeManager;
	}

	public String execute(){ 
		setIdtmp(idtmp); 
		setId(id);
		System.out.println("Execute of sendMessageAction of id is " + id + " message is " + mesContent); 
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date = new Date();
		String dateString = sdf.format(date);
		System.out.println(dateString.toString());
		Integer toUserId = blogManager.getUByBlogID(id);
		Mail mail = new Mail();
		mail.setContent(mesContent);
		mail.setTouserId(toUserId);
		mail.setTime(dateString);
		Map<String, Object> map = ActionContext.getContext().getSession();
		System.out.println(map.containsKey("req")); 
		BlogContentForm b = (BlogContentForm)map.get("req"); 
		mail.setFromuserId(b.getBlog().getUserId());
		sendMessageManage.sendMessage(mail);
		noticeManager.savemailNotice(toUserId);
		
		return SUCCESS;
	}
}
