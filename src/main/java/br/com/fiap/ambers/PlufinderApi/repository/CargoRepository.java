package br.com.fiap.ambers.PlufinderApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.ambers.PlufinderApi.entity.Ambiente;
import br.com.fiap.ambers.PlufinderApi.entity.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long>{

}
