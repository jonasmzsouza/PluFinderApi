package br.com.fiap.ambers.PlufinderApi.exception;

public class EntityNotFoundException extends Exception {

	private static final long serialVersionUID = -3099123070991256668L;
	
	public EntityNotFoundException() {
		super("Entidade n�o encontrada");
	}
	
	public EntityNotFoundException(String msg) {
		super(msg);
	}
}
