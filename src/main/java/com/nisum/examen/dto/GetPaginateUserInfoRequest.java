package com.nisum.examen.dto;

import javax.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

public class GetPaginateUserInfoRequest {
	
	@Schema(description = "Índice de inicio para la paginación", example = "examen@nisum.com")
	@NotNull
	private int start;
	
	@Schema(description = "Índice de fin para la paginación", example = "examen@nisum.com")
	@NotNull
	private int end;
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
}
