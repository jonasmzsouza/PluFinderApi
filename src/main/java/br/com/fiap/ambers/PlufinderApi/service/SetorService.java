package br.com.fiap.ambers.PlufinderApi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.ambers.PlufinderApi.entity.Setor;
import br.com.fiap.ambers.PlufinderApi.exception.CommitException;
import br.com.fiap.ambers.PlufinderApi.exception.EntityNotFoundException;
import br.com.fiap.ambers.PlufinderApi.repository.SetorRepository;

@Component
public class SetorService {

	@Autowired
	SetorRepository repository;

	public Optional<Setor> buscarPorId(Long id) throws EntityNotFoundException {
		return repository.findById(id);
	}

	public void incluirSetor(Setor setor) throws CommitException {
		repository.save(setor);
	}

	public void alterarSetor(Setor setor) throws CommitException {
		if (repository.existsById(setor.getId()))
			repository.save(setor);
	}

	public void excluirSetor(Long id) throws EntityNotFoundException, CommitException {
		if (repository.existsById(id))
			repository.deleteById(id);
	}
}
