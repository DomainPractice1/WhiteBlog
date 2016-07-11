package com.whiteblog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Job entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "job", catalog = "whiteblog")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Job implements java.io.Serializable {

	// Fields

	private Integer jobId;
	private String jobname;

	// Constructors

	/** default constructor */
	public Job() {
	}

	/** minimal constructor */
	public Job(Integer jobId) {
		this.jobId = jobId;
	}

	/** full constructor */
	public Job(Integer jobId, String jobname) {
		this.jobId = jobId;
		this.jobname = jobname;
	}

	// Property accessors
	@Id
	@Column(name = "jobID", unique = true, nullable = false)
	public Integer getJobId() {
		return this.jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	@Column(name = "jobname", length = 45)
	public String getJobname() {
		return this.jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

}