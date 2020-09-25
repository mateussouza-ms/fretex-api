package br.com.fretex.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fretex.domain.model.FinalizacaoNegociacao;

@Repository
public interface FinalizacaoNegociacaoRepository extends JpaRepository<FinalizacaoNegociacao, Long> {

}
