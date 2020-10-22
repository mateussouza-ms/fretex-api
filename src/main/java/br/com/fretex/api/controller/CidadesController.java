package br.com.fretex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fretex.domain.model.Cidade;
import br.com.fretex.domain.repository.CidadeRepository;

@RestController
@RequestMapping("/cidades")
public class CidadesController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@GetMapping
	public List<Cidade> buscar(@RequestParam("uf") String uf) {
		return cidadeRepository.findByUfSigla(uf);
	}
}
