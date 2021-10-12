package br.com.fiap.ambers.PlufinderApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.ambers.PlufinderApi.entity.Cargo;
import br.com.fiap.ambers.PlufinderApi.exception.CommitException;
import br.com.fiap.ambers.PlufinderApi.exception.EntityNotFoundException;
import br.com.fiap.ambers.PlufinderApi.repository.CargoRepository;

@Component
public class CargoService {
	
	
	@Autowired
	CargoRepository repository;

	public List<Cargo> buscarTodos() {
		return repository.findAll();
	}

	public Optional<Cargo> buscarPorId(Long id) throws EntityNotFoundException {
		return repository.findById(id);
	}

	public void incluirCargo(Cargo cargo) throws CommitException {
		repository.save(cargo);
	}

	public void alterarCargo(Cargo cargo) throws CommitException {
		if (repository.existsById(cargo.getId()))
			repository.save(cargo);
	}

	public void excluirCargo(Long id) throws EntityNotFoundException, CommitException {
		if (repository.existsById(id))
			repository.deleteById(id);
	}

}
