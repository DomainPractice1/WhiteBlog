package com.whiteblog.dao;

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

	private Integer typeId;
	private String typename;
	private Integer userId;
	private Integer supertypeId;

	// Constructors

	/** default constructor */
	public Blogtype() {
	}

	/** minimal constructor */
	public Blogtype(String typename, Integer supertypeId) {
		this.typename = typename;
		this.supertypeId = supertypeId;
	}

	/** full constructor */
	public Blogtype(String typename, Integer userId, Integer supertypeId) {
		this.typename = typename;
		this.userId = userId;
		this.supertypeId = supertypeId;
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

	@Column(name = "supertypeID", nullable = false)
	public Integer getSupertypeId() {
		return this.supertypeId;
	}

	public void setSupertypeId(Integer supertypeId) {
		this.supertypeId = supertypeId;
	}

}