package br.com.fretex.domain.exception;

public class EntidadeNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String messagem) {
		super(messagem);
	}

}
