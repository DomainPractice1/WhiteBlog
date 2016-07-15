package com.whiteblog.service;

import java.util.List;

import com.whiteblog.dao.AttentionDAO;
import com.whiteblog.entity.Attention;
import com.whiteblog.entity.Supertype;
import com.whiteblog.entity.User;

public class DeleteAttentionService {
	private AttentionDAO  attentionDAO;
	public AttentionDAO getAttentionDAO() {
		return attentionDAO;
	}
	public void setAttentionDAO(AttentionDAO attentionDAO) {
		this.attentionDAO = attentionDAO;
	}
	public String deleteAttention(int attentionID,User me){
		try{
			List<Attention> attention=attentionDAO.findByUserId(me.getUserId());
			for(int i=0;i<attention.size();i++){
				if(attention.get(i).getPassivesideId()==attentionID)
					attentionDAO.delete(attention.get(i));
			}
			return "success";
		}catch(Exception e){
			return "error";
		}	
	}

}
