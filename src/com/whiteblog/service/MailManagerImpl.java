package com.whiteblog.service;

import java.util.List;

import com.whiteblog.dao.MailDAO;
import com.whiteblog.dao.UserDAO;
import com.whiteblog.entity.Mail;
import com.whiteblog.entity.User;

public class MailManagerImpl {
	
	private MailDAO maildao;
	private UserDAO userdao;
	
	public UserDAO getUserdao() {
		return userdao;
	}

	public void setUserdao(UserDAO userdao) {
		this.userdao = userdao;
	}

	public MailDAO getMaildao() {
		return maildao;
	}

	public void setMaildao(MailDAO maildao) {
		this.maildao = maildao;
	}
	
	public List<Mail> getMailList(Integer userID){
		
		List<Mail> mailList = maildao.findByTouserId(userID);
		for(int i=0;i<mailList.size();i++){
			User user = userdao.findById(mailList.get(i).getFromuserId());
			mailList.get(i).setFromusername(user.getUsername());
		}
		return mailList;
	}

}
