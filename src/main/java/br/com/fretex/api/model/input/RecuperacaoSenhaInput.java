package br.com.fretex.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RecuperacaoSenhaInput {

	@NotBlank
	@Email
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
