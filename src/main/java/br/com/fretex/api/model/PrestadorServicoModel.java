package br.com.fretex.api.model;

import java.util.List;

public class PrestadorServicoModel {
	private Long usuarioId;
	private List<VeiculoModel> veiculos;

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public List<VeiculoModel> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<VeiculoModel> veiculos) {
		this.veiculos = veiculos;
	}

}
