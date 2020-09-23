package br.com.fretex.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fretex.api.model.NegociacaoCargaModel;
import br.com.fretex.api.model.input.NegociacaoCargaInput;
import br.com.fretex.api.util.Mapper;
import br.com.fretex.domain.exception.EntidadeNaoEncontradaException;
import br.com.fretex.domain.model.Carga;
import br.com.fretex.domain.model.NegociacaoCarga;
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
	public NegociacaoCargaModel adicionar(@PathVariable Long cargaId,
			@Valid @RequestBody NegociacaoCargaInput negociacaoCargaInput) {
		Optional<Carga> carga = cargaRepository.findById(cargaId);

		if (!carga.isPresent()) {
			throw new EntidadeNaoEncontradaException("Carga [" + cargaId + "] não encontrada.");
		}

		NegociacaoCarga negociacaoCarga = mapper.toEntity(negociacaoCargaInput, NegociacaoCarga.class);
		negociacaoCarga.setCarga(carga.get());

		return mapper.toModel(gestaoNegociacaoCargaService.abrirNegociacao(negociacaoCarga),
				NegociacaoCargaModel.class);
	}

	@GetMapping
	public List<NegociacaoCargaModel> listar(@PathVariable Long cargaId) {
		List<NegociacaoCarga> negociacoesCarga = negociacaoCargaRepository.findByCargaId(cargaId);
		return mapper.toCollectionModel(negociacoesCarga, NegociacaoCargaModel.class);
	}

}
