package com.nisum.examen.dto;

import java.util.List;
import com.nisum.examen.model.User;

public class GetPaginateUserInfoResponse extends CommonResponse{

	private List<User> userList;

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	

}