package com.rest.quiz.restQuiz;

public class Address {

	private String addrLine1;
	private String city;
	private String state;
	private int zip5;

	public Address() {
	}

	public Address(String addrLine1, String city, String state, int zip5) {
		this.addrLine1 = addrLine1;
		this.city = city;
		this.state = state;
		this.zip5 = zip5;
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

	public int getZip5() {
		return zip5;
	}

	public void setZip5(int zip5) {
		this.zip5 = zip5;
	}
}