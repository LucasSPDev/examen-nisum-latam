package com.nisum.examen.jpa;

import com.nisum.examen.model.User;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPA extends JpaRepository<User, String> {

	Optional<User> findByUuid(String uuid);
	Page<User> findAll(Pageable pageable);
	Optional<User> findByEmail(String email);
}

