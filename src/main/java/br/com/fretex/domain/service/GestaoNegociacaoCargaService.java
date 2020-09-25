package br.com.fretex.domain.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fretex.domain.exception.EntidadeNaoEncontradaException;
import br.com.fretex.domain.exception.NegocioException;
import br.com.fretex.domain.model.FinalizacaoNegociacao;
import br.com.fretex.domain.model.NegociacaoCarga;
import br.com.fretex.domain.model.ParametrosConfig;
import br.com.fretex.domain.model.Proposta;
import br.com.fretex.domain.model.StatusNegocicacao;
import br.com.fretex.domain.model.Usuario;
import br.com.fretex.domain.repository.CargaRepository;
import br.com.fretex.domain.repository.NegociacaoCargaRepository;
import br.com.fretex.domain.repository.ParametrosConfigRepository;
import br.com.fretex.domain.repository.PropostaRepository;
import br.com.fretex.domain.repository.UsuarioRepository;
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

	@Autowired
	private ParametrosConfigRepository parametrosConfigRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public NegociacaoCarga novaNegociacao(NegociacaoCarga negociacaoCarga) {

		if (negociacaoCargaRepository.existsByCargaAndStatus(negociacaoCarga.getCarga(),
				StatusNegocicacao.FINALIZADA_COM_ACORDO)) {
			throw new NegocioException(
					"A carga [" + negociacaoCarga.getCarga().getId() + "] já possui negociação finalizada com acordo.");
		}

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

	public Proposta contrapropor(Proposta contraproposta) {

		validarContraproposta(contraproposta);

		Long negociacaoCargaId = contraproposta.getNegociacaoCarga().getId();

		Proposta ultimaProposta = propostaRepository
				.findTopByNegociacaoCargaIdOrderByDataCriacaoDesc(negociacaoCargaId);

		if (contraproposta.getUsuarioResponsavel().equals(ultimaProposta.getUsuarioResponsavel())) {
			throw new NegocioException("Não é permitido ao usuário [" + contraproposta.getUsuarioResponsavel().getId()
					+ "] fazer nova proposta enquanto a última não tiver sido respondida.");
		}

		ParametrosConfig parametrosConfig = parametrosConfigRepository.findTopByOrderByIdDesc();
		Integer limitePropostas = parametrosConfig.getLimitePropostas();

		if (limitePropostas != null
				&& propostaRepository.countByNegociacaoCargaId(negociacaoCargaId) >= limitePropostas) {
			throw new NegocioException(
					"Limite de propostas atingido. São permitidas no máximo " + limitePropostas + " propostas.");
		}

		contraproposta.setDataCriacao(OffsetDateTime.now());

		return propostaRepository.save(contraproposta);
	}

	public FinalizacaoNegociacao aceitarProposta(Proposta proposta, Long usuarioId) {
		validarContraproposta(proposta);

		Long negociacaoCargaId = proposta.getNegociacaoCarga().getId();

		if (!usuarioFazParteNegociacao(negociacaoCargaId, usuarioId)) {
			throw new NegocioException(
					"O usuário [" + usuarioId + "] não faz parte da negociação [" + negociacaoCargaId + "].");
		}

		Proposta ultimaProposta = propostaRepository
				.findTopByNegociacaoCargaIdOrderByDataCriacaoDesc(negociacaoCargaId);

		if (!proposta.equals(ultimaProposta)) {
			throw new NegocioException("A proposta [" + proposta.getId()
					+ "] não pode ser aceita. Somente pode ser aceita a última proposta feita.");
		}

		if (ultimaProposta.getUsuarioResponsavel().getId().equals(usuarioId)) {
			throw new NegocioException("Uma proposta não pode ser aceita pelo mesmo usuário que a fez.");
		}

		proposta.setAceita(true);
		proposta.getNegociacaoCarga().setStatus(StatusNegocicacao.FINALIZADA_COM_ACORDO);

		propostaRepository.save(proposta);

		ParametrosConfig parametrosConfig = parametrosConfigRepository.findTopByOrderByIdDesc();

		FinalizacaoNegociacao finalizacaoNegociacao = new FinalizacaoNegociacao();
		BigDecimal valorAcordado = proposta.getValor();
		BigDecimal percentualTaxa = parametrosConfig.getPercentualTaxa();
		BigDecimal valorTaxa = valorAcordado.multiply(percentualTaxa);
		BigDecimal valorTotal = valorAcordado.add(valorTaxa);

		finalizacaoNegociacao.setValorAcordado(valorAcordado);
		finalizacaoNegociacao.setPercentualTaxa(percentualTaxa);
		finalizacaoNegociacao.setValorTaxa(valorTaxa);
		finalizacaoNegociacao.setValorTotal(valorTotal);

		NegociacaoCarga negociacaoCarga = proposta.getNegociacaoCarga();
		negociacaoCarga.setFinalizacaoNegociacao(finalizacaoNegociacao);
		negociacaoCarga = negociacaoCargaRepository.save(negociacaoCarga);

		return negociacaoCarga.getFinalizacaoNegociacao();
	}

	public void cancelarNegociacao(NegociacaoCarga negociacaoCarga) {
		if (!negociacaoCarga.getStatus().equals(StatusNegocicacao.ABERTA)) {
			throw new NegocioException("A negociação [" + negociacaoCarga.getId() + "] não pode ser cancelada.");
		}

		negociacaoCarga.setStatus(StatusNegocicacao.FINALIZADA_SEM_ACORDO);

		negociacaoCargaRepository.save(negociacaoCarga);

	}

	private void validarContraproposta(Proposta proposta) {
		if (!proposta.getNegociacaoCarga().getStatus().equals(StatusNegocicacao.ABERTA)) {
			throw new NegocioException(
					"A negociação [" + proposta.getNegociacaoCarga().getId() + "] já está finalizada.");
		}

		Long negociacaoCargaId = proposta.getNegociacaoCarga().getId();

		if (!usuarioFazParteNegociacao(negociacaoCargaId, proposta.getUsuarioResponsavel().getId())) {
			throw new NegocioException("O usuário [" + proposta.getUsuarioResponsavel().getId()
					+ "] não faz parte da negociação [" + negociacaoCargaId + "].");
		}

		if (propostaRepository.existsByNegociacaoCargaIdAndAceitaTrue(negociacaoCargaId)) {
			throw new NegocioException("Já existe uma proposta aceita para a negociação [" + negociacaoCargaId + "]");
		}

	}

	private boolean usuarioFazParteNegociacao(Long negociacaoCargaId, Long usuarioId) {
		Usuario usuarioVerificacao = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário [" + usuarioId + "] não encontrado."));

		Usuario clienteNegociacao = negociacaoCargaRepository.getClienteFromNegociacao(negociacaoCargaId);
		Usuario prestadorNegociacao = negociacaoCargaRepository.getPrestadorFromNegociacao(negociacaoCargaId);

		if (usuarioVerificacao.equals(clienteNegociacao)) {
			return true;
		}

		if (usuarioVerificacao.equals(prestadorNegociacao)) {
			return true;
		}

		return false;
	}

}
