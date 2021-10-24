/** MANUTENCAO DE FONTE
 * @author Gabriel
 * @Description Implementa��o dos relacionamentos
 * @Date 11/05/2021
 * 
 * @author Gabriel
 * @Description Atualiza��o dos relacionamentos
 * @Date 16/05/2021
 */

package br.com.fiap.ambers.PlufinderApi.entity;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import br.com.fiap.ambers.PlufinderApi.InDto.CreateChamadoEntradaDto;
import br.com.fiap.ambers.PlufinderApi.enumeration.ChamadoEnum;
import br.com.fiap.ambers.PlufinderApi.enumeration.StatusEnum;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "T_CHAMADO")
@SequenceGenerator(name = "chamado", sequenceName="SQ_T_CHAMADO", allocationSize = 1)
@Getter
@Setter
public class Chamado {
	
	@Id
	@Column(name="id_chamado", length = 10, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chamado")
	private Long id;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "id_status", nullable = false, length = 50)
	private StatusEnum status;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "id_tipo_chamado", nullable = false, length = 50)
	private ChamadoEnum tipoChamado;
	
	@CreationTimestamp
	@Column(name = "dt_hr_chamado", updatable = false)
	private LocalDateTime dataEHora;	
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_solicitante")
	private Usuario usuarioSolicitante;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario_solicitado")
	private Usuario usuarioSolicitado;
	
	@ManyToOne
	@JoinColumn(name = "id_setor_chamado")
	private Setor setor;

	public Chamado() {}

	public Chamado(StatusEnum status, ChamadoEnum tipoChamado, Calendar dataEHora) {
		this.status = status;
		this.tipoChamado = tipoChamado;
	}

	public Chamado(Long id, StatusEnum status, ChamadoEnum tipoChamado, Calendar dataEHora) {
		this(status, tipoChamado, dataEHora);
		this.id = id;
	}
	
	public Chamado(CreateChamadoEntradaDto novoChamado, Usuario usuarioSolicitante) {
		this.status = StatusEnum.PENDENTES;
		this.tipoChamado = novoChamado.getTipoChamado();
		this.dataEHora = LocalDateTime.now();
		this.usuarioSolicitante = usuarioSolicitante;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public ChamadoEnum getTipoChamado() {
		return tipoChamado;
	}

	public void setTipoChamado(ChamadoEnum tipoChamado) {
		this.tipoChamado = tipoChamado;
	}

	public LocalDateTime getDataEHora() {
		return dataEHora;
	}

	public void setDataEHora(LocalDateTime dataEHora) {
		this.dataEHora = dataEHora;
	}

	
}
