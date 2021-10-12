package br.com.fiap.ambers.PlufinderApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.ambers.PlufinderApi.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	List<Usuario> findByNomeContaining(String nome);
}
