package br.com.fretex.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fretex.domain.model.RecuperacaoSenha;

@Repository
public interface RecuperacaoSenhaRepository extends JpaRepository<RecuperacaoSenha, Long> {
	
	public Optional<RecuperacaoSenha> findByCodigo(String codigo);

}
