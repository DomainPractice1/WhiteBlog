package com.whiteblog.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Application;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.Blog;
import com.whiteblog.service.ShowBlogListService;

public class ShowBlogList extends ActionSupport{
	private List<Blog> blogList;
	private ShowBlogListService showBlogListService;

	public String execute(){
		Map<String,Object> session = ActionContext.getContext().getSession();	
		System.out.println("!!!!!!!!!!!!fuck1");
		if(!session.containsKey("loginUser")){
			blogList=showBlogListService.getAllBlog();
			ActionContext.getContext().getSession().put("blogList", blogList);
		}else{
			int userID = (Integer) session.get("loginUser");	
			blogList=showBlogListService.findByUserId(userID);
			System.out.println("!!!!!!!!!!!!fuck2");
			//HttpServletRequest request=ServletActionContext.getRequest();   
			//request.setAttribute("blogList", blogList);
			ActionContext.getContext().getSession().put("blogList", blogList);
		}
		return SUCCESS;
	}

	public List<Blog> getBlogList() {
		return blogList;
	}

	public void setBlogList(List<Blog> blogList) {
		this.blogList = blogList;
	}

	public ShowBlogListService getShowBlogListService() {
		return showBlogListService;
	}

	public void setShowBlogListService(ShowBlogListService showBlogListService) {
		this.showBlogListService = showBlogListService;
	}
}
