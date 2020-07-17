package com.learn.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	@Column(name = "street_name", length = 50)
	private String street;
	
	@Column(name = "city_name", length = 50)
	private String city;

	@Column(name = "state_name")
	private String state_name;

	@Column(name = "pin_code")
	private Long pincode;

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public Long getPincode() {
		return pincode;
	}
	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
	public Address() {
		super();
	}
	public Address(String street, String city, String state_name, Long pincode) {
		super();
		this.street = street;
		this.city = city;
		this.state_name = state_name;
		this.pincode = pincode;
	}
}