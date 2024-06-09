package com.babu.fooddelivery.dto;

import java.time.LocalDateTime;

public class UserDTO {
	
	private Integer userId;
	private String userName;
	private String password;
	private String emailId;
	private String phoneNo;
	private String address;
	private LocalDateTime registrationDate;
	private String profilePicture;
	
	public UserDTO(Integer userId, String userName, String password, String emailId, String phoneNo, String address,
			LocalDateTime registrationDate, String profilePicture) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.address = address;
		this.registrationDate = registrationDate;
		this.profilePicture = profilePicture;
	}

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userName=" + userName + ", password=" + password + ", emailId="
				+ emailId + ", phoneNo=" + phoneNo + ", address=" + address + ", registrationDate=" + registrationDate
				+ ", profilePicture=" + profilePicture + "]";
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	
}
