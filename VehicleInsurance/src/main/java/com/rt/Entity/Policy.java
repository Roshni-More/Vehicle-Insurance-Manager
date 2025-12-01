package com.rt.Entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Policy {

	private int policyId;
	private int vehicleId;
	private String policyType;
	private String startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expiryDate;
	private double premium;
	private int userId;
	private int isActive;
	private String status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate renewalDate;
	private int renewCount;

	private Vehicle vehicle;

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getRenewalDate() {
		return renewalDate;
	}

	public void setRenewalDate(LocalDate renewalDate) {
		this.renewalDate = renewalDate;
	}

	public int getRenewCount() {
		return renewCount;
	}

	public void setRenewCount(int renewCount) {
		this.renewCount = renewCount;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
