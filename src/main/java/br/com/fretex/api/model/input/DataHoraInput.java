package br.com.fretex.api.model.input;

import java.time.OffsetDateTime;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

public class DataHoraInput {

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private OffsetDateTime dataHora;

	public OffsetDateTime getDataHora() {
		return dataHora;
	}

	public void setDatHora(OffsetDateTime dataHora) {
		this.dataHora = dataHora;
	}

}
