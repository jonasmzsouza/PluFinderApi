package br.com.fiap.ambers.PlufinderApi.InDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAmbienteEntradaDto {

	@JsonProperty("codigoSetor")
	private Long codigoSetor;
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("andar")
	private Integer andar;
	
	@JsonProperty("tamanho")
	private Double tamanho;
	
	@JsonProperty("numeroProximidade")
	private Double numeroProximidade;

	@JsonProperty("nomeLocalizacao")
	private String nomeLocalizacao;
	
	@JsonProperty("codigoDispositivo")
	private Long codigoDispositivo;
	
}
