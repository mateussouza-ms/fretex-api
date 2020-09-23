package br.com.fretex.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fretex.domain.model.Carga;
import br.com.fretex.domain.model.NegociacaoCarga;
import br.com.fretex.domain.model.Veiculo;

@Repository
public interface NegociacaoCargaRepository extends JpaRepository<NegociacaoCarga, Long> {

	public boolean existsByCargaAndVeiculo(Carga carga, Veiculo veiculo);
	
	public List<NegociacaoCarga> findByCargaId(Long cargaId);
}
