package br.com.fiap.ambers.PlufinderApi.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.fiap.ambers.PlufinderApi.entity.Login;
import br.com.fiap.ambers.PlufinderApi.entity.RefreshToken;
import br.com.fiap.ambers.PlufinderApi.entity.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class TokenService {

	@Value("${plufinderapi.jwt.duration}")
	private long duration;

	@Value("${plufinderapi.jwt.secret}")
	private String secret;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	public String createToken(RefreshToken refreshToken) {
		return this.generateToken(refreshToken.getLogin());
	}

	public String createToken(Authentication authenticate) {
		Login user = (Login) authenticate.getPrincipal();		
		return this.generateToken(user);
	}
	
	public String generateToken(Login user) {
		Date today = new Date();
		Date expirationDate = new Date(today.getTime() + duration);
		
		return Jwts.builder()
				.setIssuer("PluFinderApi")
				.setSubject(user.getId()
						.toString())
				.setIssuedAt(today)
				.setExpiration(expirationDate).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean valid(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			
			return true;
		} catch (UnsupportedJwtException | MalformedJwtException | SignatureException
				| IllegalArgumentException e) {
			return false;
		} catch (ExpiredJwtException e) {
			return false;
		}
	}

	public Long getUserId(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return Long.valueOf(claims.getSubject());
	}

}
