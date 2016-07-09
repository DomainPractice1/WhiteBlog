package com.whiteblog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Country entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "country", catalog = "whiteblog")
public class Country implements java.io.Serializable {

	// Fields

	private Integer countryId;
	private String countryname;

	// Constructors

	/** default constructor */
	public Country() {
	}

	/** minimal constructor */
	public Country(Integer countryId) {
		this.countryId = countryId;
	}

	/** full constructor */
	public Country(Integer countryId, String countryname) {
		this.countryId = countryId;
		this.countryname = countryname;
	}

	// Property accessors
	@Id
	@Column(name = "countryID", unique = true, nullable = false)
	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@Column(name = "countryname", length = 45)
	public String getCountryname() {
		return this.countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

}