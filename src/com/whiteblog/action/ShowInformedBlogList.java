package com.whiteblog.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.dao.BlogDAO;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.Inform;
import com.whiteblog.service.ShowBlogListService;
import com.whiteblog.service.ShowInformedBlogListService;

public class ShowInformedBlogList extends ActionSupport{
	private List<Blog> blogList;
	private BlogDAO blogDAO;
	public BlogDAO getBlogDAO() {
		return blogDAO;
	}

	public void setBlogDAO(BlogDAO blogDAO) {
		this.blogDAO = blogDAO;
	}

	private ShowInformedBlogListService showInformedBlogListService;

	public String execute(){
		Map<String,Object> session = ActionContext.getContext().getSession();	
		//System.out.println("[here in ShowInformedBlogListAction]");
		List<Inform> list = showInformedBlogListService.getAll();
		List<Blog> informedBlogList = new ArrayList<Blog>();
		for(Inform inform : list){
			String blogId = inform.getBlogid();
			//System.out.println("[ShowInformedBlogListAction] blogId:" + blogId);
			Blog blog = blogDAO.findById(Integer.parseInt(blogId));
			//System.out.println("[ShowInformedBlogListAction] blogTitle:" + blog.getTitle());
			informedBlogList.add(blog);
		}
		//System.out.println("[ShowInformedBlogListAction] informedBlogList.size():" + informedBlogList.size());
		session.put("informedBlogList", informedBlogList);
		return SUCCESS;
	}

	public List<Blog> getBlogList() {
		return blogList;
	}

	public void setBlogList(List<Blog> blogList) {
		this.blogList = blogList;
	}

	public ShowInformedBlogListService getShowInformedBlogListService() {
		return showInformedBlogListService;
	}

	public void setShowInformedBlogListService(
			ShowInformedBlogListService showInformedBlogListService) {
		this.showInformedBlogListService = showInformedBlogListService;
	}

}
