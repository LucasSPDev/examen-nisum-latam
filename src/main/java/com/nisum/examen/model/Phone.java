package com.nisum.examen.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "PHONE")
public class Phone{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "number")
	private long number;

	@Column(name = "city_code")
	private String cityCode;

	@Column(name = "country_code")
	private String countryCode;

	@ManyToOne
	@JoinColumn(name = "user_uuid", nullable = false)
	@JsonBackReference
	private User user;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String contryCode) {
		this.countryCode = contryCode;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
