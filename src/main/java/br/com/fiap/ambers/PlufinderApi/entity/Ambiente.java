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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_AMBIENTE")
@SequenceGenerator(name = "ambiente", sequenceName="SQ_T_AMBIENTE", allocationSize = 1)
public class Ambiente {
	
	@Id
	@Column(name="id_ambiente", length = 10, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ambiente")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "id_setor", nullable = false)
	private Setor setor;
	
	@Column(name = "nm_ambiente", length = 50, nullable = false)
	private String nome;
	
	@Column(name = "nr_andar", nullable = false)
	private Integer andar;
	
	@Column(name = "nr_tamanho", nullable = false)
	private Double tamanho;
	
	@Column(name = "nr_proximidade", nullable = false)
	private Double numeroProximidade;

	@Column(name = "nm_localizacao", length = 50, nullable = false)
	private String nomeLocalizacao;
	
	@OneToOne(mappedBy = "ambiente", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private Dispositivo dispositivo;
	

	public Ambiente(Setor setor, String nome, Integer andar, Double tamanho, Double numeroProximidade,
			String nomeLocalizacao) {
		this.setor = setor;
		this.nome = nome;
		this.andar = andar;
		this.tamanho = tamanho;
		this.numeroProximidade = numeroProximidade;
		this.nomeLocalizacao = nomeLocalizacao;
	}
	
	public Ambiente(Long id, Setor setor, String nome, Integer andar, Double tamanho, Double numeroProximidade,
			String nomeLocalizacao) {
		this(setor, nome, andar, tamanho, numeroProximidade, nomeLocalizacao);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getAndar() {
		return andar;
	}

	public void setAndar(Integer andar) {
		this.andar = andar;
	}

	public Double getTamanho() {
		return tamanho;
	}

	public void setTamanho(Double tamanho) {
		this.tamanho = tamanho;
	}

	public Double getNumeroProximidade() {
		return numeroProximidade;
	}

	public void setNumeroProximidade(Double numeroProximidade) {
		this.numeroProximidade = numeroProximidade;
	}

	public String getNomeLocalizacao() {
		return nomeLocalizacao;
	}

	public void setNomeLocalizacao(String nomeLocalizacao) {
		this.nomeLocalizacao = nomeLocalizacao;
	}

	public Dispositivo getDispositivo() {
		return dispositivo;
	}

	public void setDispositivo(Dispositivo dispositivo) {
		this.dispositivo = dispositivo;
	}	
	
	
}
