package br.com.fretex.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fretex.domain.model.PrestadorServico;
import br.com.fretex.domain.model.Usuario;

@Repository
public interface PrestadorServicoRepository extends JpaRepository<PrestadorServico, Long> {

	public boolean existsByUsuario(Usuario usuario);

	public Optional<PrestadorServico> findByUsuario(Usuario usuario);

	public Optional<PrestadorServico> findByUsuarioId(Long usuarioId);
}
