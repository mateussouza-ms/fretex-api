package br.com.fretex.domain.exception;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NegocioException(String messagem) {
		super(messagem);
	}
	
}
