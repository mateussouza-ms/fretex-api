package br.com.fretex.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class PropostaModel {
	private Long id;
	private BigDecimal valor;
	private String justificativa;
	
	private Boolean aceita;
	private UsuarioResumoModel usuarioResponsavel;
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

}
