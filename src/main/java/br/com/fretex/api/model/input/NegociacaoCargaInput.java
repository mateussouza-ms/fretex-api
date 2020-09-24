package br.com.fretex.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class NegociacaoCargaInput {

	@NotNull
	private Long veiculoId;

	@Valid
	@NotNull
	private PropostaInput proposta;

	public Long getVeiculoId() {
		return veiculoId;
	}

	public void setVeiculoId(Long veiculoId) {
		this.veiculoId = veiculoId;
	}

	public PropostaInput getProposta() {
		return proposta;
	}

	public void setProposta(PropostaInput proposta) {
		this.proposta = proposta;
	}

}
