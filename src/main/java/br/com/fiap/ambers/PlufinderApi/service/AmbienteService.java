package br.com.fiap.ambers.PlufinderApi.service;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

import br.com.fiap.tds.dao.AmbienteDao;
import br.com.fiap.tds.dao.impl.AmbienteDaoImpl;
import br.com.fiap.tds.entity.Ambiente;
import br.com.fiap.tds.exception.CommitException;
import br.com.fiap.tds.exception.EntityNotFoundException;
import br.com.fiap.tds.singleton.EntityManagerFactorySingleton;

@Component
public class AmbienteService {
	
	EntityManager em;
	AmbienteDao ambienteDao;
	
	AmbienteService() {
		 this.em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		 this.ambienteDao = new AmbienteDaoImpl(em);
	}
	
	public Ambiente buscarPorId(Long id) throws EntityNotFoundException {
		return ambienteDao.read(id);
	}
	
	public void incluirAmbiente(Ambiente ambiente) throws CommitException {
		ambienteDao.create(ambiente);
		ambienteDao.commit();
	}
	
	public void alterarAmbiente(Ambiente ambiente) throws CommitException {
		ambienteDao.update(ambiente);
		ambienteDao.commit();
	}
	
	public void excluirAmbiente(Long id) throws CommitException, EntityNotFoundException {
		ambienteDao.delete(id);
		ambienteDao.commit();
	}
	
	

}
