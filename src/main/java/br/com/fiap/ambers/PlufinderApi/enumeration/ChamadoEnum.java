package br.com.fiap.ambers.PlufinderApi.enumeration;

public enum ChamadoEnum {
	
	EMERGENCIAEXTREMA(0, "EMERGENCIA EXTREMA"), EMERGENCIA(1, "EMERGENCIA"), CHAMADOSIMPLES (2, "CHAMADO SIMPLES");
	
	private int idTipoChamado;
	private String descricaoTipoChamado;
	
	private ChamadoEnum(int idTipoChamado, String descricaoTipoChamado) {
		this.idTipoChamado = idTipoChamado;
		this.descricaoTipoChamado = descricaoTipoChamado;
	}

	public long getIdTipoChamado() {
		return idTipoChamado;
	}

	public void setIdTipoChamado(int idTipoChamado) {
		this.idTipoChamado = idTipoChamado;
	}

	public String getDescricaoTipoChamado() {
		return descricaoTipoChamado;
	}

	public void setDescricaoTipoChamado(String descricaoTipoChamado) {
		this.descricaoTipoChamado = descricaoTipoChamado;
	}
	
}
