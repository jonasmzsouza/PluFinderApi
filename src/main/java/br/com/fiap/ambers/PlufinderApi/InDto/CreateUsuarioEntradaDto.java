package br.com.fiap.ambers.PlufinderApi.InDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUsuarioEntradaDto {
	
	@JsonProperty("nome")
	private String nome;

}
