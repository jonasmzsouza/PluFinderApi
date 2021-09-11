package br.com.fiap.ambers.PlufinderApi.outDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.fiap.ambers.PlufinderApi.entity.Setor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaidaConsultaSetorDto {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("nome")
	private String nome;
	
	public SaidaConsultaSetorDto(Setor setor) {
		this.id = setor.getId();
		this.nome = setor.getNome();
	}
}
