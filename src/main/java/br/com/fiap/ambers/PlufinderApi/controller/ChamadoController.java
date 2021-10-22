package br.com.fiap.ambers.PlufinderApi.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.ambers.PlufinderApi.InDto.AtenderChamadoEntradaDto;
import br.com.fiap.ambers.PlufinderApi.InDto.CreateAmbienteEntradaDto;
import br.com.fiap.ambers.PlufinderApi.InDto.CreateChamadoEntradaDto;
import br.com.fiap.ambers.PlufinderApi.entity.Ambiente;
import br.com.fiap.ambers.PlufinderApi.entity.Chamado;
import br.com.fiap.ambers.PlufinderApi.entity.Setor;
import br.com.fiap.ambers.PlufinderApi.entity.Usuario;
import br.com.fiap.ambers.PlufinderApi.exception.CommitException;
import br.com.fiap.ambers.PlufinderApi.exception.EntityNotFoundException;
import br.com.fiap.ambers.PlufinderApi.service.ChamadoService;
import br.com.fiap.ambers.PlufinderApi.service.UsuarioService;

@RestController
@RequestMapping("/chamado")
public class ChamadoController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ChamadoService chamadoService;
	
	@PostMapping
	public ResponseEntity<CreateChamadoEntradaDto> gerarChamado(@RequestBody @Valid CreateChamadoEntradaDto entrada,
			UriComponentsBuilder uriBuilder) {
		
		try {
			Optional<Usuario> usuario = usuarioService.buscarPorId(entrada.getIdUsuarioSolicitacaoChamado());
			Chamado chamado = new Chamado(entrada, usuario.get());
			this.chamadoService.gerarChamado(chamado);
			
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		URI uri = uriBuilder.path("/chamado").buildAndExpand(entrada).toUri();

		return ResponseEntity.created(uri).body(entrada);

	}
	
	@PatchMapping
	public ResponseEntity<AtenderChamadoEntradaDto> gerarChamado(@RequestBody @Valid AtenderChamadoEntradaDto entrada,
			UriComponentsBuilder uriBuilder) {
		
		try {
			Optional<Chamado> chamado = chamadoService.buscarPorId(entrada.getIdChamado());
			Optional<Usuario> usuario = usuarioService.buscarPorId(entrada.getIdUsuarioAtendente());
			this.chamadoService.atenderChamado(chamado.get(), usuario.get());
			
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		URI uri = uriBuilder.path("/chamado").buildAndExpand(entrada).toUri();

		return ResponseEntity.created(uri).body(entrada);

	}

}
