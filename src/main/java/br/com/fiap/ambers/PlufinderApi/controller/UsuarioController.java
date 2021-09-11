package br.com.fiap.ambers.PlufinderApi.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.ambers.PlufinderApi.InDto.CreateUsuarioEntradaDto;

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
