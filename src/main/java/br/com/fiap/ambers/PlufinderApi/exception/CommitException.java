package br.com.fiap.ambers.PlufinderApi.exception;

public class CommitException extends Exception {

	private static final long serialVersionUID = 5265445285297921334L;
	
	public CommitException() {
		super("Erro ao realizar commit");
	}
	
	public CommitException(String msg) {
		super(msg);
	}

}
