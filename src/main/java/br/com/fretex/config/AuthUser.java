package br.com.fretex.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.fretex.domain.model.Usuario;

public class AuthUser extends User {

	private static final long serialVersionUID = 1L;

	private Long usuarioId;
	private String usuarioNome;

	public AuthUser(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);

		setUsuarioId(usuario.getId());
		setUsuarioNome(usuario.getNome());
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getUsuarioNome() {
		return usuarioNome;
	}

	public void setUsuarioNome(String usuarioNome) {
		this.usuarioNome = usuarioNome;
	}

}
