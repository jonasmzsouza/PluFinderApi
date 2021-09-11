package br.com.fiap.ambers.PlufinderApi.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.ambers.PlufinderApi.entity.Ambiente;
import br.com.fiap.ambers.PlufinderApi.exception.CommitException;
import br.com.fiap.ambers.PlufinderApi.exception.EntityNotFoundException;
import br.com.fiap.ambers.PlufinderApi.repository.AmbienteRepository;

@Component
public class AmbienteService {
	
	@Autowired
	AmbienteRepository repository;
	
	public List<Ambiente> buscarTodos() {
		return repository.findAll();
	}
	
	public Optional<Ambiente> buscarPorId(Long id) throws EntityNotFoundException {
		return repository.findById(id);
	}
	
	public void incluirAmbiente(Ambiente ambiente) throws CommitException {
		repository.save(ambiente);
	}
	
	public void alterarAmbiente(Ambiente ambiente) throws CommitException {
		if (repository.existsById(ambiente.getId()));
			repository.save(ambiente);
	}
	
	public void excluirAmbiente(Long id) throws CommitException, EntityNotFoundException {
		if (repository.existsById(id));
		repository.deleteById(id);
	}
	
	

}
