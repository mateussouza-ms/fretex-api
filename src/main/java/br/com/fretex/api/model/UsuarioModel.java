package br.com.fretex.api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.fretex.domain.model.SituacaoUsuario;
import br.com.fretex.domain.model.Telefone;
import br.com.fretex.domain.model.TipoPessoa;

public class UsuarioModel {
	private Long id;
	private String nome;

	@JsonInclude(Include.NON_NULL)
	private String nomeFantasia;

	private String cnp;
	private TipoPessoa tipoPessoa;
	private LocalDate dataNascimento;
	private String email;
	private EnderecoModel endereco;
	private Telefone telefone;
	private SituacaoUsuario situacaoUsuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public EnderecoModel getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoModel endereco) {
		this.endereco = endereco;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public SituacaoUsuario getSituacaoUsuario() {
		return situacaoUsuario;
	}

	public void setSituacaoUsuario(SituacaoUsuario situacaoUsuario) {
		this.situacaoUsuario = situacaoUsuario;
	}

}
