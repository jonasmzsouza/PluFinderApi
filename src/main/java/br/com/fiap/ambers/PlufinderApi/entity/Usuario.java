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

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_USUARIO")
@SequenceGenerator(name = "usuario", sequenceName="SQ_T_USUARIO", allocationSize = 1)
public class Usuario implements UserDetails {
	
	@Id
	@Column(name="id_usuario", length = 10, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario")
	private Long id;
	
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Login login;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "id_cargo", nullable = false)
	private Cargo cargo;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "id_setor", nullable = false)
	private Setor setor;
	
	@OneToOne(mappedBy = "usuario", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private Dispositivo dispostivo;
	
	@Column(name = "nm_usuario", length = 100, nullable = false)
	private String nome;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "T_USU_CHAMADO",
				joinColumns = @JoinColumn(name="id_usuario"),
				inverseJoinColumns = @JoinColumn(name="id_chamado"))
	private List<Chamado> chamados;

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

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Dispositivo getDispostivo() {
		return dispostivo;
	}

	public void setDispostivo(Dispositivo dispostivio) {
		this.dispostivo = dispostivio;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

	public Usuario(String nome2, Setor setor2, Cargo cargo2) {
		this.nome = nome2;
		this.setor = setor2;
		this.cargo = cargo2;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return this.login.getSenha();
	}

	@Override
	public String getUsername() {
		return this.login.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	} 

}
