package br.com.fretex.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fretex.domain.exception.NegocioException;
import br.com.fretex.domain.model.Cliente;
import br.com.fretex.domain.model.SituacaoUsuario;
import br.com.fretex.domain.model.TipoPessoa;
import br.com.fretex.domain.model.Usuario;
import br.com.fretex.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario salvar(Usuario usuario) {
		Usuario usuarioExistente = usuarioRepository.findByCnp(usuario.getCnp());

		if (usuario.getTipoPessoa().equals(TipoPessoa.FÍSICA)) {
			if (usuario.getCnp().length() != 11) {
				throw new NegocioException("CPF inválido. Deve conter 11 caracteres.");
			}

			if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
				throw new NegocioException("Já existe usuário cadastrado com o CPF informado.");
			}
		} else {
			if (usuario.getCnp().length() != 14) {
				throw new NegocioException("CNPJ inválido. Deve conter 14 caracteres.");
			}

			if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
				throw new NegocioException("Já existe usuário cadastrado com o CNPJ informado.");
			}
		}

		usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

		if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
			throw new NegocioException("Já existe usuário cadastrado com o e-mail informado.");
		}

		if (usuarioExistente != null && usuarioExistente.getSituacao().equals(SituacaoUsuario.INATIVO)) {
			throw new NegocioException("O usuário não pode ser atualizado porque está inativo.");
		}

		usuario.setSituacao(SituacaoUsuario.ATIVO);

		return usuarioRepository.save(usuario);
	}

	public void desativar(Usuario usuario) {
		usuario.desativar();

		usuarioRepository.save(usuario);
	}
	
	public Object adicionarPerfil(Object perfil) {
		if (perfil.getClass().equals(Cliente.class)) {
			clienteRepos
		}
	}

}
