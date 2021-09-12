package br.com.fiap.ambers.PlufinderApi.outDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.fiap.ambers.PlufinderApi.entity.Cargo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaidaConsultaCargoDto {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("nome")
	private String nome;
	
	public SaidaConsultaCargoDto(Cargo cargo) {
		this.id = cargo.getId();
		this.nome = cargo.getNome();
	}
	
}
