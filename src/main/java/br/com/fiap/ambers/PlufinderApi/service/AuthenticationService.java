package br.com.fiap.ambers.PlufinderApi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fiap.ambers.PlufinderApi.entity.Login;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private LoginService loginService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Login> login = this.loginService.verificaLogin(username);
		if (login.isEmpty()) throw new UsernameNotFoundException("Login não encontrado");
		
		return login.get();
	}
	
	public static PasswordEncoder getPasswordEnconder() {
		return new BCryptPasswordEncoder();
	}

}
