package com.whiteblog.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.Blog;
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
		if(searchText == null || searchText.equals("")) searchText = new String(" ");
		byte ptext[] = searchText.getBytes();
		searchText = new String(ptext, "UTF-8");
		System.out.println("[SearchAritcle]: 搜索内容："+searchText);
		List<Blog> articleList = blogManager.getBlogDao().findByPartTitle(searchText);
		List<Blog> tmp = blogManager.getBlogDao().findByPartContent(searchText);
		articleList.removeAll(tmp);
		articleList.addAll(tmp);
		tmp = blogManager.getBlogDao().findByPartUsername(searchText);
		articleList.removeAll(tmp);
		articleList.addAll(tmp);
		
		for(int i=0;i<articleList.size();i++){
			if(articleList.get(i).getFilterwords()==0){
				articleList.remove(i);
				i--;
			}
		}
		
		//List<Blog> articleList = blogManager.getBlogDao().findAll();
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("searchList", articleList);
		return SUCCESS;
	}
}
