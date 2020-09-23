package br.com.fretex.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PropostaInput {

	@NotNull
	@Min(0)
	private BigDecimal valor;

	@Size(min = 1, max = 120)
	private String justificativa;

	@Valid
	@NotNull
	private UsuarioIdInput usuarioResponsavel;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public UsuarioIdInput getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(UsuarioIdInput usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

}
