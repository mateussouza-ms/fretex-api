package br.com.fretex.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fretex.api.model.CargaModel;
import br.com.fretex.api.model.input.CargaInput;
import br.com.fretex.api.model.input.DataHoraInput;
import br.com.fretex.api.util.Mapper;
import br.com.fretex.domain.exception.EntidadeNaoEncontradaException;
import br.com.fretex.domain.model.Carga;
import br.com.fretex.domain.repository.CargaRepository;
import br.com.fretex.domain.service.GestaoCargasService;

@RestController
@RequestMapping("/cargas")
public class CargaController {

	@Autowired
	private GestaoCargasService gestaoCargasService;
	
	@Autowired
	private CargaRepository cargaRepository;

	@Autowired
	private Mapper mapper;

	@PostMapping
	public CargaModel ofertar(@RequestBody CargaInput cargaInput) {
		return mapper.toModel(gestaoCargasService.salvar(mapper.toModel(cargaInput, Carga.class)), CargaModel.class);
	}

	@GetMapping
	public List<CargaModel> listarComFiltro(@Nullable @RequestParam String situacao, @RequestParam Long usuarioId,
			@RequestParam String usuarioPerfil) {

		return mapper.toCollectionModel(gestaoCargasService.listarComFiltro(situacao, usuarioId, usuarioPerfil),
				CargaModel.class);
	}
	
	@GetMapping("/{cargaId}")
	public CargaModel buscar(@PathVariable Long cargaId) {
		Carga carga = cargaRepository.findById(cargaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Carga [" + cargaId + "] não encontrada"));
		
		return mapper.toModel(carga, CargaModel.class);
	}

	@PutMapping("/{cargaId}/dataRetirada")
	public CargaModel informarRetirada(@PathVariable Long cargaId, @RequestBody @Valid DataHoraInput dataRetiradaInput){
		Carga carga = cargaRepository.findById(cargaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Carga [" + cargaId + "] não encontrada"));

		carga.setDataRetirada(dataRetiradaInput.getDataHora());

		return mapper.toModel(gestaoCargasService.salvar(carga), CargaModel.class);
	}

	@PutMapping("/{cargaId}/dataEntrega")
	public CargaModel informarEntrega(@PathVariable Long cargaId, @RequestBody @Valid DataHoraInput dataEntregaInput){
		Carga carga = cargaRepository.findById(cargaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Carga [" + cargaId + "] não encontrada"));

		carga.setDataEntrega(dataEntregaInput.getDataHora());

		return mapper.toModel(gestaoCargasService.salvar(carga), CargaModel.class);
	}
}
