package br.com.fiap.ambers.PlufinderApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.ambers.PlufinderApi.entity.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

}
