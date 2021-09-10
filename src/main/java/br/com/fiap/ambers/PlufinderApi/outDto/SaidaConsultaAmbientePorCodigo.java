package br.com.fiap.ambers.PlufinderApi.outDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaidaConsultaAmbientePorCodigo {

	@JsonProperty("nome")
	private String nome;
	
}
