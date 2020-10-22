package br.com.fretex.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fretex.domain.exception.NegocioException;
import br.com.fretex.domain.model.FinalizacaoNegociacao;
import br.com.fretex.domain.model.Pagamento;
import br.com.fretex.domain.model.StatusPagamento;
import br.com.fretex.domain.repository.PagamentoRepository;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;

	public Pagamento adicionar(Pagamento pagamento) {

		FinalizacaoNegociacao finalizacaoNegociacao = pagamento.getFinalizacaoNegociacao();

		if (!finalizacaoNegociacao.permitePagamento()) {
			throw new NegocioException("A negociação já está totalmente paga.");			
		}
		
		if (!pagamento.getValorPago().equals(finalizacaoNegociacao.getValorTotal())) {
			throw new NegocioException("Valor do pagamento está diferente do valor total a ser pago.");
		}

		pagamento.setStatus(StatusPagamento.AGUARDANDO_APROVACAO);
		pagamento.setDataPagamento(OffsetDateTime.now());

		return pagamentoRepository.save(pagamento);
	}
}
