package br.com.fretex.api.model;

public class CidadeModel {
	private Long id;
	private String nome;
	private UFModel uf;

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

	public UFModel getUf() {
		return uf;
	}

	public void setUf(UFModel uf) {
		this.uf = uf;
	}

}
