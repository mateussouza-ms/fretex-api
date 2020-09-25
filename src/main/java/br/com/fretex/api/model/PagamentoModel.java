package br.com.fretex.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import br.com.fretex.domain.model.FormaPagamento;
import br.com.fretex.domain.model.StatusPagamento;

public class PagamentoModel {

	private Long id;
	private OffsetDateTime dataPagamento;
	private BigDecimal valorPago;
	private FormaPagamento formaPagamento;
	private Integer numeroParcelas;
	private StatusPagamento status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OffsetDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(OffsetDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

	public StatusPagamento getStatus() {
		return status;
	}

	public void setStatus(StatusPagamento status) {
		this.status = status;
	}

}
