package br.com.fiap.ambers.PlufinderApi.controller;

import java.net.URI;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.ambers.PlufinderApi.InDto.CreateSetorEntradaDto;
import br.com.fiap.ambers.PlufinderApi.InDto.CreateUsuarioEntradaDto;
import br.com.fiap.ambers.PlufinderApi.outDto.SaidaConsultaSetorPorCodigo;
import br.com.fiap.ambers.PlufinderApi.service.SetorService;
import br.com.fiap.tds.dao.impl.SetorDaoImpl;
import br.com.fiap.tds.entity.Setor;
import br.com.fiap.tds.entity.Usuario;
import br.com.fiap.tds.exception.CommitException;
import br.com.fiap.tds.exception.EntityNotFoundException;

@RestController
@RequestMapping("/setor")
public class SetorController {
	
	@Autowired
	SetorService service;
	
	@GetMapping("{id}")
	public ResponseEntity<SaidaConsultaSetorPorCodigo> buscarPorCodigo(@PathVariable Long codigo) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		
		SaidaConsultaSetorPorCodigo retorno = new SaidaConsultaSetorPorCodigo();
		
		try {
			Setor setor = service.buscarPorId(codigo);
			mapper.map(setor, retorno);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ResponseEntity.ok(retorno);
	}
	
	@PostMapping
	public ResponseEntity<CreateSetorEntradaDto> incluirSetor(
			@RequestBody @Valid CreateSetorEntradaDto entrada,
			UriComponentsBuilder uriBuilder) {
		
		try {
			service.incluirSetor(new Setor(entrada.getNome()));
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			ResponseEntity.internalServerError().body(e.getMessage());
		}
		URI uri = uriBuilder
				.path("/setor/{nome}")
				.buildAndExpand(entrada.getNome())
				.toUri();
		
		return ResponseEntity.created(uri).body(entrada);
		
	}
}
