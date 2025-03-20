package com.nisum.examen.model;

import java.util.Date;
import java.util.List;


public class User {
	
	private String uuid;
	private Date created;
	private Date modified;
	private Date lastLogin;
	private boolean isac1tive;
	private String token;
	
	private String name;
	private String email;
	private String password;
	private List<UserPhone> phoneList;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public boolean isIsac1tive() {
		return isac1tive;
	}
	public void setIsac1tive(boolean isac1tive) {
		this.isac1tive = isac1tive;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
