package com.whiteblog.service;
import com.whiteblog.dao.MailDAO;
import com.whiteblog.dao.UserDAO;
import com.whiteblog.entity.Mail; 
import com.whiteblog.entity.User;
public class SendMessageManage {
	private MailDAO mailDAO;
	private UserDAO userDAO; 
	private String mesContent;
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public String getMesContent() {
		return mesContent;
	}
	public void setMesContent(String mesContent) {
		this.mesContent = mesContent;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public MailDAO getMailDAO() {
		return mailDAO;
	}
	public void setMailDAO(MailDAO mailDAO) {
		this.mailDAO = mailDAO;
	} 
	public void sendMessage(Mail mail){
		 mailDAO.save(mail);
	}
	
	
}
