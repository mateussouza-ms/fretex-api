package br.com.fretex.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import br.com.fretex.api.model.ClienteInput;
import br.com.fretex.api.model.ClienteModel;
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
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public ClienteModel adicionar(@Valid @RequestBody ClienteInput cliente) {		
		return toModel(cadastroClienteService.salvar(toEntity(cliente)));
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<ClienteModel> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		if (!cliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(toModel(cliente.get()));
	}
	
	
	@GetMapping
	public List<ClienteModel> listar() {
		return toCollectionModel(clienteRepository.findAll());
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<ClienteModel> atualizar(@Valid @RequestBody ClienteInput clienteInput, @PathVariable Long clienteId) {
		Optional<Cliente> clienteExistente = clienteRepository.findById(clienteId);
		
		if (!clienteExistente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		var cliente = toEntity(clienteInput);
		
		cliente.setId(clienteId);
		cliente.getEndereco().setId(clienteExistente.get().getEndereco().getId());
		cliente.getTelefone().setId(clienteExistente.get().getTelefone().getId());
		cliente.getUsuario().setId(clienteExistente.get().getUsuario().getId());
		cliente = cadastroClienteService.salvar(cliente);
		return ResponseEntity.ok(toModel(cliente));
	}
	
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> desativar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		if (!cliente.isPresent()) {
			return ResponseEntity.notFound().build();		
		}
		
		cadastroClienteService.desativar(cliente.get());
		
		return ResponseEntity.noContent().build();
	}
	
	
	private ClienteModel toModel(Cliente cliente) {
		return modelMapper.map(cliente, ClienteModel.class);
	}

	private Cliente toEntity(ClienteInput clienteInput) {
		return modelMapper.map(clienteInput, Cliente.class);
	}
	
	private List<ClienteModel> toCollectionModel(List<Cliente> clientes) {
		return clientes.stream()
			.map(cliente -> toModel(cliente))
			.collect(Collectors.toList());
	}
}
