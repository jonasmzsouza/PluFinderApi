package br.com.fiap.ambers.PlufinderApi.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.fiap.ambers.PlufinderApi.entity.Login;
import br.com.fiap.ambers.PlufinderApi.entity.Usuario;
import br.com.fiap.ambers.PlufinderApi.exception.EntityNotFoundException;
import br.com.fiap.ambers.PlufinderApi.service.LoginService;
import br.com.fiap.ambers.PlufinderApi.service.TokenService;
import br.com.fiap.ambers.PlufinderApi.service.UsuarioService;

public class AuthorizationFilter extends OncePerRequestFilter {

	private TokenService tokenService;

	private UsuarioService usuarioService;
	
	private LoginService loginService;

	public AuthorizationFilter(TokenService tokenService, LoginService loginService) {
		this.tokenService = tokenService;
		this.loginService = loginService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = extractToken(request);

		boolean valid = tokenService.valid(token);

		if (valid) {
			try {
				authorize(token);
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		filterChain.doFilter(request, response);

	}

	private void authorize(String token) throws EntityNotFoundException {
		Long id = tokenService.getUserId(token);
		Login login = loginService.buscarPorIdUsuario(id).get();

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(login, null,
				login.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	private String extractToken(HttpServletRequest request) {

		String header = request.getHeader("Authorization");

		if (header == null || header.isEmpty() || !header.startsWith("Bearer "))
			return null;

		return header.substring(7, header.length());

	}

}
