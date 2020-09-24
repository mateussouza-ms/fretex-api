package br.com.fretex.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fretex.domain.model.Proposta;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

	public boolean existsByNegociacaoCargaIdAndAceitaTrue(Long negociacaoCargaId);

	public int countByNegociacaoCargaId(Long negociacaoCargaId);
	
	public Proposta findTopByNegociacaoCargaIdOrderByDataCriacaoDesc(Long negociacaoCargaId);
}
