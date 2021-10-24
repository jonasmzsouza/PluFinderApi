package br.com.fiap.ambers.PlufinderApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.ambers.PlufinderApi.entity.Login;
import br.com.fiap.ambers.PlufinderApi.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
	Optional<RefreshToken> findByLogin(Login login);
}
