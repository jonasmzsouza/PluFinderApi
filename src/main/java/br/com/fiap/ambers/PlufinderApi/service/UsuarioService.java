package br.com.fiap.ambers.PlufinderApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.fiap.ambers.PlufinderApi.entity.Ambiente;
import br.com.fiap.ambers.PlufinderApi.entity.Usuario;
import br.com.fiap.ambers.PlufinderApi.exception.CommitException;
import br.com.fiap.ambers.PlufinderApi.exception.EntityNotFoundException;
import br.com.fiap.ambers.PlufinderApi.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository repository;
	
	public List<Usuario > buscarTodos() {
		return repository.findAll();
	}
	
	public Optional<Usuario> buscarPorId(Long id) throws EntityNotFoundException {
		return repository.findById(id);
	}
	
	public List<Usuario> buscarPorNomeQueContenha(String nome) {
		return repository.findByNomeContaining(nome);
	}
	
	public void incluirUsuario(Usuario usuario) throws CommitException {
		repository.save(usuario);
	}
	
	public void alterarUsuario(Usuario usuario) throws CommitException {
		if (repository.existsById(usuario.getId()));
			repository.save(usuario);
	}
	
	public void excluirUsuario(Long id) throws CommitException, EntityNotFoundException {
		if (repository.existsById(id));
		repository.deleteById(id);
	}
	
	
	
	

}
