package br.com.fretex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fretex.domain.model.UF;
import br.com.fretex.domain.repository.EstadosRepository;

@RestController
@RequestMapping("/estados")
public class EstadosController {
	
	@Autowired
	private EstadosRepository estadosRepository;
	
	@GetMapping
	public List<UF> listar() {
		return estadosRepository.findAllOrderByNomeAsc();
	}

}
