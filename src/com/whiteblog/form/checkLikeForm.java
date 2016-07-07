package com.whiteblog.form;

public class checkLikeForm {
	private int blogId;
	private String isLike;
	public checkLikeForm() {
		super();
	}
	public checkLikeForm(int blogId, String isLike) {
		super();
		this.blogId = blogId;
		this.isLike = isLike;
	}
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getIsLike() {
		return isLike;
	}
	public void setIsLike(String isLike) {
		this.isLike = isLike;
	}
	
}
