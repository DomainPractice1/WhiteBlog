package com.whiteblog.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.Blog;
import com.whiteblog.service.BlogContentManageImpl;

public class blogContentAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private BlogContentManageImpl blogContentManage;
	public final String SUCCESS = "success";
	public final String FAIL = "fail";
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
		Blog ins = blogContentManage.findById(id);
		if(ins == null)
			return FAIL;
		Map req = (Map)ActionContext.getContext();
		req.put(id, ins);
		return SUCCESS;
	}
}
