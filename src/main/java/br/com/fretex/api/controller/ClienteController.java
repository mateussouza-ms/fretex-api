package br.com.fretex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fretex.api.model.ClienteModel;
import br.com.fretex.api.util.Mapper;
import br.com.fretex.domain.exception.EntidadeNaoEncontradaException;
import br.com.fretex.domain.model.Cliente;
import br.com.fretex.domain.model.Usuario;
import br.com.fretex.domain.repository.ClienteRepository;
import br.com.fretex.domain.repository.UsuarioRepository;
import br.com.fretex.domain.service.CadastroUsuarioService;

@RestController
public class ClienteController {

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private Mapper mapper;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/usuarios/{usuarioId}/perfil/cliente")
	public ClienteModel adicionar(@PathVariable Long usuarioId) {
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

		Cliente cliente = new Cliente();

		cliente.setUsuario(usuario);

		cliente = (Cliente) cadastroUsuarioService.adicionarPerfil(cliente);
		return mapper.toModel(cliente, ClienteModel.class);
	}

	@GetMapping("/usuarios/{usuarioId}/perfil/cliente")
	public List<ClienteModel> buscar(@PathVariable Long usuarioId) {
		var usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado."));
		
		var clientes = clienteRepository.findByUsuario(usuario);

		return mapper.toCollectionModel(clientes, ClienteModel.class);
	}

	@GetMapping("/clientes")
	public List<ClienteModel> listar() {
		var clientes = clienteRepository.findAll();

		return mapper.toCollectionModel(clientes, ClienteModel.class);
	}

}
