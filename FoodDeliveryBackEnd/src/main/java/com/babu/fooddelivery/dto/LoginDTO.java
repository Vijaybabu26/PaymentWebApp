package com.babu.fooddelivery.dto;

public class LoginDTO {
	private String phoneNo;
	private String password;
	@Override
	public String toString() {
		return "LoginDTO [phoneNo=" + phoneNo + ", password=" + password + "]";
	}
	public LoginDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginDTO(String phoneNo, String password) {
		super();
		this.phoneNo = phoneNo;
		this.password = password;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
