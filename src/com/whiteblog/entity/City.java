package com.whiteblog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * City entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "city", catalog = "whiteblog")
public class City implements java.io.Serializable {

	// Fields

	private Integer cityId;
	private String cityname;
	private Integer provinceId;

	// Constructors

	/** default constructor */
	public City() {
	}

	/** minimal constructor */
	public City(Integer cityId) {
		this.cityId = cityId;
	}

	/** full constructor */
	public City(Integer cityId, String cityname, Integer provinceId) {
		this.cityId = cityId;
		this.cityname = cityname;
		this.provinceId = provinceId;
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

	@Column(name = "provinceID")
	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

}