package com.nisum.examen.jpa;

import com.nisum.examen.model.User;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPA extends JpaRepository<User, String> {
	
	 Optional<User> findByUuid(String uuid);
}

