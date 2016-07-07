package com.whiteblog.action;

import com.opensymphony.xwork2.ActionSupport;
import com.whiteblog.entity.Blog;
import com.whiteblog.service.BlogManagerImpl;

public class deleteBlogAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private BlogManagerImpl blogManager;
	public BlogManagerImpl getBlogManager() {
		return blogManager;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setBlogManager(BlogManagerImpl blogManager) {
		this.blogManager = blogManager;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Blog thisBlog = blogManager.getBlogDao().findById(id);
		String res = ""; 
		if(blogManager.getBlogDao().delete(thisBlog).equals(SUCCESS)){
			//成功删除
			id = -1;
			res = SUCCESS;
		}else{
			//删除失败
			id = -2;
			res = ERROR;
		}
		return res;
	}
}
