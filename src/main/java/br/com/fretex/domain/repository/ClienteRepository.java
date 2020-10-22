package br.com.fretex.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fretex.domain.model.Cliente;
import br.com.fretex.domain.model.Usuario;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	public boolean existsByUsuario(Usuario usuario);
	
	public List<Cliente> findByUsuario(Usuario usuario);
	
	public Optional<Cliente> findByUsuarioId(Long usuarioId);
}
