package com.whiteblog.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.Blog;
import com.whiteblog.form.BlogContentForm;
import com.whiteblog.service.BlogContentManageImpl;
import com.whiteblog.service.UserManagerImpl;

public class blogContentAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private BlogContentManageImpl blogContentManage;
	public final String SUCCESS = "success";
	public final String FAIL = "fail";
	private UserManagerImpl userManager;
	public UserManagerImpl getUserManager() {
		return userManager;
	}
	public void setUserManager(UserManagerImpl userManager) {
		this.userManager = userManager;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BlogContentManageImpl getBlogContentManage() {
		return blogContentManage;
	}
	public void setBlogContentManage(BlogContentManageImpl blogContentManage) {
		this.blogContentManage = blogContentManage;
	}
	/**
	 * @return 
	 */
	public String execute(){
		System.out.println("Appear in BlogContentAction: showing blog of id = " + id);
		Blog ins = blogContentManage.findById(id);
		if(ins == null)
			return FAIL;
		String username = userManager.findUsernameById(id);
		System.out.println(username);
		BlogContentForm blogContentForm = new BlogContentForm(ins, username);
		if(ActionContext.getContext().getSession().containsKey("req"))
			ActionContext.getContext().getSession().remove("req");
		ActionContext.getContext().put("req", blogContentForm);
		ActionContext.getContext().getSession().put("blogId",id);
		return SUCCESS;
	}
	
}
