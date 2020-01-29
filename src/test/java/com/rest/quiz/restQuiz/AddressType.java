package com.rest.quiz.restQuiz;

public class AddressType {

	private String addrLine1;
	private String city;
	private String state;
	private int zipCode;

	public AddressType() {
	}

	public AddressType(String addrLine1, String city, String state, int zipCode) {
		this.addrLine1 = addrLine1;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	public String getAddrLine1() {
		return addrLine1;
	}

	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
}
// Destination POJO - Address.java
