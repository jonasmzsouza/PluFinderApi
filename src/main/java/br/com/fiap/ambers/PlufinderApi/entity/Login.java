/**
 * @author Gabriel
 * @Description Atualiza��o dos relacionamentos
 * @Date 16/05/2021
 */

package br.com.fiap.ambers.PlufinderApi.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.management.relation.Role;
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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "T_LOGIN")
@SequenceGenerator(name = "login", sequenceName="SQ_T_LOGIN", allocationSize = 1)
@Getter
@Setter
@Data
public class Login implements UserDetails {
	
	@Id
	@Column(name="id_login", length = 10, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login")
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario", nullable = false, unique = true)
	private Usuario usuario;
	
	@Column(name = "ds_email", length = 200, nullable = false)
	private String email;
	
	@Column(name = "vl_senha", length = 1500, nullable = false)
	private String senha;

	public Login() {}

	public Login(Usuario usuario, String email, String senha) {
		this.usuario = usuario;
		this.email = email;
		this.senha = senha;
	}

	public Login(Long id, Usuario usuario, String email, String senha) {
		this(usuario, email, senha);
		this.id = id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
//		@SuppressWarnings("unchecked")
//		Collection<? extends GrantedAuthority> roles = (Collection<? extends GrantedAuthority>) new ArrayList<Role>();
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
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
