package com.whiteblog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Hobby entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hobby", catalog = "whiteblog")
public class Hobby implements java.io.Serializable {

	// Fields

	private Integer hobbyId;
	private String supertypeId;
	private String userId;

	// Constructors

	/** default constructor */
	public Hobby() {
	}

	/** minimal constructor */
	public Hobby(Integer hobbyId) {
		this.hobbyId = hobbyId;
	}

	/** full constructor */
	public Hobby(Integer hobbyId, String supertypeId, String userId) {
		this.hobbyId = hobbyId;
		this.supertypeId = supertypeId;
		this.userId = userId;
	}

	// Property accessors
	@Id
	@Column(name = "hobbyID", unique = true, nullable = false)
	public Integer getHobbyId() {
		return this.hobbyId;
	}

	public void setHobbyId(Integer hobbyId) {
		this.hobbyId = hobbyId;
	}

	@Column(name = "supertypeID", length = 45)
	public String getSupertypeId() {
		return this.supertypeId;
	}

	public void setSupertypeId(String supertypeId) {
		this.supertypeId = supertypeId;
	}

	@Column(name = "userID", length = 45)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}