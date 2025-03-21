package com.nisum.examen.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.nisum.examen.base.Constants;
import com.nisum.examen.dto.CommonResponse;
import com.nisum.examen.dto.UserRequest;
import com.nisum.examen.dto.UserResponse;
import com.nisum.examen.jpa.PhoneJPA;
import com.nisum.examen.jpa.UserJPA;
import com.nisum.examen.model.Phone;
import com.nisum.examen.model.User;


import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserJPA userJPA;

	@Autowired
	private PhoneJPA phoneJPA;
	
    @PersistenceContext
    private EntityManager entityManager;
	
	public UserResponse createNewUser(UserRequest userRequest) {

		UserResponse userResponse = new UserResponse();
		List<Phone> phones = new ArrayList<>();
		User newUser = new User();
	
		
		newUser.setUuid(UUID.randomUUID().toString());
		newUser.setName(userRequest.getName());
		newUser.setEmail(userRequest.getEmail());
		newUser.setPassword(userRequest.getPassword());
		newUser.setCreated(new Date());
		newUser.setActive(true);
		newUser.setLastLogin(new Date());
		newUser.setModified(new Date());
		newUser.setToken("abc");
		
		for (Phone phone : userRequest.getPhones()) {
		    phone.setUser(newUser);  
		    phones.add(phone);
		}
		
		newUser.setPhones(phones);
		
		userJPA.save(newUser);
		
		System.out.println("Nuevo Usuario ===================>"+newUser.toString());
		
		newUser = getUserByUuid(newUser.getUuid());
		
		if(newUser == null) {
			userResponse.setCode(Constants.CODE_ERR_CREATE_USER);
			userResponse.setCodeDescription(Constants.CODE_ERR_CREATE_USER_DESC);
		}else {
			
			userResponse.setCode(Constants.CODE_OK);
			userResponse.setCodeDescription(Constants.CODE_OK_DESCRIPTION);
			
			userResponse.setName(newUser.getName());
			userResponse.setEmail(newUser.getEmail());
			userResponse.setPassword(newUser.getPassword());
			userResponse.setPhoneList(newUser.getPhones());
			
			userResponse.setUuid(newUser.getUuid());
			userResponse.setCreated(newUser.getCreated());
			userResponse.setLastLogin(newUser.getLastLogin());
			userResponse.setModified(newUser.getModified());
			userResponse.setToken(newUser.getToken());
			userResponse.setActive(newUser.isActive());
			
		}

		return userResponse;

	}

    public User getUserByUuid(String uuid) {
        return userJPA.findByUuid(uuid).orElse(null);
    }
	

	public UserResponse getUserInfoByUUID(String uuid) {

		UserResponse userResponse = new UserResponse();
		
		User user = getUserByUuid(uuid);
		
		if(user == null) {
			
			userResponse.setCode(Constants.CODE_ERR_QUERY_USER);
			userResponse.setCodeDescription(Constants.CODE_ERR_QUERY_USER_DESC);

			
		}else {
			
			userResponse.setCode(Constants.CODE_OK);
			userResponse.setCodeDescription(Constants.CODE_OK_DESCRIPTION);
			userResponse.setName(user.getName());
			userResponse.setEmail(user.getEmail());
			userResponse.setPassword(user.getPassword());
			userResponse.setPhoneList(user.getPhones());
			
		}
		


		return userResponse;

	}

	
    public void verificarRegistros() {
        List<User> users = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        System.out.println("Usuarios en BD: " + users.size());
        users.forEach(user -> System.out.println(user));
    }

}
