package com.whiteblog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Collection entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "collection", catalog = "whiteblog")
public class Collection implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8392979968925300721L;
	private Integer collectionId;
	private Integer userId;
	private Integer blogId;

	// Constructors

	/** default constructor */
	public Collection() {
	}

	/** minimal constructor */
	public Collection(Integer collectionId) {
		this.collectionId = collectionId;
	}

	/** full constructor */
	public Collection(Integer collectionId, Integer userId, Integer blogId) {
		this.collectionId = collectionId;
		this.userId = userId;
		this.blogId = blogId;
	}

	// Property accessors
	@Id
	@Column(name = "collectionID", unique = true, nullable = false)
	public Integer getCollectionId() {
		return this.collectionId;
	}

	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}

	@Column(name = "userID")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "blogID")
	public Integer getBlogId() {
		return this.blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

}