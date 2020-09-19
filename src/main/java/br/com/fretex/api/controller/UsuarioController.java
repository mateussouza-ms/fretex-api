package br.com.fretex.api.controller;

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

import br.com.fretex.api.model.UsuarioInput;
import br.com.fretex.api.model.UsuarioModel;
import br.com.fretex.domain.model.Usuario;
import br.com.fretex.domain.repository.UsuarioRepository;
import br.com.fretex.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	ModelMapper modelMapper;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public UsuarioModel adicionar(@Valid @RequestBody UsuarioInput usuarioInput) {
		return toModel(cadastroUsuarioService.salvar(toEntity(usuarioInput)));
	}

	@GetMapping("/{usuarioId}")
	public ResponseEntity<UsuarioModel> buscar(@PathVariable Long usuarioId) {
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);

		if (!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(toModel(usuario.get()));
	}

	@GetMapping
	public List<UsuarioModel> listar() {
		return toCollectionModel(usuarioRepository.findAll());
	}

	@PutMapping("/{usuarioId}")
	public ResponseEntity<UsuarioModel> atualizar(@Valid @RequestBody UsuarioInput usuarioInput,
			@PathVariable Long usuarioId) {
		Optional<Usuario> usuarioExistente = usuarioRepository.findById(usuarioId);

		if (!usuarioExistente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		var usuario = toEntity(usuarioInput);

		usuario.setId(usuarioId);
		usuario.getEndereco().setId(usuarioExistente.get().getEndereco().getId());
		usuario.getTelefone().setId(usuarioExistente.get().getTelefone().getId());
		usuario = cadastroUsuarioService.salvar(usuario);
		return ResponseEntity.ok(toModel(usuario));
	}

	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<Void> desativar(@PathVariable Long usuarioId) {
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);

		if (!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		cadastroUsuarioService.desativar(usuario.get());

		return ResponseEntity.noContent().build();
	}
	
	private UsuarioModel toModel(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioModel.class);
	}

	private Usuario toEntity(UsuarioInput usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}

	private List<UsuarioModel> toCollectionModel(List<Usuario> usuarios) {
		return usuarios.stream().map(usuario -> toModel(usuario)).collect(Collectors.toList());
	}
}
