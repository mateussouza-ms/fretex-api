package br.com.fretex.api.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import br.com.fretex.domain.model.FormaPagamento;

public class FinalizacaoNegociacaoModel {

	private Long id;
	private BigDecimal valorAcordado;
	private Double percentualTaxa;
	private BigDecimal valorTaxa;
	private BigDecimal valorTotal;
	private List<FormaPagamento> formasPagamento;

	public FinalizacaoNegociacaoModel() {
		this.formasPagamento = Arrays.asList(FormaPagamento.values());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorAcordado() {
		return valorAcordado;
	}

	public void setValorAcordado(BigDecimal valorAcordado) {
		this.valorAcordado = valorAcordado;
	}

	public Double getPercentualTaxa() {
		return percentualTaxa;
	}

	public void setPercentualTaxa(Double percentualTaxa) {
		this.percentualTaxa = percentualTaxa;
	}

	public BigDecimal getValorTaxa() {
		return valorTaxa;
	}

	public void setValorTaxa(BigDecimal valorTaxa) {
		this.valorTaxa = valorTaxa;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<FormaPagamento> getFormasPagamento() {
		return formasPagamento;
	}

	public void setFormasPagamento(List<FormaPagamento> formasPagamento) {
		this.formasPagamento = formasPagamento;
	}

}
