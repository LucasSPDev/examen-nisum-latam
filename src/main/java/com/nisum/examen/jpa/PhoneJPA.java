package com.nisum.examen.jpa;

import com.nisum.examen.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneJPA extends JpaRepository<Phone, Long> {
}