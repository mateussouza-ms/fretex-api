package br.com.fretex.api.model.input;

import javax.validation.constraints.NotNull;

public class NegociacaoCargaInput {
	
	@NotNull
	private Long veiculoId;

	public Long getVeiculoId() {
		return veiculoId;
	}

	public void setVeiculoId(Long veiculoId) {
		this.veiculoId = veiculoId;
	}

}
