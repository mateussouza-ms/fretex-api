package br.com.fretex.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fretex.api.model.UsuarioModel;
import br.com.fretex.api.model.input.SenhaInput;
import br.com.fretex.api.model.input.UsuarioInput;
import br.com.fretex.api.model.input.UsuarioUpdateInput;
import br.com.fretex.api.util.Mapper;
import br.com.fretex.config.AuthUser;
import br.com.fretex.domain.exception.NegocioException;
import br.com.fretex.domain.model.RecuperacaoSenha;
import br.com.fretex.domain.model.Usuario;
import br.com.fretex.domain.repository.RecuperacaoSenhaRepository;
import br.com.fretex.domain.repository.UsuarioRepository;
import br.com.fretex.domain.service.CadastroUsuarioService;
import br.com.fretex.domain.service.RecuperacaoSenhaService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RecuperacaoSenhaRepository recuperacaoSenhaRepository;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private RecuperacaoSenhaService recuperacaoSenhaService;

	@Autowired
	private Mapper mapper;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public UsuarioModel adicionar(@Valid @RequestBody UsuarioInput usuarioInput) {
		return mapper.toModel(cadastroUsuarioService.salvar(mapper.toEntity(usuarioInput, Usuario.class)),
				UsuarioModel.class);
	}

	@GetMapping("/{usuarioId}")
	public ResponseEntity<UsuarioModel> buscar(@PathVariable Long usuarioId) {
		AuthUser usuarioLogado = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (!usuarioLogado.getUsuarioId().equals(usuarioId)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);

		if (!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(mapper.toModel(usuario.get(), UsuarioModel.class));
	}

	@GetMapping
	public List<UsuarioModel> listar() {
		return mapper.toCollectionModel(usuarioRepository.findAll(), UsuarioModel.class);
	}

	@PutMapping("/{usuarioId}")
	public ResponseEntity<UsuarioModel> atualizar(@Valid @RequestBody UsuarioUpdateInput usuarioInput,
			@PathVariable Long usuarioId) {
		AuthUser usuarioLogado = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (!usuarioLogado.getUsuarioId().equals(usuarioId)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findById(usuarioId);

		if (!usuarioExistente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		var usuario = mapper.toEntity(usuarioInput, Usuario.class);

		usuario.setId(usuarioId);
		usuario.getTelefone().setId(usuarioExistente.get().getTelefone().getId());
		usuario.setSenha(usuarioExistente.get().getSenha());
		usuario = cadastroUsuarioService.salvar(usuario);
		return ResponseEntity.ok(mapper.toModel(usuario, UsuarioModel.class));
	}

	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<Void> desativar(@PathVariable Long usuarioId) {
		AuthUser usuarioLogado = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (!usuarioLogado.getUsuarioId().equals(usuarioId)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);

		if (!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		cadastroUsuarioService.desativar(usuario.get());

		return ResponseEntity.noContent().build();
	}

	@PostMapping("recuperacao-senha")
	public ResponseEntity<Void> recuperarSenha(@RequestParam String email) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

		if (!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		recuperacaoSenhaService.recuperarSenha(usuario.get());

		return ResponseEntity.noContent().build();
	}

	@PostMapping("redefinicao-senha")
	public ResponseEntity<Void> redefinirSenha(
			@RequestParam String codigoRecuperacao,
			@RequestParam String novaSenha) {

		RecuperacaoSenha recuperacaoSenha = recuperacaoSenhaRepository.findByCodigo(codigoRecuperacao)
				.orElseThrow(() -> new NegocioException("Código de recuperação de senha inválido."));

		recuperacaoSenha.getUsuario().setSenha(novaSenha);
		recuperacaoSenhaService.redefinirSenha(recuperacaoSenha);

		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
		AuthUser usuarioLogado = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (!usuarioLogado.getUsuarioId().equals(usuarioId)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		cadastroUsuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
		return ResponseEntity.noContent().build();
	}

}
