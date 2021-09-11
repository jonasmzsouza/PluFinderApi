package br.com.fiap.ambers.PlufinderApi.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.ambers.PlufinderApi.InDto.CreateAmbienteEntradaDto;
import br.com.fiap.ambers.PlufinderApi.entity.Ambiente;
import br.com.fiap.ambers.PlufinderApi.entity.Setor;
import br.com.fiap.ambers.PlufinderApi.exception.CommitException;
import br.com.fiap.ambers.PlufinderApi.service.AmbienteService;
import br.com.fiap.ambers.PlufinderApi.service.SetorService;

@RestController
@RequestMapping("/ambiente")
public class AmbienteController {

	@Autowired
	AmbienteService ambienteService;

	@Autowired
	SetorService setorService;
	
//	@GetMapping("{id}")
//	public ResponseEntity<T> buscarPorCodigo() {
//		
//	}

	@PostMapping
	public ResponseEntity<CreateAmbienteEntradaDto> incluirAmbiente(@RequestBody @Valid CreateAmbienteEntradaDto entrada,
			UriComponentsBuilder uriBuilder) {


		try {
			Setor setor = new Setor(entrada.getCodigoSetor(), null);
			ambienteService.incluirAmbiente(new Ambiente(setor, entrada.getNome(), entrada.getAndar(),
					entrada.getTamanho(), entrada.getNumeroProximidade(), entrada.getNomeLocalizacao()));
		} catch (CommitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		URI uri = uriBuilder.path("/ambiente/{nome}").buildAndExpand(entrada.getNome()).toUri();

		return ResponseEntity.created(uri).body(entrada);

	}
}
