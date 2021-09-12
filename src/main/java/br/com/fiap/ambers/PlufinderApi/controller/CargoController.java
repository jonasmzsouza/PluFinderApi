package br.com.fiap.ambers.PlufinderApi.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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

import br.com.fiap.ambers.PlufinderApi.InDto.CreateCargoEntradaDto;
import br.com.fiap.ambers.PlufinderApi.entity.Cargo;
import br.com.fiap.ambers.PlufinderApi.exception.CommitException;
import br.com.fiap.ambers.PlufinderApi.exception.EntityNotFoundException;
import br.com.fiap.ambers.PlufinderApi.outDto.SaidaConsultaCargoDto;
import br.com.fiap.ambers.PlufinderApi.service.CargoService;

@RestController
@RequestMapping("/cargo")
public class CargoController {

	@Autowired
	CargoService service;
	
	@GetMapping
	@Cacheable("cargos")
	public ResponseEntity<List<SaidaConsultaCargoDto>> buscarTodos() {
		List<SaidaConsultaCargoDto> retorno = new ArrayList<SaidaConsultaCargoDto>();
		try {
			List<Cargo> cargos = service.buscarTodos();
			
			for(Cargo cargo : cargos) {
				retorno.add(new SaidaConsultaCargoDto(cargo));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ResponseEntity.ok(retorno);
	}
	
	@GetMapping("{id}")
	@Cacheable("cargos")
	public ResponseEntity<SaidaConsultaCargoDto> buscarPorCodigo(@PathVariable Long id) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		SaidaConsultaCargoDto retorno = new SaidaConsultaCargoDto();
		try {
			Optional<Cargo> cargo = service.buscarPorId(id);
			
			if(cargo.isPresent())
				retorno = new SaidaConsultaCargoDto(cargo.get());
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ResponseEntity.ok(retorno);
	}
	
	@PostMapping
	public ResponseEntity<CreateCargoEntradaDto> incluirCargo(
			@RequestBody @Valid CreateCargoEntradaDto entrada,
			UriComponentsBuilder uriBuilder) {
		
		try {
			service.incluirCargo(new Cargo(entrada.getNome()));
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			ResponseEntity.internalServerError().body(e.getMessage());
		}
		URI uri = uriBuilder
				.path("/cargo/{nome}")
				.buildAndExpand(entrada.getNome())
				.toUri();
		
		return ResponseEntity.created(uri).body(entrada);
		
	}
	
	@PutMapping("{id}")
	@CacheEvict(value = "cargos", allEntries = true)
	public ResponseEntity<CreateCargoEntradaDto> atualizarCargo(
			@PathVariable Long id,
			@RequestBody CreateCargoEntradaDto entrada
			) {
		
		try {
		Optional<Cargo> cargo = service.buscarPorId(id);
		
		if(cargo.isEmpty())
			return ResponseEntity.notFound().build();
		
		Cargo novoCargo = cargo.get();
		
		novoCargo.setNome(entrada.getNome());
		
			service.alterarCargo(novoCargo);
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
	@CacheEvict(value = "cargos", allEntries = true)
	public ResponseEntity<Cargo> excluirCargo(@PathVariable Long id) {
		try {
			Optional<Cargo> cargo = service.buscarPorId(id);
			
			if(cargo.isEmpty())
				return ResponseEntity.notFound().build();
			
			service.excluirCargo(id);
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok().build();
	}

}
