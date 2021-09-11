/** MANUTEN��O DE FONTE
 * @author Gabriel
 * @Description Implementa��o dos relacionamentos
 * @Date 11/05/2021
 * 
 * @author Gabriel
 * @Description Atualiza��o dos relacionamentos
 * @Date 16/05/2021
 */

package br.com.fiap.ambers.PlufinderApi.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.fiap.tds.enumeration.TipoDispositivoEnum;

@Entity
@Table(name = "T_DISPOSITIVO")
@SequenceGenerator(name = "dispositivo", sequenceName="SQ_T_DISPOSITIVO", allocationSize = 1)
public class Dispositivo {
	
	@Id
	@Column(name="id_dispositivo", length = 10, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dispositivo")
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario", unique = true)
	private Usuario usuario;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_ambiente", unique = true)
	private Ambiente ambiente;
	
	@Column(name = "nm_dispositivo", length = 50, nullable = false)
	private String nome;
	
	private TipoDispositivoEnum tipo;

	public Dispositivo() {}

	public Dispositivo(Usuario usuario, String nome) {
		this.tipo = TipoDispositivoEnum.WEAREABLE;
		this.usuario = usuario;
		this.nome = nome;
	}

	public Dispositivo(Ambiente ambiente, String nome) {
		this.tipo = TipoDispositivoEnum.AMBIENTE;
		this.nome = nome;
		this.ambiente = ambiente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Ambiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(Ambiente ambiente) {
		this.ambiente = ambiente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoDispositivoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoDispositivoEnum tipo) {
		if (this.usuario != null) 
			this.tipo = TipoDispositivoEnum.WEAREABLE;
		else
		this.tipo = TipoDispositivoEnum.AMBIENTE;
	}	
	
	
}
