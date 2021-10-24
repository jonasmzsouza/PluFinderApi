package br.com.fiap.ambers.PlufinderApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.ambers.PlufinderApi.entity.Login;
import br.com.fiap.ambers.PlufinderApi.entity.Usuario;

public interface LoginRepository extends JpaRepository<Login, Long> {	
	Optional<Login> findByEmail(String email);
	Optional<Login> findByUsuario(Usuario usuario);
}
