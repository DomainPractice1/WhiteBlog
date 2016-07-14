package com.whiteblog.service;

import com.whiteblog.dao.AttentionDAO;
import com.whiteblog.entity.Attention;
import com.whiteblog.entity.User;

public class PayAttentionService {
	private AttentionDAO attentionDAO;
	private static int  attentionCount=30;

	public AttentionDAO getAttentionDAO() {
		return attentionDAO;
	}

	public void setAttentionDAO(AttentionDAO attentionDAO) {
		this.attentionDAO = attentionDAO;
	}
    public void payattention(User user,int passiveUserID){
    	Attention attention=new Attention();
    	attention.setUserId(user.getUserId());
    	attention.setPassivesideId(passiveUserID);
    	attention.setAttentionId(attentionCount++);
    	attentionDAO.save(attention);
    }
}
