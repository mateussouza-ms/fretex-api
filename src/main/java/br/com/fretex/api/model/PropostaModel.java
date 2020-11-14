package br.com.fretex.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public class PropostaModel {
	private Long id;
	private BigDecimal valor;
	private String justificativa;

	private Boolean aceita;
	private UsuarioResumoModel usuarioResponsavel;
	private OffsetDateTime dataCriacao;
	private LocalDate dataRetirada;
	private LocalDate dataEntrega;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Boolean isAceita() {
		return aceita;
	}

	public void setAceita(Boolean aceita) {
		this.aceita = aceita;
	}

	public UsuarioResumoModel getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(UsuarioResumoModel usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

	public OffsetDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(OffsetDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
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
