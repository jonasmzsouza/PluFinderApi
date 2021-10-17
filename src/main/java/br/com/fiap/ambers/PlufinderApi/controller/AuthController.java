package br.com.fiap.ambers.PlufinderApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import br.com.fiap.ambers.PlufinderApi.InDto.CredentialDto;
import br.com.fiap.ambers.PlufinderApi.InDto.LoginEntradaDto;
import br.com.fiap.ambers.PlufinderApi.outDto.SaidaAuthenticationTokensDto;
import br.com.fiap.ambers.PlufinderApi.service.RefreshTokenService;
import br.com.fiap.ambers.PlufinderApi.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired RefreshTokenService refreshTokenService;
	
	@PostMapping
	public ResponseEntity<SaidaAuthenticationTokensDto> auth(@RequestBody LoginEntradaDto credential ) {
		UsernamePasswordAuthenticationToken auth = 
				new UsernamePasswordAuthenticationToken(credential.getUsername(), credential.getPassword());
		
		try {
			
			Authentication authenticate = authManager.authenticate(auth);
			String token = tokenService.createToken(authenticate);
			String refreshToken = refreshTokenService.generateRefreshToken(authenticate);
			
			SaidaAuthenticationTokensDto retorno = new SaidaAuthenticationTokensDto(token, refreshToken);
			
			return ResponseEntity.ok(retorno);
					
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
			
		}
		
	}
	
}
