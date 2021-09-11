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

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import br.com.fiap.ambers.PlufinderApi.enumeration.ChamadoEnum;
import br.com.fiap.ambers.PlufinderApi.enumeration.StatusEnum;


@Entity
@Table(name = "T_CHAMADO")
@SequenceGenerator(name = "chamado", sequenceName="SQ_T_CHAMADO", allocationSize = 1)
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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_hr_chamado", updatable = false)
	private Calendar dataEHora;	
	
	@ManyToMany(mappedBy = "chamados")
	private List<Usuario> usuarios;

	public Chamado() {}

	public Chamado(StatusEnum status, ChamadoEnum tipoChamado, Calendar dataEHora) {
		this.status = status;
		this.tipoChamado = tipoChamado;
		this.dataEHora = dataEHora;
	}

	public Chamado(Long id, StatusEnum status, ChamadoEnum tipoChamado, Calendar dataEHora) {
		this(status, tipoChamado, dataEHora);
		this.id = id;
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

	public Calendar getDataEHora() {
		return dataEHora;
	}

	public void setDataEHora(Calendar dataEHora) {
		this.dataEHora = dataEHora;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}	
	
	
	
}
