package com.whiteblog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Likeit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "likeit", catalog = "whiteblog")
public class Likeit implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer blogId;
	private Integer userId;

	// Constructors

	/** default constructor */
	public Likeit() {
	}

	/** full constructor */
	public Likeit(Integer blogId, Integer userId) {
		this.blogId = blogId;
		this.userId = userId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "blogId")
	public Integer getBlogId() {
		return this.blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	@Column(name = "userId")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}