package br.com.fiap.ambers.PlufinderApi.outDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaidaConsultaAmbienteDto {

	@JsonProperty("id")
	private String id;

	@JsonProperty("nome")
	private String nome;

	@JsonProperty("setor")
	private SaidaConsultaSetorDto setor;

}
