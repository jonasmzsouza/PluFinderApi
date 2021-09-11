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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_CARGO")
@SequenceGenerator(name = "cargo", sequenceName = "SQ_T_CARGO", allocationSize = 1)
public class Cargo {

	@Id
	@Column(name = "id_cargo", length = 10, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cargo")
	private Long id;

	@Column(name = "nm_cargo", length = 50, nullable = false)
	private String nome;

	@OneToMany(mappedBy = "cargo", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private List<Usuario> usuarios;

	public void addUsuario(Usuario usuario) {
		if (usuarios == null)
			usuarios = new ArrayList<Usuario>();

		usuario.setCargo(this);
		usuarios.add(usuario);
	}

	public Cargo(String nome) {
		this.nome = nome;
	}

	public Cargo(Long id, String nome) {
		this(nome);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	

}
