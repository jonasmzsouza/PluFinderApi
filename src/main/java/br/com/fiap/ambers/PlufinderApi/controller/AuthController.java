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
import br.com.fiap.ambers.PlufinderApi.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<String> auth(@RequestBody CredentialDto credential ) {
		UsernamePasswordAuthenticationToken auth = 
				new UsernamePasswordAuthenticationToken(credential.getUsername(), credential.getPassword());
		
		try {
			
			Authentication authenticate = authManager.authenticate(auth);
			String token = tokenService.createToken(authenticate);
			
			return ResponseEntity.ok(token);
					
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
			
		}
		
	}
	
}
