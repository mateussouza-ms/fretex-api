package br.com.fretex.api.model;

import java.math.BigDecimal;

public class VeiculoModel {
	private Long id;
	private Long prestadorServicoId;
	private String nome;
	private String placa;
	private BigDecimal pesoMaximo;
	private String outrasCaracteristicas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPrestadorServicoId() {
		return prestadorServicoId;
	}

	public void setPrestadorServicoId(Long prestadorServicoId) {
		this.prestadorServicoId = prestadorServicoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public BigDecimal getPesoMaximo() {
		return pesoMaximo;
	}

	public void setPesoMaximo(BigDecimal pesoMaximo) {
		this.pesoMaximo = pesoMaximo;
	}

	public String getOutrasCaracteristicas() {
		return outrasCaracteristicas;
	}

	public void setOutrasCaracteristicas(String outrasCaracteristicas) {
		this.outrasCaracteristicas = outrasCaracteristicas;
	}

}
