package br.com.fretex.api.model;

import java.util.List;

import javax.validation.Valid;

import com.sun.istack.NotNull;

public class PrestadorServicoInput {
	
	@Valid
	@NotNull
	private List<VeiculoInput> veiculos;

	public List<VeiculoInput> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<VeiculoInput> veiculos) {
		this.veiculos = veiculos;
	}

}
