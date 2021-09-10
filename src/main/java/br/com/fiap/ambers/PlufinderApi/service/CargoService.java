package br.com.fiap.ambers.PlufinderApi.service;

import javax.persistence.EntityManager;

import br.com.fiap.tds.dao.CargoDao;
import br.com.fiap.tds.dao.impl.CargoDaoImpl;
import br.com.fiap.tds.entity.Cargo;
import br.com.fiap.tds.exception.CommitException;
import br.com.fiap.tds.singleton.EntityManagerFactorySingleton;

public class CargoService {
	
	EntityManager em;
	CargoDao cargoDao;

	CargoService() {
		this.em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		this.cargoDao = new CargoDaoImpl(em);
	}
	
	public void incluirCargo(Cargo cargo) throws CommitException {
		cargoDao.create(cargo);
		cargoDao.commit();
	}

}
