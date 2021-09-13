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

import br.com.fiap.ambers.PlufinderApi.InDto.CreateUsuarioEntradaDto;
import br.com.fiap.ambers.PlufinderApi.entity.Cargo;
import br.com.fiap.ambers.PlufinderApi.entity.Setor;
import br.com.fiap.ambers.PlufinderApi.entity.Usuario;
import br.com.fiap.ambers.PlufinderApi.exception.CommitException;
import br.com.fiap.ambers.PlufinderApi.exception.EntityNotFoundException;
import br.com.fiap.ambers.PlufinderApi.outDto.SaidaConsultaUsuarioDto;
import br.com.fiap.ambers.PlufinderApi.service.AmbienteService;
import br.com.fiap.ambers.PlufinderApi.service.CargoService;
import br.com.fiap.ambers.PlufinderApi.service.SetorService;
import br.com.fiap.ambers.PlufinderApi.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService service;
	
	@Autowired
	SetorService setorService;
	
	@Autowired
	AmbienteService ambienteService;
	
	@Autowired
	CargoService cargoService;
	
	@GetMapping
	public ResponseEntity<List<SaidaConsultaUsuarioDto>> buscarTodos() {
		List<SaidaConsultaUsuarioDto> retorno = new ArrayList<SaidaConsultaUsuarioDto>();
		try {
			List<Usuario> usuarios = service.buscarTodos();
			
			ModelMapper mapper = new ModelMapper();
			for(Usuario usuario : usuarios) {
				retorno.add(mapper.map(usuario, SaidaConsultaUsuarioDto.class));
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(null);
		}
		return ResponseEntity.ok(retorno);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<SaidaConsultaUsuarioDto> buscarPorCodigo(@PathVariable Long id) {
		ModelMapper mapper = new ModelMapper();
		SaidaConsultaUsuarioDto retorno = new SaidaConsultaUsuarioDto();
		try {
			Optional<Usuario> usuario = service.buscarPorId(id);
			
			if(usuario.isPresent())
			retorno = mapper.map(usuario.get(), SaidaConsultaUsuarioDto.class);
		} catch (EntityNotFoundException e) {
			ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(retorno);
	}

	@PostMapping
	public ResponseEntity<CreateUsuarioEntradaDto> incluirUsuario(@RequestBody @Valid CreateUsuarioEntradaDto entrada,
			UriComponentsBuilder uriBuilder) {
		
		try {
			Optional<Setor> setor = setorService.buscarPorId(entrada.getSetor());
			Optional<Cargo> cargo = cargoService.buscarPorId(entrada.getCargo());
			service.incluirUsuario(new Usuario(entrada.getNome(), setor.get(), cargo.get()));
		} catch (CommitException e) {
			return ResponseEntity.internalServerError().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
		URI uri = uriBuilder.path("/usuario/{nome}").buildAndExpand(entrada.getNome()).toUri();

		return ResponseEntity.created(uri).body(entrada);
	}
	
	@PutMapping("{id}")
	@CacheEvict(value = "usuarios", allEntries = true)
	public ResponseEntity<CreateUsuarioEntradaDto> atualizarSetor(
			@PathVariable Long id,
			@RequestBody CreateUsuarioEntradaDto entrada
			) {
		
		try {
		Optional<Usuario> usuario = service.buscarPorId(id);
		
		if(usuario.isEmpty())
			return ResponseEntity.notFound().build();
		
		Optional<Cargo> cargo = cargoService.buscarPorId(entrada.getCargo());
		Optional<Setor> setor = setorService.buscarPorId(entrada.getSetor());
		Usuario novoUsuario = usuario.get();
		
		novoUsuario.setNome(entrada.getNome());
		novoUsuario.setCargo(cargo.get());
		novoUsuario.setSetor(setor.get());
		
		service.alterarUsuario(novoUsuario);
		} catch (CommitException e) {
			return ResponseEntity.internalServerError().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(entrada);
	}
	
	@DeleteMapping("{id}")
	@CacheEvict(value = "usuarios", allEntries = true)
	public ResponseEntity<Usuario> excluirUsuario(@PathVariable Long id) {
		try {
			Optional<Usuario> usuario = service.buscarPorId(id);
			
			if(usuario.isEmpty())
				return ResponseEntity.notFound().build();
			
			service.excluirUsuario(id);
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (CommitException e) {
			ResponseEntity.internalServerError().body(e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok().build();
	}
}
