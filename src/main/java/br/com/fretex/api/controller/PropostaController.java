package br.com.fretex.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fretex.api.model.FinalizacaoNegociacaoModel;
import br.com.fretex.api.model.NegociacaoCargaModel;
import br.com.fretex.api.model.input.AceitacaoPropostaInput;
import br.com.fretex.api.model.input.PropostaInput;
import br.com.fretex.api.util.Mapper;
import br.com.fretex.domain.exception.EntidadeNaoEncontradaException;
import br.com.fretex.domain.model.FinalizacaoNegociacao;
import br.com.fretex.domain.model.NegociacaoCarga;
import br.com.fretex.domain.model.Proposta;
import br.com.fretex.domain.model.Usuario;
import br.com.fretex.domain.repository.CargaRepository;
import br.com.fretex.domain.repository.NegociacaoCargaRepository;
import br.com.fretex.domain.repository.PropostaRepository;
import br.com.fretex.domain.repository.UsuarioRepository;
import br.com.fretex.domain.service.GestaoNegociacaoCargaService;

@RestController
@RequestMapping("/cargas/{cargaId}/negociacoes/{negociacaoCargaId}/propostas")
public class PropostaController {

	@Autowired
	private CargaRepository cargaRepository;

	@Autowired
	private NegociacaoCargaRepository negociacaoCargaRepository;

	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private GestaoNegociacaoCargaService gestaoNegociacaoCargaService;

	@Autowired
	private Mapper mapper;

	@PostMapping
	public NegociacaoCargaModel contrapropor(@PathVariable Long cargaId, @PathVariable Long negociacaoCargaId,
			@Valid @RequestBody PropostaInput propostaInput) {
		if (!cargaRepository.existsById(cargaId)) {
			throw new EntidadeNaoEncontradaException("Carga [" + cargaId + "] não encontrada.");
		}

		NegociacaoCarga negociacaoCarga = negociacaoCargaRepository.findById(negociacaoCargaId).orElseThrow(
				() -> new EntidadeNaoEncontradaException("Negociação [" + negociacaoCargaId + "] não encontrada."));

		Proposta proposta = mapper.toEntity(propostaInput, Proposta.class);
		proposta.setNegociacaoCarga(negociacaoCarga);
		
		Long usuarioId = proposta.getUsuarioResponsavel().getId();
		Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(
				() -> new EntidadeNaoEncontradaException("Usuário [" + usuarioId + "] não encontrado."));
		
		proposta.setUsuarioResponsavel(usuario);
		
		proposta = gestaoNegociacaoCargaService.contrapropor(proposta);
				
		return mapper.toModel(negociacaoCarga, NegociacaoCargaModel.class);
	}

	@PatchMapping("/{propostaId}")
	public FinalizacaoNegociacaoModel aceitar(@PathVariable Long propostaId,
			@Valid @RequestBody AceitacaoPropostaInput aceitacaoPropostaInput) {
		Proposta proposta = propostaRepository.findById(propostaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Proposta [" + propostaId + "] não encontrada."));

		FinalizacaoNegociacao finalizacaoNegociacao = gestaoNegociacaoCargaService.aceitarProposta(proposta,
				aceitacaoPropostaInput.getUsuarioId());

		return mapper.toModel(finalizacaoNegociacao, FinalizacaoNegociacaoModel.class);
	}
}
