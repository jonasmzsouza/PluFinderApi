package br.com.fiap.ambers.PlufinderApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.ambers.PlufinderApi.entity.Chamado;
import br.com.fiap.ambers.PlufinderApi.entity.Usuario;
import br.com.fiap.ambers.PlufinderApi.enumeration.StatusEnum;
import br.com.fiap.ambers.PlufinderApi.repository.ChamadoRepository;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;
	
	@Autowired 
	private UsuarioService usuarioService;
	
	public void gerarChamado(Chamado chamado) {
		this.repository.save(chamado);
	}
	
	public void atenderChamado(Chamado chamado, Usuario usuario) {
		chamado.setUsuarioSolicitado(usuario);
		chamado.setStatus(StatusEnum.RESPONDIDOS);
		repository.save(chamado);
	}
	
	public List<Chamado> buscarTodos() {
		return this.repository.findAll();
	}
	
	public Optional<Chamado> buscarPorId(Long id) {
		return this.repository.findById(id);
	}
	
}
