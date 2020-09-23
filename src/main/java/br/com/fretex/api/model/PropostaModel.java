package br.com.fretex.api.model;

import java.math.BigDecimal;

public class PropostaModel {
	private Long id;
	private BigDecimal valor;
	private String justificativa;
	private boolean aceita;
	private PropostaModel contraproposta;
	private Long usuarioResponsavelId;

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

	public PropostaModel getContraproposta() {
		return contraproposta;
	}

	public void setContraproposta(PropostaModel contraproposta) {
		this.contraproposta = contraproposta;
	}

	public Long getUsuarioResponsavelId() {
		return usuarioResponsavelId;
	}

	public void setUsuarioResponsavelId(Long usuarioResponsavelId) {
		this.usuarioResponsavelId = usuarioResponsavelId;
	}

}
