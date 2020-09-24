package br.com.fretex.api.model.input;

import javax.validation.constraints.NotNull;

import br.com.fretex.core.anotations.OnlyTrue;

public class AceitacaoPropostaInput {

	@NotNull
	@OnlyTrue
	private boolean aceita;

	@NotNull
	private Long usuarioId;

	public boolean isAceita() {
		return aceita;
	}

	public void setAceita(boolean aceita) {
		this.aceita = aceita;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

}
