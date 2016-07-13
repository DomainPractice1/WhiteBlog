package com.whiteblog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Inform entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "inform", catalog = "whiteblog")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Inform implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6762675631347965650L;
	private Integer id;
	private String blogid;

	// Constructors

	/** default constructor */
	public Inform() {
	}

	/** minimal constructor */
	public Inform(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Inform(Integer id, String blogid) {
		this.id = id;
		this.blogid = blogid;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "blogid", length = 45)
	public String getBlogid() {
		return this.blogid;
	}

	public void setBlogid(String blogid) {
		this.blogid = blogid;
	}

}