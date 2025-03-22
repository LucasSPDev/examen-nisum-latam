package com.nisum.examen.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.nisum.examen.base.Constants;
import com.nisum.examen.dto.CommonResponse;
import com.nisum.examen.dto.CreateNewUserRequest;
import com.nisum.examen.dto.CreateNewUserResponse;
import com.nisum.examen.dto.GetUserInfoByEmailResponse;
import com.nisum.examen.dto.GetUserInfoByUUIDResponse;
import com.nisum.examen.dto.GetPaginateUserInfoResponse;
import com.nisum.examen.dto.ModifyUserRequest;
import com.nisum.examen.dto.ModifyUserResponse;
import com.nisum.examen.jpa.PhoneJPA;
import com.nisum.examen.jpa.UserJPA;
import com.nisum.examen.model.Phone;
import com.nisum.examen.model.User;
import com.nisum.examen.util.JwtUtil;

@Service
public class UserService {

	@Autowired
	private UserJPA userJPA;

	@Autowired
	private PhoneJPA phoneJPA;

	public CreateNewUserResponse createNewUser(CreateNewUserRequest request) {

		CreateNewUserResponse response = new CreateNewUserResponse();
		List<Phone> phones = new ArrayList<>();
		User newUser;
	
		newUser = getUserByEmail(request.getEmail());
		
		if(newUser != null) {
			response.setCode(Constants.CODE_ERR_CREATE_USER_BY_EMAIL);
			response.setCodeDescription(Constants.CODE_ERR_CREATE_USER_BY_EMAIL_DESC);
			return response;
		}
		
		newUser = new User();
		newUser.setUuid(UUID.randomUUID().toString());
		newUser.setName(request.getName());
		newUser.setEmail(request.getEmail());
		newUser.setPassword(request.getPassword());
		newUser.setCreated(new Date());
		newUser.setActive(true);
		newUser.setLastLogin(new Date());
		newUser.setModified(new Date());
		
		JwtUtil jwtUtil = new JwtUtil();
		String token = jwtUtil.generateToken(newUser.getEmail());
		System.out.println("Token generado =====> " + token);
		
		newUser.setToken(token);

		for (Phone phone : request.getPhones()) {
			phone.setUser(newUser);  
			phones.add(phone);
		}

		newUser.setPhones(phones);

		System.out.println("Nuevo Usuario ===================>"+newUser.toString());
		
		userJPA.save(newUser);

		newUser = getUserByUuid(newUser.getUuid());

		if(newUser == null) {
			response.setCode(Constants.CODE_ERR_CREATE_USER);
			response.setCodeDescription(Constants.CODE_ERR_CREATE_USER_DESC);
		}else {

			response.setCode(Constants.CODE_OK);
			response.setCodeDescription(Constants.CODE_OK_DESCRIPTION);
			response.setUser(newUser);

		}

		return response;

	}
	
	public ModifyUserResponse modifyUserByUUID(ModifyUserRequest request) {

		ModifyUserResponse response = new ModifyUserResponse();
		List<Phone> phones = new ArrayList<>();
		User validateUser;
	
		validateUser = getUserByEmail(request.getEmail());
		
		if(validateUser == null) {
			response.setCode(Constants.CODE_ERR_MODIFY_USER_BY_EMAIL);
			response.setCodeDescription(Constants.CODE_ERR_MODIFY_USER_BY_EMAIL_DESC);
			return response;
		}
		
		System.out.println("Usuario existente ===================>"+validateUser.toString());
		
		
		User modifyUser = new User();
		modifyUser.setUuid(validateUser.getUuid());
		modifyUser.setName(request.getName());
		modifyUser.setEmail(request.getEmail());
		modifyUser.setPassword(request.getPassword());
		modifyUser.setModified(new Date());
		modifyUser.setCreated(validateUser.getCreated());
		modifyUser.setLastLogin(validateUser.getLastLogin());
		modifyUser.setActive(validateUser.getActive());
		modifyUser.setToken(validateUser.getToken());

		for (Phone phone : request.getPhones()) {
			phone.setUser(modifyUser);  
			phones.add(phone);
		}
		
		modifyUser.setPhones(phones);
		
		System.out.println("Nuevo Usuario ===================>"+modifyUser.toString());
		
		userJPA.save(modifyUser);

		

		modifyUser = getUserByUuid(modifyUser.getUuid());
		
		
		if (!modifyUser.equals(validateUser)) {
			
			System.out.println("Los usuarios son diferentes");
			response.setCode(Constants.CODE_ERR_MODIFY_USER);
			response.setCodeDescription(Constants.CODE_ERR_MODIFY_USER_DESC);

		} else {
			System.out.println("Los usuarios son iguales");
			response.setCode(Constants.CODE_OK);
			response.setCodeDescription(Constants.CODE_OK_DESCRIPTION);
			response.setUser(modifyUser);
		}

		return response;

	}

	public GetUserInfoByUUIDResponse getUserInfoByUUID(String uuid) {

		GetUserInfoByUUIDResponse response = new GetUserInfoByUUIDResponse();

		User user = getUserByUuid(uuid);

		if(user == null) {
			response.setCode(Constants.CODE_ERR_QUERY_USER);
			response.setCodeDescription(Constants.CODE_ERR_QUERY_USER_DESC);
		}else {
			response.setCode(Constants.CODE_OK);
			response.setCodeDescription(Constants.CODE_OK_DESCRIPTION);
			response.setUser(user);
		}

		return response;
	}
	
	public GetUserInfoByEmailResponse getUserInfoByEmail(String email) {

		GetUserInfoByEmailResponse response = new GetUserInfoByEmailResponse();

		User user = getUserByEmail(email);

		if(user == null) {
			response.setCode(Constants.CODE_ERR_QUERY_USER);
			response.setCodeDescription(Constants.CODE_ERR_QUERY_USER_DESC);
		}else {
			response.setCode(Constants.CODE_OK);
			response.setCodeDescription(Constants.CODE_OK_DESCRIPTION);
			response.setUser(user);
		}

		return response;
	}

	public GetPaginateUserInfoResponse getPaginateUserInfo(int start, int end) {

		GetPaginateUserInfoResponse userPaginateResponse = new GetPaginateUserInfoResponse();
		userPaginateResponse.setCode(Constants.CODE_OK);
		userPaginateResponse.setCodeDescription(Constants.CODE_OK_DESCRIPTION);

		int pageSize = end - start + 1; 
		int pageIndex = start / pageSize; 

		Page<User> usersPage = userJPA.findAll(PageRequest.of(pageIndex, pageSize, Sort.by("created").descending()));

		userPaginateResponse.setUserList(usersPage.getContent());

		return userPaginateResponse;
	}
	
	public User getUserByUuid(String uuid) {
		return userJPA.findByUuid(uuid).orElse(null);
	}
	
	public User getUserByEmail(String email) {

	    return userJPA.findByEmail(email).orElse(null);
	}

}
