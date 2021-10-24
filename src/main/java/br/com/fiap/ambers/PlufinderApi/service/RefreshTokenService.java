package br.com.fiap.ambers.PlufinderApi.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.fiap.ambers.PlufinderApi.entity.Login;
import br.com.fiap.ambers.PlufinderApi.entity.RefreshToken;
import br.com.fiap.ambers.PlufinderApi.repository.RefreshTokenRepository;

@Service
public class RefreshTokenService {

	@Autowired
	private RefreshTokenRepository repository;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private LoginService loginService;

	@Value("${plufinderapi.refreshtoken.duration}")
	private long duration;

	public String generateRefreshToken(Authentication authenticate) {

		Login user = (Login) authenticate.getPrincipal();

		Date today = new Date();
		Date expirationDate = new Date(today.getTime() + duration);

		String refreshTokenId = java.util.UUID.randomUUID().toString();
		
		Optional<RefreshToken> refreshTokenJaExiste = this.repository.findByLogin(user);
		if(refreshTokenJaExiste.isPresent())
			repository.deleteById(refreshTokenJaExiste.get().getId());

		RefreshToken refreshToken = new RefreshToken(refreshTokenId, expirationDate, user);
		this.repository.save(refreshToken);

		return refreshToken.getId();
	}

	public Optional<RefreshToken> buscarRefreshToken(String refreshToken) {
		Date today = new Date();
		Optional<RefreshToken> refreshTokenExistente = this.repository.findById(refreshToken);
		
		
		if (refreshTokenExistente.isEmpty() || 
				(refreshTokenExistente.isPresent() && refreshTokenExistente.get().getExpiresIn().before(new Date(today.getTime())))) {
			return null;
		}
		
		return refreshTokenExistente;
	}
	
}
