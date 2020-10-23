package br.com.fretex.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fretex.domain.model.Usuario;
import br.com.fretex.domain.repository.ClienteRepository;
import br.com.fretex.domain.repository.PrestadorServicoRepository;
import br.com.fretex.domain.repository.UsuarioRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PrestadorServicoRepository prestadorServicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);

		if (!usuario.isPresent()) {
			usuario = usuarioRepository.findByCnp(username);
		}

		if (!usuario.isPresent()) {
			throw new UsernameNotFoundException("Usuário não encontrado com o e-mail informado");
		}

		List<GrantedAuthority> authorities = new ArrayList<>();

		if (prestadorServicoRepository.findByUsuario(usuario.get()).isPresent()) {
			authorities.add(new SimpleGrantedAuthority("PRESTADOR_SERVICO"));
		}

		if (clienteRepository.findByUsuarioId(usuario.get().getId()).isPresent()) {
			authorities.add(new SimpleGrantedAuthority("CLIENTE"));
		}

		return new AuthUser(usuario.get(), authorities);
	}

}
