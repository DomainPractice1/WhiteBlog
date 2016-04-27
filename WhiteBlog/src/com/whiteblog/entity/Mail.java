package com.whiteblog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "mail", catalog = "whiteblog")
public class Mail implements java.io.Serializable {

	// Fields

	private Integer mailId;
	private Integer fromuserId;
	private Integer touserId;
	private String content;
	private String time;

	// Constructors

	/** default constructor */
	public Mail() {
	}

	/** full constructor */
	public Mail(Integer fromuserId, Integer touserId, String content,
			String time) {
		this.fromuserId = fromuserId;
		this.touserId = touserId;
		this.content = content;
		this.time = time;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "mailID", unique = true, nullable = false)
	public Integer getMailId() {
		return this.mailId;
	}

	public void setMailId(Integer mailId) {
		this.mailId = mailId;
	}

	@Column(name = "fromuserID")
	public Integer getFromuserId() {
		return this.fromuserId;
	}

	public void setFromuserId(Integer fromuserId) {
		this.fromuserId = fromuserId;
	}

	@Column(name = "touserID")
	public Integer getTouserId() {
		return this.touserId;
	}

	public void setTouserId(Integer touserId) {
		this.touserId = touserId;
	}

	@Column(name = "content", length = 20000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "time", length = 45)
	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}