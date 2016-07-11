package com.whiteblog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * City entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "city", catalog = "whiteblog")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class City implements java.io.Serializable {

	// Fields

	private Integer cityId;
	private String cityname;
	private Integer countryId;

	// Constructors

	/** default constructor */
	public City() {
	}

	/** minimal constructor */
	public City(Integer cityId) {
		this.cityId = cityId;
	}

	/** full constructor */
	public City(Integer cityId, String cityname, Integer countryId) {
		this.cityId = cityId;
		this.cityname = cityname;
		this.countryId = countryId;
	}

	// Property accessors
	@Id
	@Column(name = "cityID", unique = true, nullable = false)
	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Column(name = "cityname", length = 45)
	public String getCityname() {
		return this.cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	@Column(name = "countryID")
	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

}