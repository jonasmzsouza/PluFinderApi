package br.com.fiap.ambers.PlufinderApi.InDto;

import java.time.LocalDate;

import br.com.fiap.ambers.PlufinderApi.enumeration.ChamadoEnum;
import lombok.Data;

@Data
public class CreateChamadoEntradaDto {
	
	private Long idUsuarioSolicitacaoChamado;
	private ChamadoEnum tipoChamado;
	
}
