package br.com.fretex.api.controller;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fretex.api.model.CargaModel;
import br.com.fretex.api.model.NegociacaoCargaModel;
import br.com.fretex.api.model.input.NegociacaoCargaInput;
import br.com.fretex.api.util.Mapper;
import br.com.fretex.domain.exception.EntidadeNaoEncontradaException;
import br.com.fretex.domain.model.Carga;
import br.com.fretex.domain.model.NegociacaoCarga;
import br.com.fretex.domain.model.Proposta;
import br.com.fretex.domain.repository.CargaRepository;
import br.com.fretex.domain.repository.NegociacaoCargaRepository;
import br.com.fretex.domain.service.GestaoNegociacaoCargaService;

@RestController
@RequestMapping("/cargas/{cargaId}/negociacoes")
public class NegociacaoCargaController {

	@Autowired
	private CargaRepository cargaRepository;

	@Autowired
	private NegociacaoCargaRepository negociacaoCargaRepository;

	@Autowired
	private GestaoNegociacaoCargaService gestaoNegociacaoCargaService;

	@Autowired
	private Mapper mapper;

	@PostMapping
	public CargaModel adicionar(@PathVariable Long cargaId,
			@Valid @RequestBody NegociacaoCargaInput negociacaoCargaInput) {
		Carga carga = cargaRepository.findById(cargaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Carga [" + cargaId + "] não encontrada."));

		NegociacaoCarga negociacaoCarga = mapper.toEntity(negociacaoCargaInput, NegociacaoCarga.class);
		Proposta proposta = mapper.toEntity(negociacaoCargaInput.getProposta(), Proposta.class);
		proposta.setNegociacaoCarga(negociacaoCarga);
		proposta.setDataCriacao(OffsetDateTime.now());
		negociacaoCarga.setCarga(carga);		
		negociacaoCarga.setPropostas(new ArrayList<Proposta>());		
		negociacaoCarga.getPropostas().add(proposta);
		
		negociacaoCarga = gestaoNegociacaoCargaService.novaNegociacao(negociacaoCarga);

		carga = negociacaoCarga.getCarga();
		
		return mapper.toModel(carga, CargaModel.class);
	}

	@GetMapping
	public List<NegociacaoCargaModel> listar(@PathVariable Long cargaId) {
		cargaRepository.findById(cargaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Carga [" + cargaId + "] não encontrada."));

		List<NegociacaoCarga> negociacoesCarga = negociacaoCargaRepository.findByCargaId(cargaId);
		return mapper.toCollectionModel(negociacoesCarga, NegociacaoCargaModel.class);
	}
	
	@GetMapping("/{negociacaoCargaId}")
	public ResponseEntity<NegociacaoCargaModel> buscar(@PathVariable Long negociacaoCargaId) {
		Optional<NegociacaoCarga> negociacaoCarga = negociacaoCargaRepository.findById(negociacaoCargaId);

		if (!negociacaoCarga.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(mapper.toModel(negociacaoCarga.get(), NegociacaoCargaModel.class));
	}
	
	@DeleteMapping("/{negociacaoCargaId}")
	public void cancelar(@PathVariable Long cargaId, @PathVariable Long negociacaoCargaId) {
		Carga carga = cargaRepository.findById(cargaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Carga [" + cargaId + "] não encontrada."));
		
		NegociacaoCarga negociacaoCarga = negociacaoCargaRepository.findByIdAndCarga(negociacaoCargaId, carga)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Negociação [" + negociacaoCargaId + "] não encontrada."));

		gestaoNegociacaoCargaService.cancelarNegociacao(negociacaoCarga);
	}

}
