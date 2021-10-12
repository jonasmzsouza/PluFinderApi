package br.com.fiap.ambers.PlufinderApi.outDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaidaConsultaUsuarioDto {
	private Long id;
	private String nome;
	private SaidaConsultaSetorDto setor;
	private SaidaConsultaCargoDto cargo;
}
