package br.com.fretex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fretex.api.model.PagamentoModel;
import br.com.fretex.api.model.input.PagamentoInput;
import br.com.fretex.api.util.Mapper;
import br.com.fretex.domain.exception.EntidadeNaoEncontradaException;
import br.com.fretex.domain.model.NegociacaoCarga;
import br.com.fretex.domain.model.Pagamento;
import br.com.fretex.domain.repository.CargaRepository;
import br.com.fretex.domain.repository.NegociacaoCargaRepository;
import br.com.fretex.domain.service.PagamentoService;

@RestController
@RequestMapping("/cargas/{cargaId}/negociacoes/{negociacaoCargaId}/pagamentos")
public class PagamentoController {

	@Autowired
	private CargaRepository cargaRepository;

	@Autowired
	private NegociacaoCargaRepository negociacaoCargaRepository;
	
	@Autowired
	private PagamentoService pagamentoService;
	
	@Autowired
	private Mapper mapper;

	@PostMapping
	public PagamentoModel adicionar(@PathVariable Long cargaId, @PathVariable Long negociacaoCargaId,
			@RequestBody PagamentoInput pagamentoInput) {

		if (!cargaRepository.existsById(cargaId)) {
			throw new EntidadeNaoEncontradaException("Carga [" + cargaId + "] não encontrada.");
		}

		NegociacaoCarga negociacaoCarga = negociacaoCargaRepository.findById(negociacaoCargaId).orElseThrow(
				() -> new EntidadeNaoEncontradaException("Negociação [" + negociacaoCargaId + "] não encontrada."));

		Pagamento pagamento = mapper.toEntity(pagamentoInput, Pagamento.class);
		
		pagamento.setFinalizacaoNegociacao(negociacaoCarga.getFinalizacaoNegociacao());
		
		pagamento = pagamentoService.adicionar(pagamento);
		
		return mapper.toModel(pagamento, PagamentoModel.class);
	}

}
