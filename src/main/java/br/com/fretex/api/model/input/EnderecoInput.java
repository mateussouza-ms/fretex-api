package br.com.fretex.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EnderecoInput {

	@NotBlank
	@Size(min = 8, max = 8)
	private String cep;

	@NotBlank
	@Size(max = 120)
	private String logradouro;

	@NotBlank
	@Size(max = 20)
	private String numero;

	@NotBlank
	@Size(max = 50)
	private String bairro;

	@NotBlank
	@Size(max = 120)
	private String complemento;

	@Valid
	@NotNull
	private CidadeIdInput cidade;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public CidadeIdInput getCidade() {
		return cidade;
	}

	public void setCidade(CidadeIdInput cidade) {
		this.cidade = cidade;
	}

}
