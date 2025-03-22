package com.nisum.examen.dto;

import com.nisum.examen.model.User;

public class GetUserInfoByUUIDResponse extends CommonResponse{
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}