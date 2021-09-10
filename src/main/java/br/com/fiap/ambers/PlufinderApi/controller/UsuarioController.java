package br.com.fiap.ambers.PlufinderApi.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.ambers.PlufinderApi.InDto.CreateUsuarioEntradaDto;
import br.com.fiap.tds.dao.impl.UsuarioDaoImpl;
import br.com.fiap.tds.entity.Usuario;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	
//	@GetMapping
//	@Cacheable("usuarios")
//	public Page<Task> index(
//			@RequestParam(required = false) String title,
//			@PageableDefault Pageable pageable
//			) {
//		if (title == null) 
//			return repository.findAll(pageable); 
//		
//		return repository.findByTitleContaining(title, pageable);
//	}
	
	@PostMapping
	public ResponseEntity<CreateUsuarioEntradaDto> incluirUsuario(
			@RequestBody @Valid CreateUsuarioEntradaDto entrada,
			UriComponentsBuilder uriBuilder) {

		URI uri = uriBuilder
				.path("/api/task/{id}")
				.buildAndExpand(CreateUsuarioEntradaDto.class)
				.toUri();
		
		return ResponseEntity.created(uri).body(entrada);
		
	}
}
