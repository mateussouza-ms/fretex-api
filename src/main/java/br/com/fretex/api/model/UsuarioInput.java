package br.com.fretex.api.model;

import java.time.LocalDate;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.fretex.domain.model.Telefone;
import br.com.fretex.domain.model.TipoPessoa;

public class UsuarioInput {

	@NotBlank
	@Size(max = 120)
	private String nome;

	@Size(max = 120)
	private String nomeFantasia;

	@NotBlank
	@Size(min = 11, max = 14)
	private String cnp;

	@NotNull
	private TipoPessoa tipoPessoa;

	@NotNull
	@Temporal(TemporalType.DATE)
	private LocalDate dataNascimento;

	@NotBlank
	@Email
	private String email;

	@Valid
	@NotNull
	private EnderecoInput endereco;

	@Valid
	@NotNull
	private Telefone telefone;

	@NotBlank
	@Size(max = 120)
	private String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EnderecoInput getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoInput endereco) {
		this.endereco = endereco;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
