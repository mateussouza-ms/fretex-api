package br.com.fretex.domain.service;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fretex.domain.exception.EntidadeNaoEncontradaException;
import br.com.fretex.domain.model.Carga;
import br.com.fretex.domain.model.Cidade;
import br.com.fretex.domain.repository.CargaRepository;
import br.com.fretex.domain.repository.CidadeRepository;
import br.com.fretex.domain.repository.ClienteRepository;

@Service
public class GestaoCargasService {

	@Autowired
	public CargaRepository cargaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

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

}
