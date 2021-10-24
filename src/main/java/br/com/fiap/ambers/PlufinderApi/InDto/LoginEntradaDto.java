package br.com.fiap.ambers.PlufinderApi.InDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginEntradaDto {
	@Email
	private String username;
	
	@NotBlank
	private String password;
}
