package com.whiteblog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Supertype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "supertype", catalog = "whiteblog")
public class Supertype implements java.io.Serializable {

	// Fields

	private Integer supertypeId;
	private String supertypeName;

	// Constructors

	/** default constructor */
	public Supertype() {
	}

	/** full constructor */
	public Supertype(Integer supertypeId, String supertypeName) {
		this.supertypeId = supertypeId;
		this.supertypeName = supertypeName;
	}

	// Property accessors
	@Id
	@Column(name = "supertypeID", unique = true, nullable = false)
	public Integer getSupertypeId() {
		return this.supertypeId;
	}

	public void setSupertypeId(Integer supertypeId) {
		this.supertypeId = supertypeId;
	}

	@Column(name = "supertypeName", nullable = false, length = 45)
	public String getSupertypeName() {
		return this.supertypeName;
	}

	public void setSupertypeName(String supertypeName) {
		this.supertypeName = supertypeName;
	}

}