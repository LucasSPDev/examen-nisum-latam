package com.nisum.examen.service;

import org.springframework.stereotype.Service;

import com.nisum.examen.dto.CommonResponse;
import com.nisum.examen.dto.UserRequest;
import com.nisum.examen.dto.UserResponse;


@Service
public class UserService {
	
	public UserResponse createNewUser(UserRequest userRequest) {
		
		UserResponse userResponse = new UserResponse();
		
		return userResponse;
		
	}
	
	
	public CommonResponse getUserInfoByUUID() {
		
		CommonResponse commonResponse = new CommonResponse();
		
		return commonResponse;
		
	}
	

}
