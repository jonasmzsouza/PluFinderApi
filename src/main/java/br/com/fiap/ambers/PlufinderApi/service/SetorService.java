package br.com.fiap.ambers.PlufinderApi.service;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import br.com.fiap.tds.dao.SetorDao;
import br.com.fiap.tds.dao.impl.SetorDaoImpl;
import br.com.fiap.tds.entity.Setor;
import br.com.fiap.tds.exception.CommitException;
import br.com.fiap.tds.exception.EntityNotFoundException;
import br.com.fiap.tds.singleton.EntityManagerFactorySingleton;

@Component
public class SetorService {

	EntityManager em;
	SetorDao setorDao;

	SetorService() {
		this.em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		this.setorDao = new SetorDaoImpl(em);
	}

	public Setor buscarPorId(Long id) throws EntityNotFoundException {
		return setorDao.read(id);
	}

	public void incluirSetor(Setor setor) throws CommitException {
		setorDao.create(setor);
		setorDao.commit();
	}
	
	public void alterarSetor(Setor setor) throws CommitException {
		setorDao.update(setor);
		setorDao.commit();
	}
	
	public void excluirSetor(Long id) throws EntityNotFoundException, CommitException {
		setorDao.delete(id);
		setorDao.commit();
	}
}
