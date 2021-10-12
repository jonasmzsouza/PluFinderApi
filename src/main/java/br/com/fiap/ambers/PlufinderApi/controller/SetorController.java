package br.com.fiap.ambers.PlufinderApi.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.ambers.PlufinderApi.InDto.CreateSetorEntradaDto;
import br.com.fiap.ambers.PlufinderApi.entity.Setor;
import br.com.fiap.ambers.PlufinderApi.exception.CommitException;
import br.com.fiap.ambers.PlufinderApi.exception.EntityNotFoundException;
import br.com.fiap.ambers.PlufinderApi.outDto.SaidaConsultaSetorDto;
import br.com.fiap.ambers.PlufinderApi.service.SetorService;

@RestController
@RequestMapping("/setor")
public class SetorController {
	
	@Autowired
	SetorService service;
	
	@GetMapping
	public ResponseEntity<List<SaidaConsultaSetorDto>> buscarTodos() {
		List<SaidaConsultaSetorDto> retorno = new ArrayList<SaidaConsultaSetorDto>();
		try {
			List<Setor> setores = service.buscarTodos();
			
			for(Setor setor : setores) {
				retorno.add(new SaidaConsultaSetorDto(setor));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ResponseEntity.ok(retorno);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<SaidaConsultaSetorDto> buscarPorCodigo(@PathVariable Long id) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		SaidaConsultaSetorDto retorno = new SaidaConsultaSetorDto();
		try {
			Optional<Setor> setor = service.buscarPorId(id);
			
			if(setor.isPresent())
			retorno.setId(setor.get().getId());
			retorno.setNome(setor.get().getNome());
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
	
	@PutMapping("{id}")
	@CacheEvict(value = "setores", allEntries = true)
	public ResponseEntity<CreateSetorEntradaDto> atualizarSetor(
			@PathVariable Long id,
			@RequestBody CreateSetorEntradaDto entrada
			) {
		
		try {
		Optional<Setor> setor = service.buscarPorId(id);
		
		if(setor.isEmpty())
			return ResponseEntity.notFound().build();
		
		Setor novoSetor = setor.get();
		
		novoSetor.setNome(entrada.getNome());
		
			service.alterarSetor(novoSetor);
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(entrada);
	}
	
	@DeleteMapping("{id}")
	@CacheEvict(value = "setores", allEntries = true)
	public ResponseEntity<Setor> excluirSetor(@PathVariable Long id) {
		try {
			Optional<Setor> setor = service.buscarPorId(id);
			
			if(setor.isEmpty())
				return ResponseEntity.notFound().build();
			
			service.excluirSetor(id);
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok().build();
	}
}
