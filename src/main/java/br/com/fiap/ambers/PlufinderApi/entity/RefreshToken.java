package br.com.fiap.ambers.PlufinderApi.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_REFRESH_TOKEN")
@NoArgsConstructor
public class RefreshToken {

	public RefreshToken(String refreshTokenId, Date expirationDate, Login user) {
		this.id = refreshTokenId;
		this.expiresIn = expirationDate;
		this.login = user;
	}

	@Getter
	@Id
	private String id;
	
	@Getter
	@Column(name = "expires_in")
	private Date expiresIn;
	
	@Getter
	@OneToOne
	@JoinColumn(name = "id_login", nullable = false, unique = true)
	private Login login;
	
}
