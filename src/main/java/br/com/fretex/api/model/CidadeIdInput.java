package br.com.fretex.api.model;

import javax.validation.constraints.NotNull;

public class CidadeIdInput {
	
	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
