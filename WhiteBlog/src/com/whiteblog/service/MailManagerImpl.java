package com.whiteblog.service;

import java.util.List;

import com.whiteblog.dao.MailDAO;
import com.whiteblog.entity.Mail;

public class MailManagerImpl {
	
	private MailDAO maildao;

	public MailDAO getMaildao() {
		return maildao;
	}

	public void setMaildao(MailDAO maildao) {
		this.maildao = maildao;
	}
	
	public List<Mail> getMailList(Integer userID){
		
		List<Mail> mailList = maildao.findByTouserId(userID);
		
		return mailList;
	}

}
