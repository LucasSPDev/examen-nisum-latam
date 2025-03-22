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
import com.nisum.examen.dto.CreateNewUserRequest;
import com.nisum.examen.dto.CreateNewUserResponse;
import com.nisum.examen.dto.GetUserInfoByEmailRequest;
import com.nisum.examen.dto.GetUserInfoByEmailResponse;
import com.nisum.examen.dto.GetUserInfoByUUIDRequest;
import com.nisum.examen.dto.GetUserInfoByUUIDResponse;
import com.nisum.examen.dto.GetPaginateUserInfoRequest;
import com.nisum.examen.dto.GetPaginateUserInfoResponse;
import com.nisum.examen.dto.ModifyUserRequest;
import com.nisum.examen.dto.ModifyUserResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	public ResponseEntity<CreateNewUserResponse> createNewUser(@Valid @RequestBody CreateNewUserRequest request, BindingResult bindingResult) {

		try {

			CreateNewUserResponse response = new CreateNewUserResponse();
			response.setCode(Constants.CODE_OK);
			response.setCodeDescription(Constants.CODE_OK_DESCRIPTION);

			CommonResponse commonResponse = validateRequestUserCreation(bindingResult);

			if(!commonResponse.getCode().equals(Constants.CODE_OK)) {
				response.setCode(commonResponse.getCode());
				response.setCodeDescription(commonResponse.getCodeDescription());	
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

			}

			response = this.userService.createNewUser(request);		
			return ResponseEntity.status(HttpStatus.OK).body(response);

		}catch(Exception e) {
			e.printStackTrace();
			
			CreateNewUserResponse errResponse = new CreateNewUserResponse();
			errResponse.setCode(Constants.CODE_GENERAL_ERROR);
			errResponse.setCodeDescription(Constants.CODE_GENERAL_ERROR_DESC);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errResponse);
		}

	}
	
	@PostMapping("/modifyUser")
	public ResponseEntity<ModifyUserResponse> modifyUser(@RequestBody ModifyUserRequest request) {

		try {

			ModifyUserResponse response = new ModifyUserResponse();
			response.setCode(Constants.CODE_OK);
			response.setCodeDescription(Constants.CODE_OK_DESCRIPTION);

			response = this.userService.modifyUserByUUID(request);
			return ResponseEntity.status(HttpStatus.OK).body(response);

		}catch(Exception e) {
			e.printStackTrace();

			ModifyUserResponse errResponse = new ModifyUserResponse();
			errResponse.setCode(Constants.CODE_GENERAL_ERROR);
			errResponse.setCodeDescription(Constants.CODE_GENERAL_ERROR_DESC);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errResponse);
		}	

	}

	@GetMapping("/getPaginateUserInfo")
	public ResponseEntity<GetPaginateUserInfoResponse> getPaginateUserInfo(@RequestBody GetPaginateUserInfoRequest userPaginateRequest) {

		try {		

			GetPaginateUserInfoResponse response = this.userService.getPaginateUserInfo(userPaginateRequest.getStart(), userPaginateRequest.getEnd());
			return ResponseEntity.status(HttpStatus.OK).body(response);

		}catch(Exception e) {
			e.printStackTrace();
			
			GetPaginateUserInfoResponse errResponse = new GetPaginateUserInfoResponse();
			errResponse.setCode(Constants.CODE_GENERAL_ERROR);
			errResponse.setCodeDescription(Constants.CODE_GENERAL_ERROR_DESC);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errResponse);
		}	

	}

	@GetMapping("/getUserInfoByUUID")
	public ResponseEntity<GetUserInfoByUUIDResponse> getUserInfoByUUID(@RequestBody GetUserInfoByUUIDRequest request) {
		try {

			GetUserInfoByUUIDResponse response = new GetUserInfoByUUIDResponse();
			response.setCode(Constants.CODE_OK);
			response.setCodeDescription(Constants.CODE_OK_DESCRIPTION);

			response = this.userService.getUserInfoByUUID(request.getUuid());
			return ResponseEntity.status(HttpStatus.OK).body(response);

		}catch(Exception e) {
			e.printStackTrace();

			GetUserInfoByUUIDResponse errResponse = new GetUserInfoByUUIDResponse();
			errResponse.setCode(Constants.CODE_GENERAL_ERROR);
			errResponse.setCodeDescription(Constants.CODE_GENERAL_ERROR_DESC);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errResponse);
		}	

	}

	@GetMapping("/getUserInfoByEmail")
	public ResponseEntity<GetUserInfoByEmailResponse> getUserInfoByEmail(@RequestBody GetUserInfoByEmailRequest request) {
		try {

			GetUserInfoByEmailResponse response = new GetUserInfoByEmailResponse();
			response.setCode(Constants.CODE_OK);
			response.setCodeDescription(Constants.CODE_OK_DESCRIPTION);

			response = this.userService.getUserInfoByEmail(request.getEmail());
			return ResponseEntity.status(HttpStatus.OK).body(response);

		}catch(Exception e) {
			e.printStackTrace();
			
			GetUserInfoByEmailResponse errResponse = new GetUserInfoByEmailResponse();
			errResponse.setCode(Constants.CODE_GENERAL_ERROR);
			errResponse.setCodeDescription(Constants.CODE_GENERAL_ERROR_DESC);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errResponse);
		}

	}

	public CommonResponse validateRequestUserCreation(BindingResult bindingResult) {

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
