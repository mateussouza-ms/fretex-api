package br.com.fretex.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fretex.domain.model.ParametrosConfig;

@Repository
public interface ParametrosConfigRepository extends JpaRepository<ParametrosConfig, Long> {

	public ParametrosConfig findTopByOrderByIdDesc();

}
