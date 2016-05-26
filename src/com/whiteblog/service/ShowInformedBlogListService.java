package com.whiteblog.service;

import java.util.List;

import com.whiteblog.dao.BlogDAO;
import com.whiteblog.dao.InformDAO;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.Inform;

public class ShowInformedBlogListService {
	private InformDAO informDAO;
	
	public List<Inform> getAll(){
		return informDAO.findAll();
	}

	public InformDAO getInformDAO() {
		return informDAO;
	}

	public void setInformDAO(InformDAO informDAO) {
		this.informDAO = informDAO;
	}
}
