package com.nisum.examen.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.nisum.examen.model.Phone;



import io.swagger.v3.oas.annotations.media.Schema;

public class GetUserInfoByEmailRequest{

	@Schema(description = "Email de contacto del usuario", example = "examen@nisum.com")
	@NotNull
	@NotEmpty
	@NotBlank
	@Pattern(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

}