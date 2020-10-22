package br.com.fretex.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fretex.domain.model.UF;

@Repository
public interface EstadosRepository extends JpaRepository<UF, Long> {

	@Query("select uf from UF as uf order by Nome")
	public List<UF> findAllOrderByNomeAsc();
}
