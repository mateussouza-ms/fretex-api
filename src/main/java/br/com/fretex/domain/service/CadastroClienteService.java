package br.com.fretex.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fretex.domain.exception.NegocioException;
import br.com.fretex.domain.model.Cliente;
import br.com.fretex.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvar(Cliente cliente) {
		Cliente clienteExistente = clienteRepository.findByCnp(cliente.getCnp());
		
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe cliente cadastrado com este CPF/CNPJ");
		}
		
		clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe cliente cadastrado com este email");
		}
		
		return clienteRepository.save(cliente);
	}
	
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}

}
