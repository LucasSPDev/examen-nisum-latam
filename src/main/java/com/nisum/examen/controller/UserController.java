package com.nisum.examen.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.examen.service.UserService;
import com.nisum.examen.base.Constants;
import com.nisum.examen.dto.CommonResponse;
import com.nisum.examen.dto.UserRequest;
import com.nisum.examen.dto.UserResponse;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

@RestController
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/getStatusService")
	public ResponseEntity<String> getStatusService() {

		return new ResponseEntity<>("Server is up!!", HttpStatus.OK);
	}

	@PostMapping("/createNewUser")
	public ResponseEntity<UserResponse> createNewUser(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult) {

		try {

			UserResponse userResponse = new UserResponse();
			userResponse.setCode(Constants.CODE_OK);
			userResponse.setCodeDescription(Constants.CODE_OK_DESCRIPTION);

			CommonResponse commonResponse = validateRequestUser(userRequest, bindingResult);

			if(!commonResponse.getCode().equals(Constants.CODE_OK)) {
				userResponse.setCode(commonResponse.getCode());
				userResponse.setCodeDescription(commonResponse.getCodeDescription());	
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);

			}

			userResponse = this.userService.createNewUser(userRequest);		
			return ResponseEntity.status(HttpStatus.OK).body(userResponse);

		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}


	}


	@GetMapping("/getUserInfo")
	public ResponseEntity<UserResponse> getUserInfoByUUID(@RequestBody UserRequest userRequest) {

		UserResponse userResponse = new UserResponse();
		userResponse.setCode(Constants.CODE_OK);
		userResponse.setCodeDescription(Constants.CODE_OK_DESCRIPTION);

		userResponse = this.userService.getUserInfoByUUID(userRequest.getUuid());
		return ResponseEntity.status(HttpStatus.OK).body(userResponse);
	}


	public CommonResponse validateRequestUser(UserRequest userRequest, BindingResult bindingResult) {

		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setCode(Constants.CODE_OK);
		commonResponse.setCodeDescription(Constants.CODE_OK_DESCRIPTION);


		if (bindingResult.hasErrors()) {
			for (FieldError error : bindingResult.getFieldErrors()) {

				switch (error.getField()) {
				case "name":
					commonResponse.setCode(Constants.CODE_NAME_USER);
					commonResponse.setCodeDescription(Constants.CODE_NAME_USER_DESC);
					break;
				case "email":
					commonResponse.setCode(Constants.CODE_EMAIL_FORMAT);
					commonResponse.setCodeDescription(Constants.CODE_EMAIL_FORMAT_DESC);
					break;
				case "password":
					commonResponse.setCode(Constants.CODE_PASSWORD_FORMAT);
					commonResponse.setCodeDescription(Constants.CODE_PASSWORD_FORMAT_DESC);
					break;
				case "phones":
					commonResponse.setCode(Constants.CODE_PHONE_USER);
					commonResponse.setCodeDescription(Constants.CODE_PHONE_USER_DESC);
					break;

				}
			}

		}


		return commonResponse;

	}


}
