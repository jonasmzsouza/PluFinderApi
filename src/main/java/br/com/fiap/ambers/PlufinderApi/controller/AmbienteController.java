package br.com.fiap.ambers.PlufinderApi.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

import br.com.fiap.ambers.PlufinderApi.InDto.CreateAmbienteEntradaDto;
import br.com.fiap.ambers.PlufinderApi.InDto.CreateSetorEntradaDto;
import br.com.fiap.ambers.PlufinderApi.entity.Ambiente;
import br.com.fiap.ambers.PlufinderApi.entity.Setor;
import br.com.fiap.ambers.PlufinderApi.exception.CommitException;
import br.com.fiap.ambers.PlufinderApi.exception.EntityNotFoundException;
import br.com.fiap.ambers.PlufinderApi.outDto.SaídaConsultaAmbienteDto;
import br.com.fiap.ambers.PlufinderApi.service.AmbienteService;
import br.com.fiap.ambers.PlufinderApi.service.SetorService;

@RestController
@RequestMapping("/ambiente")
public class AmbienteController {

	@Autowired
	AmbienteService ambienteService;

	@Autowired
	SetorService setorService;
	
	@GetMapping
	public ResponseEntity<List<SaídaConsultaAmbienteDto>> buscarTodos() {
		List<SaídaConsultaAmbienteDto> retorno = new ArrayList<SaídaConsultaAmbienteDto>();
		try {
			List<Ambiente> ambientes = ambienteService.buscarTodos();
			
			ModelMapper mapper = new ModelMapper();
			for(Ambiente ambiente : ambientes) {
				retorno.add(mapper.map(ambiente, SaídaConsultaAmbienteDto.class));
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(null);
		}
		return ResponseEntity.ok(retorno);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<SaídaConsultaAmbienteDto> buscarPorCodigo(@PathVariable Long id) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		SaídaConsultaAmbienteDto retorno = new SaídaConsultaAmbienteDto();
		try {
			Optional<Ambiente> ambiente = ambienteService.buscarPorId(id);
			
			if(ambiente.isPresent())
			mapper.map(ambiente, retorno);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
			
		return ResponseEntity.ok(retorno);
	}

	@PostMapping
	public ResponseEntity<CreateAmbienteEntradaDto> incluirAmbiente(@RequestBody @Valid CreateAmbienteEntradaDto entrada,
			UriComponentsBuilder uriBuilder) {
		
		try {
			Setor setor = new Setor(entrada.getCodigoSetor(), null);
			ambienteService.incluirAmbiente(new Ambiente(setor, entrada.getNome(), entrada.getAndar(),
					entrada.getTamanho(), entrada.getNumeroProximidade(), entrada.getNomeLocalizacao()));
		} catch (CommitException e) {
			return ResponseEntity.notFound().build();
		}
		
		URI uri = uriBuilder.path("/ambiente/{nome}").buildAndExpand(entrada.getNome()).toUri();

		return ResponseEntity.created(uri).body(entrada);

	}
	
	@PutMapping("{id}")
	@CacheEvict(value = "ambientes", allEntries = true)
	public ResponseEntity<CreateAmbienteEntradaDto> atualizarSetor(
			@PathVariable Long id,
			@RequestBody CreateAmbienteEntradaDto entrada
			) {
		
		try {
		Optional<Ambiente> ambiente = ambienteService.buscarPorId(id);
		
		if(ambiente.isEmpty())
			return ResponseEntity.notFound().build();
		
		Ambiente novoAmbiente = ambiente.get();
		
		novoAmbiente.setNome(entrada.getNome());
		novoAmbiente.setNomeLocalizacao(entrada.getNomeLocalizacao());
		novoAmbiente.setAndar(entrada.getAndar());
		novoAmbiente.setNumeroProximidade(entrada.getNumeroProximidade());
		novoAmbiente.setTamanho(entrada.getTamanho());
		novoAmbiente.getSetor().setId(entrada.getCodigoSetor());
		
		ambienteService.alterarAmbiente(novoAmbiente);
		} catch (CommitException e) {
			return ResponseEntity.notFound().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(entrada);
	}
	
	@DeleteMapping("{id}")
	@CacheEvict(value = "ambientes", allEntries = true)
	public ResponseEntity<Ambiente> excluirAmbiente(@PathVariable Long id) {
		try {
			Optional<Ambiente> ambiente = ambienteService.buscarPorId(id);
			
			if(ambiente.isEmpty())
				return ResponseEntity.notFound().build();
			
			ambienteService.excluirAmbiente(id);
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (CommitException e) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok().build();
	}
}
