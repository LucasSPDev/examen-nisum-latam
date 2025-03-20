package com.nisum.examen.dto;

import java.util.List;

import com.nisum.examen.model.UserPhone;

public class UserResponse extends CommonResponse{
	
	private String name;
	private String email;
	private String password;
	private List<UserPhone> phoneList;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<UserPhone> getPhoneList() {
		return phoneList;
	}
	public void setPhoneList(List<UserPhone> phoneList) {
		this.phoneList = phoneList;
	}
	
	


}