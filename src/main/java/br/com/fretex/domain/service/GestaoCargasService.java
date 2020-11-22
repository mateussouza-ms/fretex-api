package br.com.fretex.domain.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fretex.domain.exception.EntidadeNaoEncontradaException;
import br.com.fretex.domain.model.Carga;
import br.com.fretex.domain.model.Cidade;
import br.com.fretex.domain.model.Cliente;
import br.com.fretex.domain.model.PrestadorServico;
import br.com.fretex.domain.model.StatusNegocicacao;
import br.com.fretex.domain.repository.CargaRepository;
import br.com.fretex.domain.repository.CidadeRepository;
import br.com.fretex.domain.repository.ClienteRepository;
import br.com.fretex.domain.repository.PrestadorServicoRepository;

@Service
public class GestaoCargasService {

	@Autowired
	public CargaRepository cargaRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PrestadorServicoRepository prestadorServicoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	public Carga salvar(Carga carga) {
		if (!clienteRepository.existsById(carga.getCliente().getId())) {
			throw new EntidadeNaoEncontradaException("Cliente não encontrado.");
		}

		Optional<Cidade> cidadeRetirada = cidadeRepository.findById(carga.getEnderecoRetirada().getCidade().getId());

		if (!cidadeRetirada.isPresent()) {
			throw new EntidadeNaoEncontradaException(
					"Cidade [" + carga.getEnderecoRetirada().getCidade().getId() + "] não encontrada");
		}

		Optional<Cidade> cidadeEntrega = cidadeRepository.findById(carga.getEnderecoEntrega().getCidade().getId());

		if (!cidadeEntrega.isPresent()) {
			throw new EntidadeNaoEncontradaException(
					"Cidade [" + carga.getEnderecoEntrega().getCidade().getId() + "] não encontrada");
		}

		carga.getEnderecoRetirada().setCidade(cidadeRetirada.get());
		carga.getEnderecoEntrega().setCidade(cidadeEntrega.get());
		carga.setDataCadastro(OffsetDateTime.now());

		return cargaRepository.save(carga);
	}

	public List<Carga> listarComFiltro(String situacao, Long usuarioId, String usuarioPerfil) {

		if (usuarioPerfil.equals("CLIENTE")) {
			Cliente cliente = clienteRepository.findByUsuarioId(usuarioId)
					.orElseThrow(() -> new EntidadeNaoEncontradaException(
							"O usuário [" + usuarioId + "] não posssui o perfil [" + usuarioPerfil + "]"));

			if (situacao != null && situacao.equals("cadastradas")) {
				return cargaRepository.findByNegociacoesIsNullAndCliente(cliente);
			}

			if (situacao != null && situacao.equals("em-negociacao")) {
				return cargaRepository.findByNegociacoesStatusAndCliente(StatusNegocicacao.ABERTA, cliente);
			}

			if (situacao != null && situacao.equals("aguardando-retirada")) {
				return cargaRepository.findByDataRetiradaIsNullAndNegociacoesStatusAndCliente(
						StatusNegocicacao.FINALIZADA_COM_ACORDO, cliente);
			}

			if (situacao != null && situacao.equals("em-transporte")) {
				return cargaRepository.findByDataEntregaIsNullAndDataRetiradaIsNotNullAndNegociacoesStatusAndCliente(
						StatusNegocicacao.FINALIZADA_COM_ACORDO, cliente);
			}

			if (situacao != null && situacao.equals("entregues")) {
				return cargaRepository.findByDataEntregaIsNotNullAndNegociacoesStatusAndCliente(
						StatusNegocicacao.FINALIZADA_COM_ACORDO, cliente);
			}
			
			if (situacao != null && situacao.equals("finalizadas-sem-acordo")) {
				return cargaRepository.listarFinalizadasSemAcordoCliente(cliente);
			}
		}

		if (usuarioPerfil.equals("PRESTADOR_SERVICOS")) {
			
			PrestadorServico prestadorServico = prestadorServicoRepository.findByUsuarioId(usuarioId)
					.orElseThrow(() -> new EntidadeNaoEncontradaException(
							"O usuário [" + usuarioId + "] não posssui o perfil [" + usuarioPerfil + "]"));
			
			if (situacao != null && situacao.equals("cadastradas")) {
				return cargaRepository.findByNegociacoesIsNullOrNegociacoesStatusAndNegociacoesVeiculoPrestadorServicoNot(StatusNegocicacao.ABERTA, prestadorServico);
			}

			if (situacao != null && situacao.equals("em-negociacao")) {
				return cargaRepository.findByNegociacoesStatusAndNegociacoesVeiculoPrestadorServico(StatusNegocicacao.ABERTA, prestadorServico);
			}

			if (situacao != null && situacao.equals("aguardando-retirada")) {
				return cargaRepository
						.findByDataRetiradaIsNullAndNegociacoesStatusAndNegociacoesVeiculoPrestadorServico(StatusNegocicacao.FINALIZADA_COM_ACORDO, prestadorServico);
			}

			if (situacao != null && situacao.equals("em-transporte")) {
				return cargaRepository.findByDataEntregaIsNullAndDataRetiradaIsNotNullAndNegociacoesStatusAndNegociacoesVeiculoPrestadorServico(
						StatusNegocicacao.FINALIZADA_COM_ACORDO, prestadorServico);
			}

			if (situacao != null && situacao.equals("entregues")) {
				return cargaRepository.findByDataEntregaIsNotNullAndNegociacoesStatusAndNegociacoesVeiculoPrestadorServico(
						StatusNegocicacao.FINALIZADA_COM_ACORDO, prestadorServico);
			}
			
			if (situacao != null && situacao.equals("finalizadas-sem-acordo")) {
				return cargaRepository.findByNegociacoesStatusAndNegociacoesVeiculoPrestadorServico(StatusNegocicacao.FINALIZADA_SEM_ACORDO, prestadorServico);
			}

		}
		return new ArrayList<Carga>();

	}
}
