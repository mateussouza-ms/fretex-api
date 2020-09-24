package br.com.fretex.api.model;

import java.util.List;

import br.com.fretex.domain.model.StatusNegocicacao;

public class NegociacaoCargaModel {
	private Long id;
	private VeiculoModel veiculo;
	private StatusNegocicacao status;
	private List<PropostaModel> propostas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VeiculoModel getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(VeiculoModel veiculo) {
		this.veiculo = veiculo;
	}

	public StatusNegocicacao getStatus() {
		return status;
	}

	public void setStatus(StatusNegocicacao status) {
		this.status = status;
	}

	public List<PropostaModel> getPropostas() {
		return propostas;
	}

	public void setPropostas(List<PropostaModel> propostas) {
		this.propostas = propostas;
	}

}
