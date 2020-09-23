package br.com.fretex.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fretex.domain.exception.EntidadeNaoEncontradaException;
import br.com.fretex.domain.exception.NegocioException;
import br.com.fretex.domain.model.NegociacaoCarga;
import br.com.fretex.domain.model.Proposta;
import br.com.fretex.domain.model.StatusNegocicacao;
import br.com.fretex.domain.repository.CargaRepository;
import br.com.fretex.domain.repository.NegociacaoCargaRepository;
import br.com.fretex.domain.repository.PropostaRepository;
import br.com.fretex.domain.repository.VeiculoRepository;

@Service
public class GestaoNegociacaoCargaService {

	@Autowired
	public CargaRepository cargaRepository;

	@Autowired
	private NegociacaoCargaRepository negociacaoCargaRepository;

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private PropostaRepository propostaRepository;

	public NegociacaoCarga abrirNegociacao(NegociacaoCarga negociacaoCarga) {

		if (!veiculoRepository.existsById(negociacaoCarga.getVeiculo().getId())) {
			throw new EntidadeNaoEncontradaException(
					"Veículo [" + negociacaoCarga.getVeiculo().getId() + "] não encontrado;");
		}

		if (negociacaoCargaRepository.existsByCargaAndVeiculo(negociacaoCarga.getCarga(),
				negociacaoCarga.getVeiculo())) {
			throw new NegocioException("Já exite negociação aberta para a carga [" + negociacaoCarga.getCarga().getId()
					+ "] e veículo [" + negociacaoCarga.getVeiculo().getId() + "].");
		}

		negociacaoCarga.setStatus(StatusNegocicacao.ABERTA);

		return negociacaoCargaRepository.save(negociacaoCarga);
	}

	public Proposta novaProposta(Proposta proposta) {

		if (!proposta.getNegociacaoCarga().getStatus().equals(StatusNegocicacao.ABERTA)) {
			throw new NegocioException(
					"A negociação [" + proposta.getNegociacaoCarga().getId() + "] já está finalizada.");
		}

		return propostaRepository.save(proposta);
	}
	
	public Proposta contrapropor(Proposta propostaAnterior, Proposta contraproposta) {
		propostaAnterior.setContraproposta(contraproposta);
		
		contraproposta.setNegociacaoCarga(propostaAnterior.getNegociacaoCarga());
		
		return propostaRepository.save(propostaAnterior);
	}

}
