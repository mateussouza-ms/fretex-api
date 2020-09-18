package br.com.fretex.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fretex.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	public Cliente findByCnp(String cnp);
	public Cliente findByEmail(String email);
}
