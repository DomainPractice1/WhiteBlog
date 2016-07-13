package com.whiteblog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Attention entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "attention", catalog = "whiteblog")
public class Attention implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1836934514299755138L;
	private Integer attentionId;
	private Integer userId;
	private Integer passivesideId;

	// Constructors

	/** default constructor */
	public Attention() {
	}

	/** minimal constructor */
	public Attention(Integer attentionId) {
		this.attentionId = attentionId;
	}

	/** full constructor */
	public Attention(Integer attentionId, Integer userId, Integer passivesideId) {
		this.attentionId = attentionId;
		this.userId = userId;
		this.passivesideId = passivesideId;
	}

	// Property accessors
	@Id
	@Column(name = "attentionID", unique = true, nullable = false)
	public Integer getAttentionId() {
		return this.attentionId;
	}

	public void setAttentionId(Integer attentionId) {
		this.attentionId = attentionId;
	}

	@Column(name = "userID")
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "passivesideID")
	public Integer getPassivesideId() {
		return this.passivesideId;
	}

	public void setPassivesideId(Integer passivesideId) {
		this.passivesideId = passivesideId;
	}

}