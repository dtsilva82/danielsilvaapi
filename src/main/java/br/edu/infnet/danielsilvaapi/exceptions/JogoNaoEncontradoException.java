package br.edu.infnet.danielsilvaapi.exceptions;

public class JogoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public JogoNaoEncontradoException(String message) {
		super(message);
	}

}
