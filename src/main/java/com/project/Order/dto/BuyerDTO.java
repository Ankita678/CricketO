package com.project.order.dto;


public class BuyerDTO {
	
	int buyerId;
	String name;
	String email;
	String phoneNumber;
	String password;
	String isPrivileged;
	String rewardPoints;
	String isActive;
	
	public int getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getIsPrivileged() {
		return isPrivileged;
	}
	public void setIsPrivileged(String isPrivileged) {
		this.isPrivileged = isPrivileged;
	}
	public String getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(String rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String toString() {
		return "BuyerDTO [buyerId = " + buyerId + ",name = " + name + ",email = " + email
				+ ",phoneNumber = " + phoneNumber + ", password = " + password 
				+ ",isPrivileged = " + isPrivileged + ", rewardPoints = " + rewardPoints 
				+ ",isActive = " + isActive + "]";
	}

}
