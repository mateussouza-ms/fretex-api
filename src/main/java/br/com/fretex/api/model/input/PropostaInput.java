package br.com.fretex.api.model.input;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PropostaInput {

	@NotNull
	@Min(0)
	private BigDecimal valor;

	@Size(min = 1, max = 120)
	private String justificativa;

	@Valid
	@NotNull
	private UsuarioIdInput usuarioResponsavel;

	private LocalDate dataRetirada;
	private LocalDate dataEntrega;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public UsuarioIdInput getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(UsuarioIdInput usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

	public LocalDate getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(LocalDate dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

}
