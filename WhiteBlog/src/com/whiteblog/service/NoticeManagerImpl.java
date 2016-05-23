package com.whiteblog.service;

import java.util.List;

import com.whiteblog.dao.NoticeDAO;
import com.whiteblog.entity.Notice;

public class NoticeManagerImpl {
	
	private NoticeDAO noticedao;

	public NoticeDAO getNoticedao() {
		return noticedao;
	}

	public void setNoticedao(NoticeDAO noticedao) {
		this.noticedao = noticedao;
	}
	
	public List<Notice> getNoticeList(Integer userID){
		
		
		List<Notice> result = noticedao.findByUserId(userID);
		
		for(int i=0;i<result.size();i++){
			
			result.get(i).setIsread("1");
			
			noticedao.attachDirty(result.get(i));
			
		}
		
		return result;
		
	}
	
	public String checkNew(Integer userID){		
		List<Notice> result = noticedao.findByUserId(userID);		
		Boolean isread = true;	
		
		for(int i =0; i<result.size();i++){
			
			if(result.get(i).getIsread().equals("0")){
				isread = false;
			}			
		}		
		if(isread){		
			return "NN";//无新消息		
		}else{		
			return "new";//有新消息
		}	
	}
	
}