package br.com.fiap.ambers.PlufinderApi.InDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class RefreshTokenEntradaDto {
	
	@JsonProperty("refresh_token")
	private String refreshToken;
}
