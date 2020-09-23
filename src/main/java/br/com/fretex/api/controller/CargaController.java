package br.com.fretex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fretex.api.model.CargaModel;
import br.com.fretex.api.model.input.CargaInput;
import br.com.fretex.api.util.Mapper;
import br.com.fretex.domain.model.Carga;
import br.com.fretex.domain.repository.CargaRepository;
import br.com.fretex.domain.service.GestaoCargasService;

@RestController
@RequestMapping("/cargas")
public class CargaController {

	@Autowired
	private CargaRepository cargaRepository;

	@Autowired
	private GestaoCargasService gestaoCargasService;

	@Autowired
	private Mapper mapper;

	@PostMapping
	public CargaModel ofertar(@RequestBody CargaInput cargaInput) {
		return mapper.toModel(gestaoCargasService.salvar(mapper.toModel(cargaInput, Carga.class)), CargaModel.class);
	}

	@GetMapping
	public List<CargaModel> listar() {
		return mapper.toCollectionModel(cargaRepository.findAll(), CargaModel.class);
	}

}
