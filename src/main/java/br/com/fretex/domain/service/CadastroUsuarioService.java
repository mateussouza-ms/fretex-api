package br.com.fretex.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fretex.domain.exception.EntidadeNaoEncontradaException;
import br.com.fretex.domain.exception.NegocioException;
import br.com.fretex.domain.model.Cidade;
import br.com.fretex.domain.model.Cliente;
import br.com.fretex.domain.model.PrestadorServico;
import br.com.fretex.domain.model.SituacaoUsuario;
import br.com.fretex.domain.model.TipoPessoa;
import br.com.fretex.domain.model.Usuario;
import br.com.fretex.domain.model.Veiculo;
import br.com.fretex.domain.repository.CidadeRepository;
import br.com.fretex.domain.repository.ClienteRepository;
import br.com.fretex.domain.repository.PrestadorServicoRepository;
import br.com.fretex.domain.repository.UsuarioRepository;
import br.com.fretex.domain.repository.VeiculoRepository;

@Service
public class CadastroUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PrestadorServicoRepository prestadorServicoRepository;

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Usuario salvar(Usuario usuario) {

		if (usuario.getTipoPessoa().equals(TipoPessoa.FÍSICA)) {
			if (usuario.getCnp().length() != 11) {
				throw new NegocioException("CPF inválido. Deve conter 11 caracteres.");
			}

			Optional<Usuario> usuarioExistente = usuarioRepository.findByCnp(usuario.getCnp());

			if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
				throw new NegocioException("Já existe usuário cadastrado com o CPF informado.");
			}
		} else {
			if (usuario.getCnp().length() != 14) {
				throw new NegocioException("CNPJ inválido. Deve conter 14 caracteres.");
			}

			Optional<Usuario> usuarioExistente = usuarioRepository.findByCnp(usuario.getCnp());

			if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
				throw new NegocioException("Já existe usuário cadastrado com o CNPJ informado.");
			}
		}

		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new NegocioException("Já existe usuário cadastrado com o e-mail informado.");
		}

		if (usuarioExistente.isPresent() && usuarioExistente.get().getSituacao().equals(SituacaoUsuario.INATIVO)) {
			throw new NegocioException("O usuário não pode ser atualizado porque está inativo.");
		}

		if (usuario.getEndereco() != null) {
			Optional<Cidade> cidade = cidadeRepository.findById(usuario.getEndereco().getCidade().getId());

			if (!cidade.isPresent()) {
				throw new EntidadeNaoEncontradaException(
						"Cidade [" + usuario.getEndereco().getCidade().getId() + "] não encontrada");
			}

			usuario.getEndereco().setCidade(cidade.get());
		}

		usuario.setSituacao(SituacaoUsuario.ATIVO);

		if(usuario.getId() == null) {
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		}

		return usuarioRepository.save(usuario);
	}

	public void desativar(Usuario usuario) {
		usuario.desativar();

		usuarioRepository.save(usuario);
	}

	public Object adicionarPerfil(Object perfil) {
		if (perfil.getClass().equals(Cliente.class)) {

			var cliente = (Cliente) perfil;

			if (!cliente.getUsuario().getSituacao().equals(SituacaoUsuario.ATIVO)) {
				throw new NegocioException("Usuário não está ativo. Não permitido o cadastro de novo perfil.");
			}

			if (clienteRepository.existsByUsuario(cliente.getUsuario())) {
				throw new NegocioException("Usuário já possui o perfil de cliente.");
			}

			return clienteRepository.save(cliente);
		}

		if (perfil.getClass().equals(PrestadorServico.class)) {

			var prestadorServico = (PrestadorServico) perfil;

			if (!prestadorServico.getUsuario().getSituacao().equals(SituacaoUsuario.ATIVO)) {
				throw new NegocioException("Usuário não está ativo. Não permitido o cadastro de novo perfil.");
			}

			if (prestadorServicoRepository.existsByUsuario(prestadorServico.getUsuario())) {
				throw new NegocioException("Usuário já possui o perfil de prestador de serviço.");
			}

			prestadorServico = prestadorServicoRepository.save(prestadorServico);

			//prestadorServico.setVeiculos(adicionarVeiculos(prestadorServico, prestadorServico.getVeiculos()));

			return prestadorServico;
		}

		throw new NegocioException("Objeto inválido.");
	}

	public Veiculo adicionarVeiculo(PrestadorServico prestadorServico, Veiculo veiculo) {
		veiculo.setPrestadorServico(prestadorServico);
		return veiculoRepository.save(veiculo);
	}

	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário [" + usuarioId + "] não encontrado."));

		if (!senhaAtual.equals(usuario.getSenha())) {
			throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
		}

		usuario.setSenha(novaSenha);
		usuarioRepository.save(usuario);
	}

}
