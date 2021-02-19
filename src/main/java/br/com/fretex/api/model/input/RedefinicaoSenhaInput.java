package br.com.fretex.api.model.input;

import javax.validation.constraints.NotBlank;

public class RedefinicaoSenhaInput {
	
	@NotBlank
	private String codigoRecuperacao;
	
	private String novaSenha;

	public String getCodigoRecuperacao() {
		return codigoRecuperacao;
	}

	public void setCodigoRecuperacao(String codigoRecuperacao) {
		this.codigoRecuperacao = codigoRecuperacao;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

}
