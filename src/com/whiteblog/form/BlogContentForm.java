package com.whiteblog.form;

import com.whiteblog.entity.Blog;
/**
 * 
 * @author qingp
 *
 */
public class BlogContentForm {
	private Blog blog;
	private String username;
	public BlogContentForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BlogContentForm(Blog blog, String username) {
		super();
		this.blog = blog;
		this.username = username;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
