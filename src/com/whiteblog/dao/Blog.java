package com.whiteblog.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Blog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "blog", catalog = "whiteblog")
public class Blog implements java.io.Serializable {

	// Fields

	private Integer blogId;
	private Integer userId;
	private Integer typeId;
	private String title;
	private String content;
	private String time;
	private String username;
	private Integer filterwords;
	private Integer likenumber;
	private Integer forwardnumber;
	private Integer viewnumber;
	private Integer commentnumber;
	private Integer status;

	// Constructors

	/** default constructor */
	public Blog() {
	}

	/** minimal constructor */
	public Blog(Integer userId, String title, String content, Integer status) {
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.status = status;
	}

	/** full constructor */
	public Blog(Integer userId, Integer typeId, String title, String content,
			String time, String username, Integer filterwords,
			Integer likenumber, Integer forwardnumber, Integer viewnumber,
			Integer commentnumber, Integer status) {
		this.userId = userId;
		this.typeId = typeId;
		this.title = title;
		this.content = content;
		this.time = time;
		this.username = username;
		this.filterwords = filterwords;
		this.likenumber = likenumber;
		this.forwardnumber = forwardnumber;
		this.viewnumber = viewnumber;
		this.commentnumber = commentnumber;
		this.status = status;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "blogID", unique = true, nullable = false)
	public Integer getBlogId() {
		return this.blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	@Column(name = "userID", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "typeID")
	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Column(name = "title", nullable = false, length = 200)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", nullable = false, length = 20000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "time", length = 100)
	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Column(name = "username", length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "filterwords")
	public Integer getFilterwords() {
		return this.filterwords;
	}

	public void setFilterwords(Integer filterwords) {
		this.filterwords = filterwords;
	}

	@Column(name = "likenumber")
	public Integer getLikenumber() {
		return this.likenumber;
	}

	public void setLikenumber(Integer likenumber) {
		this.likenumber = likenumber;
	}

	@Column(name = "forwardnumber")
	public Integer getForwardnumber() {
		return this.forwardnumber;
	}

	public void setForwardnumber(Integer forwardnumber) {
		this.forwardnumber = forwardnumber;
	}

	@Column(name = "viewnumber")
	public Integer getViewnumber() {
		return this.viewnumber;
	}

	public void setViewnumber(Integer viewnumber) {
		this.viewnumber = viewnumber;
	}

	@Column(name = "commentnumber")
	public Integer getCommentnumber() {
		return this.commentnumber;
	}

	public void setCommentnumber(Integer commentnumber) {
		this.commentnumber = commentnumber;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}