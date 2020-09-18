package br.com.fretex.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fretex.domain.model.Cliente;
import br.com.fretex.domain.repository.ClienteRepository;
import br.com.fretex.domain.service.CadastroClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CadastroClienteService cadastroClienteService;
	
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {		
		return cadastroClienteService.salvar(cliente);
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		if (!cliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(cliente.get());
	}
	
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @RequestBody Cliente cliente, @PathVariable Long clienteId) {
		Optional<Cliente> clienteExistente = clienteRepository.findById(clienteId);
		
		if (!clienteExistente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		cliente.getEndereco().setId(clienteExistente.get().getEndereco().getId());
		cliente.getTelefone().setId(clienteExistente.get().getTelefone().getId());
		cliente = cadastroClienteService.salvar(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> excluir(@PathVariable Long clienteId) {
		if (clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();		
		}
		
		cadastroClienteService.excluir(clienteId);
		
		return ResponseEntity.noContent().build();
	}
	

}
