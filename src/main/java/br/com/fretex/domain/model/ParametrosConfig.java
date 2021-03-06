package br.com.fretex.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ParametrosConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer limitePropostas;

	@NotNull
	private BigDecimal percentualTaxa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLimitePropostas() {
		return limitePropostas;
	}

	public void setLimitePropostas(Integer limitePropostas) {
		this.limitePropostas = limitePropostas;
	}

	public BigDecimal getPercentualTaxa() {
		return percentualTaxa;
	}

	public void setPercentualTaxa(BigDecimal percentualTaxa) {
		this.percentualTaxa = percentualTaxa;
	}

}
