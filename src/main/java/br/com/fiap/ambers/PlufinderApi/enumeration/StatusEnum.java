package br.com.fiap.ambers.PlufinderApi.enumeration;

public enum StatusEnum {
	
	PENDENTES(0, "PENDENTES"), RESPONDIDOS(1, "RESPONDIDOS"), CONCLUIDOS (2, "CONCLUIDOS");
	
	private int IdStatusChamado;
	private String descricaoStatusChamado;
	
	private StatusEnum(int IdStatusChamado, String descricaoStatusChamado) {
		this.IdStatusChamado = 0;
		this.descricaoStatusChamado = "PENDENTES";
	}

	public long getIdStatusChamado() {
		return IdStatusChamado;
	}

	public void setIdStatusChamado(int idStatusChamado) {
		IdStatusChamado = idStatusChamado;
	}

	public String getDescricaoStatusChamado() {
		return descricaoStatusChamado;
	}

	public void setDescricaoStatusChamado(String descricaoStatusChamado) {
		this.descricaoStatusChamado = descricaoStatusChamado;
	}
	
}
