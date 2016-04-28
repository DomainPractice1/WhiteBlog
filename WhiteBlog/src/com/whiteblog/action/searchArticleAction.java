package com.whiteblog.action;

import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.service.BlogManagerImpl;

public class searchArticleAction extends ActionSupport {
	private String searchText;
	private BlogManagerImpl blogManager;
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public BlogManagerImpl getBlogManager() {
		return blogManager;
	}
	public void setBlogManager(BlogManagerImpl blogManager) {
		this.blogManager = blogManager;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(searchText.equals("") || searchText == null) return ERROR;
		System.out.println(searchText);
		
		return SUCCESS;
	}
	
	
}
