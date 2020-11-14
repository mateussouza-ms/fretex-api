package br.com.fretex.api.model.input;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CargaInput {

	@Valid
	@NotNull
	private ClienteIdInput cliente;

	@NotBlank
	@Size(max = 50)
	private String tipoCarga;

	@Valid
	@NotNull
	private BigDecimal peso;

	@Valid
	@NotNull
	private EnderecoInput enderecoRetirada;

	@Valid
	@NotNull
	private EnderecoInput enderecoEntrega;

	@Size(max = 120)
	private String observacoes;

	private LocalDate dataRetiradaPretendida;
	private LocalDate dataEntregaPretendida;
	private Boolean negociaDatas;

	public ClienteIdInput getCliente() {
		return cliente;
	}

	public void setCliente(ClienteIdInput cliente) {
		this.cliente = cliente;
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

	public EnderecoInput getEnderecoRetirada() {
		return enderecoRetirada;
	}

	public void setEnderecoRetirada(EnderecoInput enderecoRetirada) {
		this.enderecoRetirada = enderecoRetirada;
	}

	public EnderecoInput getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(EnderecoInput enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public LocalDate getDataRetiradaPretendida() {
		return dataRetiradaPretendida;
	}

	public void setDataRetiradaPretendida(LocalDate dataRetiradaPretendida) {
		this.dataRetiradaPretendida = dataRetiradaPretendida;
	}

	public LocalDate getDataEntregaPretendida() {
		return dataEntregaPretendida;
	}

	public void setDataEntregaPretendida(LocalDate dataEntregaPretendida) {
		this.dataEntregaPretendida = dataEntregaPretendida;
	}

	public Boolean getNegociaDatas() {
		return negociaDatas;
	}

	public void setNegociaDatas(Boolean negociaDatas) {
		this.negociaDatas = negociaDatas;
	}

}
