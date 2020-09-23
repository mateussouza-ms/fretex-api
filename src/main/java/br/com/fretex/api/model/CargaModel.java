package br.com.fretex.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class CargaModel {
	private Long id;
	private Long clienteId;
	private String tipoCarga;
	private BigDecimal peso;
	private EnderecoModel enderecoRetirada;
	private EnderecoModel enderecoEntrega;
	private String observacoes;
	private OffsetDateTime dataCadastro;
	private OffsetDateTime dataRetirada;
	private OffsetDateTime dataEntrega;
	private List<NegociacaoCargaModel> negociacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public String getTipoCarga() {
		return tipoCarga;
	}

	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public EnderecoModel getEnderecoRetirada() {
		return enderecoRetirada;
	}

	public void setEnderecoRetirada(EnderecoModel enderecoRetirada) {
		this.enderecoRetirada = enderecoRetirada;
	}

	public EnderecoModel getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(EnderecoModel enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public OffsetDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(OffsetDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public OffsetDateTime getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(OffsetDateTime dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public OffsetDateTime getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(OffsetDateTime dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public List<NegociacaoCargaModel> getNegociacoes() {
		return negociacoes;
	}

	public void setNegociacoes(List<NegociacaoCargaModel> negociacoes) {
		this.negociacoes = negociacoes;
	}

}
