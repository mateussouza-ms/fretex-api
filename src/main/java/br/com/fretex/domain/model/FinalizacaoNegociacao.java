package br.com.fretex.domain.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class FinalizacaoNegociacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Min(0)
	private BigDecimal valorAcordado;

	@NotNull
	@Min(0)
	private BigDecimal percentualTaxa;

	@NotNull
	@Min(0)
	private BigDecimal valorTaxa;

	@NotNull
	@Min(0)
	private BigDecimal valorTotal;

	@OneToMany(mappedBy = "finalizacaoNegociacao")
	private List<Pagamento> pagamentos;

	public boolean permitePagamento() {
		BigDecimal totalJaPago = BigDecimal.ZERO;
		
		for (Pagamento pagamento : pagamentos) {
			if(pagamento.getStatus().equals(StatusPagamento.AGUARDANDO_APROVACAO)
					|| pagamento.getStatus().equals(StatusPagamento.APROVADO)) {
				totalJaPago = totalJaPago.add(pagamento.getValorPago());
				
			}
		}
		
		return totalJaPago.compareTo(getValorTotal()) == -1;
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

	public BigDecimal getPercentualTaxa() {
		return percentualTaxa;
	}

	public void setPercentualTaxa(BigDecimal percentualTaxa) {
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

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FinalizacaoNegociacao other = (FinalizacaoNegociacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
