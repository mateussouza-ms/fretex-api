package br.com.fretex.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class PropostaModel {
	private Long id;
	private BigDecimal valor;
	private String justificativa;
	private boolean aceita;
	private Long usuarioResponsavelId;
	private OffsetDateTime dataCriacao;

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

	public boolean isAceita() {
		return aceita;
	}

	public void setAceita(boolean aceita) {
		this.aceita = aceita;
	}

	public Long getUsuarioResponsavelId() {
		return usuarioResponsavelId;
	}

	public void setUsuarioResponsavelId(Long usuarioResponsavelId) {
		this.usuarioResponsavelId = usuarioResponsavelId;
	}

	public OffsetDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(OffsetDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
