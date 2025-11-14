package com.rt.Entity;

public class Customer {

	private String customerId;
	private String customerName;
	private String phoneNo;
	private String emailAddress;
	private String address;
	private int isactive;
	private Signup userId;

	public Customer() {
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getisactive() {
		return isactive;
	}

	public void setisactive(int isactive) {
		this.isactive = isactive;
	}

	public Signup getUserId() {
		return userId;
	}

	public void setUserId(Signup userId) {
		this.userId = userId;
	}
}
