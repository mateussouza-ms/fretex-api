package br.com.fretex.api.model.input;

import javax.validation.Valid;

import com.sun.istack.NotNull;

public class PrestadorServicoInput {
	
	@Valid
	@NotNull
	private VeiculoInput veiculo;

	public VeiculoInput getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(VeiculoInput veiculo) {
		this.veiculo = veiculo;
	}

}
