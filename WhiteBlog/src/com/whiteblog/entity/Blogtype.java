package com.whiteblog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Blogtype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "blogtype", catalog = "whiteblog")
public class Blogtype implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private String typename;
	private Integer userId;

	// Constructors

	/** default constructor */
	public Blogtype() {
	}

	/** minimal constructor */
	public Blogtype(Integer typeId, String typename) {
		this.typeId = typeId;
		this.typename = typename;
	}

	/** full constructor */
	public Blogtype(Integer typeId, String typename, Integer userId) {
		this.typeId = typeId;
		this.typename = typename;
		this.userId = userId;
	}

	// Property accessors
	@Id
	@Column(name = "typeID", unique = true, nullable = false)
	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Column(name = "typename", nullable = false, length = 45)
	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	@Column(name = "userID")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}