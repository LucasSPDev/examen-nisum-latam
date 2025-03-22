package com.nisum.examen.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotEmpty;

import com.nisum.examen.model.Phone;



import io.swagger.v3.oas.annotations.media.Schema;

public class GetUserInfoByUUIDRequest{

	@Schema(description = "Identificador del usuario", example = "8981e574-1418-4dd2-8532-1a64a38ea658")
	@NotNull
	@NotEmpty
	@NotBlank
	private String uuid;

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}