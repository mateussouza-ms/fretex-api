package br.com.fretex.api.model.input;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class VeiculoInput {

	@NotNull
	private String nome;

	@NotBlank
	@Size(min = 7, max = 7)
	private String placa;

	@NotNull
	@Min(0)
	private BigDecimal pesoMaximo;

	private String outrasCaracteristicas;

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
