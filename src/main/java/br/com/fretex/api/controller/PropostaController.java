package br.com.fretex.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fretex.api.model.PropostaModel;
import br.com.fretex.api.model.input.AceitacaoPropostaInput;
import br.com.fretex.api.model.input.PropostaInput;
import br.com.fretex.api.util.Mapper;
import br.com.fretex.domain.exception.EntidadeNaoEncontradaException;
import br.com.fretex.domain.model.NegociacaoCarga;
import br.com.fretex.domain.model.Proposta;
import br.com.fretex.domain.repository.CargaRepository;
import br.com.fretex.domain.repository.NegociacaoCargaRepository;
import br.com.fretex.domain.repository.PropostaRepository;
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
	private GestaoNegociacaoCargaService gestaoNegociacaoCargaService;

	@Autowired
	private Mapper mapper;

	@PostMapping
	public PropostaModel contrapropor(@PathVariable Long cargaId, @PathVariable Long negociacaoCargaId,
			@Valid @RequestBody PropostaInput propostaInput) {
		if (!cargaRepository.existsById(cargaId)) {
			throw new EntidadeNaoEncontradaException("Carga [" + cargaId + "] não encontrada.");
		}

		NegociacaoCarga negociacaoCarga = negociacaoCargaRepository.findById(negociacaoCargaId).orElseThrow(
				() -> new EntidadeNaoEncontradaException("Negociação [" + negociacaoCargaId + "] não encontrada."));

		Proposta proposta = mapper.toEntity(propostaInput, Proposta.class);
		proposta.setNegociacaoCarga(negociacaoCarga);

		return mapper.toModel(gestaoNegociacaoCargaService.contrapropor(proposta), PropostaModel.class);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PatchMapping("/{propostaId}")
	public void aceitar(@PathVariable Long propostaId,
			@Valid @RequestBody AceitacaoPropostaInput aceitacaoPropostaInput) {
		Proposta proposta = propostaRepository.findById(propostaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Proposta [" + propostaId + "] não encontrada."));

		gestaoNegociacaoCargaService.aceitarProposta(proposta, aceitacaoPropostaInput.getUsuarioId());

	}
}
