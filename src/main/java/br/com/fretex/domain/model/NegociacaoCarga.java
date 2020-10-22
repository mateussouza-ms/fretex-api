package br.com.fretex.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

@Entity
public class NegociacaoCarga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Carga carga;

	@ManyToOne
	private Veiculo veiculo;

	@Enumerated(EnumType.STRING)
	private StatusNegocicacao status;

	@OneToOne(cascade = CascadeType.ALL)
	private FinalizacaoNegociacao finalizacaoNegociacao;

	@OneToMany(mappedBy = "negociacaoCarga", cascade = CascadeType.ALL)
	@OrderBy("id ASC")
	private List<Proposta> propostas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Carga getCarga() {
		return carga;
	}

	public void setCarga(Carga carga) {
		this.carga = carga;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public StatusNegocicacao getStatus() {
		return status;
	}

	public void setStatus(StatusNegocicacao status) {
		this.status = status;
	}

	public FinalizacaoNegociacao getFinalizacaoNegociacao() {
		return finalizacaoNegociacao;
	}

	public void setFinalizacaoNegociacao(FinalizacaoNegociacao finalizacaoNegociacao) {
		this.finalizacaoNegociacao = finalizacaoNegociacao;
	}

	public List<Proposta> getPropostas() {
		return propostas;
	}

	public void setPropostas(List<Proposta> propostas) {
		this.propostas = propostas;
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
		NegociacaoCarga other = (NegociacaoCarga) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
