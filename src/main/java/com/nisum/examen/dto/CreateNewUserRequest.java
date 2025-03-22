package com.nisum.examen.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.nisum.examen.model.Phone;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateNewUserRequest{

	private String uuid;

	@Schema(description = "Nombre completo del usuario", example = "Juan Rodríguez")
	@NotNull
	@NotEmpty
	@NotBlank
	private String name;

	@Schema(description = "Email de contacto del usuario", example = "examen@nisum.com")
	@NotNull
	@NotEmpty
	@NotBlank
	@Pattern(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
	private String email;

	@Schema(description = "Contraseña del usuario", example = "Ab123456789")
	@NotNull
	@NotEmpty
	@NotBlank
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=(.*\\d){9,}).*$")
	private String password;

	@Schema(description = "Lista de telefonos de contacto del usuario", example = "Lista")
	@NotEmpty
	@NotNull
	private List<Phone> phones;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Phone> getPhones() {
		return phones;
	}
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}