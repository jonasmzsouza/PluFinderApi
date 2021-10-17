package br.com.fiap.ambers.PlufinderApi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.fiap.ambers.PlufinderApi.InDto.LoginEntradaDto;
import br.com.fiap.ambers.PlufinderApi.InDto.RefreshTokenEntradaDto;
import br.com.fiap.ambers.PlufinderApi.entity.Login;
import br.com.fiap.ambers.PlufinderApi.entity.RefreshToken;
import br.com.fiap.ambers.PlufinderApi.outDto.SaidaAuthenticationTokensDto;
import br.com.fiap.ambers.PlufinderApi.service.LoginService;
import br.com.fiap.ambers.PlufinderApi.service.RefreshTokenService;
import br.com.fiap.ambers.PlufinderApi.service.TokenService;

@RestController
@RequestMapping("/refresh")
public class RefreshTokenController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired 
	private RefreshTokenService refreshTokenService;
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping
	public ResponseEntity<SaidaAuthenticationTokensDto> auth(@RequestBody RefreshTokenEntradaDto refreshToken ) {
		
		System.out.println(refreshToken);
		Optional<RefreshToken> refreshTokenExistente = refreshTokenService.buscarRefreshToken(refreshToken.getRefreshToken());
		
		try {
			if (refreshTokenExistente.isPresent()) {
					
			String token = tokenService.createToken(refreshTokenExistente.get());
			
			SaidaAuthenticationTokensDto retorno = new SaidaAuthenticationTokensDto(token, null);
			
			return ResponseEntity.ok(retorno);
			} else {
				return ResponseEntity.notFound().build();
			}
					
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
			
		}
		
	}
	
}
