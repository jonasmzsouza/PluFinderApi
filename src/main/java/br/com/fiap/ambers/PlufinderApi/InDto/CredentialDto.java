package br.com.fiap.ambers.PlufinderApi.InDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class CredentialDto {
	
	@Email
	private String username;
	
	@NotBlank
	private String password;
	
	@NotNull
	private Long idUsuario;
	
}
