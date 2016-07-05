package com.whiteblog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Blogtype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "blogtype", catalog = "whiteblog")
public class Blogtype implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2790743504300345715L;
	private Integer typeId;
	private String typename;
	private Integer userId;
	private String superTypename;

	// Constructors

	/** default constructor */
	public Blogtype() {
	}

	/** minimal constructor */
	public Blogtype(String typename) {
		this.typename = typename;
	}

	/** full constructor */
	public Blogtype(String typename, Integer userId, String superTypename) {
		this.typename = typename;
		this.userId = userId;
		this.superTypename = superTypename;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
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

	@Column(name = "superTypename", length = 45)
	public String getSuperTypename() {
		return this.superTypename;
	}

	public void setSuperTypename(String superTypename) {
		this.superTypename = superTypename;
	}

}