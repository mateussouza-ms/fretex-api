package br.com.fretex.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Carga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Cliente cliente;
	private String tipoCarga;
	private BigDecimal peso;

	@ManyToOne(cascade = CascadeType.ALL)
	private Endereco enderecoRetirada;

	@ManyToOne(cascade = CascadeType.ALL)
	private Endereco enderecoEntrega;

	private String observacoes;
	private OffsetDateTime dataCadastro;
	private OffsetDateTime dataRetirada;
	private OffsetDateTime dataEntrega;
	private LocalDate dataRetiradaPretendida;
	private LocalDate dataEntregaPretendida;
	private Boolean negociaDatas;

	@OneToMany(mappedBy = "carga")
	private List<NegociacaoCarga> negociacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getTipoCarga() {
		return tipoCarga;
	}

	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public Endereco getEnderecoRetirada() {
		return enderecoRetirada;
	}

	public void setEnderecoRetirada(Endereco enderecoRetirada) {
		this.enderecoRetirada = enderecoRetirada;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public OffsetDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(OffsetDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public OffsetDateTime getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(OffsetDateTime dataRetirada) {
		this.dataRetirada = dataRetirada;
	}

	public OffsetDateTime getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(OffsetDateTime dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public LocalDate getDataRetiradaPretendida() {
		return dataRetiradaPretendida;
	}

	public void setDataRetiradaPretendida(LocalDate dataRetiradaPretendida) {
		this.dataRetiradaPretendida = dataRetiradaPretendida;
	}

	public LocalDate getDataEntregaPretendida() {
		return dataEntregaPretendida;
	}

	public void setDataEntregaPretendida(LocalDate dataEntregaPretendida) {
		this.dataEntregaPretendida = dataEntregaPretendida;
	}

	public Boolean getNegociaDatas() {
		return negociaDatas;
	}

	public void setNegociaDatas(Boolean negociaDatas) {
		this.negociaDatas = negociaDatas;
	}

	public List<NegociacaoCarga> getNegociacoes() {
		return negociacoes;
	}

	public void setNegociacoes(List<NegociacaoCarga> negociacoes) {
		this.negociacoes = negociacoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carga other = (Carga) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
