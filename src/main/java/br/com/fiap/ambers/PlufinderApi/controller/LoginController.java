package br.com.fiap.ambers.PlufinderApi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.ambers.PlufinderApi.InDto.CredentialDto;
import br.com.fiap.ambers.PlufinderApi.entity.Login;
import br.com.fiap.ambers.PlufinderApi.entity.Usuario;
import br.com.fiap.ambers.PlufinderApi.exception.EntityNotFoundException;
import br.com.fiap.ambers.PlufinderApi.service.LoginService;
import br.com.fiap.ambers.PlufinderApi.service.TokenService;
import br.com.fiap.ambers.PlufinderApi.service.UsuarioService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<CredentialDto> incluirLogin(@RequestBody CredentialDto credential) {

		try {
			Optional<Usuario> usuario = usuarioService.buscarPorId(credential.getIdUsuario());
			if(usuario.isPresent())
				loginService.incluirLogin(new Login(usuario.get(), credential.getUsername(), credential.getPassword()));

		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().build();
	}
	
}
