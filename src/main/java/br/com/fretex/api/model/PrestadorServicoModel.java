package br.com.fretex.api.model;

import java.util.List;

public class PrestadorServicoModel {
	private Long id;
	private Long usuarioId;
	private List<VeiculoModel> veiculos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
