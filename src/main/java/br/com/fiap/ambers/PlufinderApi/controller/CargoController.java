package br.com.fiap.ambers.PlufinderApi.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.ambers.PlufinderApi.InDto.CreateCargoEntradaDto;

@RestController
@RequestMapping("/cargo")
public class CargoController {

	@PostMapping
	public ResponseEntity<CreateCargoEntradaDto> incluirCargo(@RequestBody @Valid CreateCargoEntradaDto entrada,
			UriComponentsBuilder uriBuilder) {

		URI uri = uriBuilder.path("/dispositivo/incluir/{nome}").buildAndExpand(entrada.getNome()).toUri();

		return ResponseEntity.created(uri).body(entrada);
	}

}
