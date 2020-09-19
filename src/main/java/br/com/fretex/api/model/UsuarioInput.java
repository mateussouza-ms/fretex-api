package br.com.fretex.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioInput {

	@NotBlank
	@Size(max = 120)
	private String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
