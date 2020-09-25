package br.com.fretex.api.model.input;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.fretex.domain.model.FormaPagamento;

public class PagamentoInput {

	@NotNull
	@Min(0)
	private double valorPago;

	@NotNull
	private FormaPagamento formaPagamento;

	@NotNull
	@Min(1)
	private Integer numeroParcelas;

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
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

}
