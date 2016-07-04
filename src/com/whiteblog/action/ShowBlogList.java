package com.whiteblog.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Application;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.User;
import com.whiteblog.service.ShowBlogListService;

public class ShowBlogList extends ActionSupport{
	private List<Blog> blogList;
	private List<Blog> unCheckBlog;
	private ShowBlogListService showBlogListService;

	public String execute(){
		System.out.println("[At ShowBlogList]");
		Map<String,Object> session = ActionContext.getContext().getSession();		
		if(!session.containsKey("loginUser")){
			blogList=showBlogListService.getAllBlog();
			System.out.println("blogList size:"+blogList.size());
			ActionContext.getContext().getSession().put("blogList", blogList);
		}else{
//<<<<<<< HEAD
			User user = (User) session.get("loginUser");	
			blogList=showBlogListService.findByUserId(user.getUserId());
			for(int i=0;i<blogList.size();i++){
				if(blogList.get(i).getFilterwords()==0){
					blogList.remove(i);
					i--;
				}
			}
			//HttpServletRequest request=ServletActionContext.getRequest();   
			//request.setAttribute("blogList", blogList);
//=======
//			int userID = (Integer) session.get("loginUser");	
//			blogList=showBlogListService.findByUserId(userID);
//			System.out.println("!!!!!!!!!!!!fuck2");
//>>>>>>> func-deleteBlog
			ActionContext.getContext().getSession().put("blogList", blogList);
		}
		return SUCCESS;
	}

	public String changeBlogList(){
//		Map<String,Object> session = ActionContext.getContext().getSession();
//		if(!session.containsKey("loginUser")){
//			blogList=showBlogListService.getAllBlog();
//		}else{
//			int userID = (Integer) session.get("loginUser");	
//			blogList=showBlogListService.findByUserId(userID);
//		}
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
	
	public String showUNCheckBlog(){
		
		Map<String,Object> session = ActionContext.getContext().getSession();	
		unCheckBlog = showBlogListService.getuncheckBlog();
		System.out.println("[uncheckblog size]:"+unCheckBlog.size());
		session.put("uncheckblog",unCheckBlog);
		
		return SUCCESS;
	}
}
