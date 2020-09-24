package br.com.fretex.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fretex.domain.model.Carga;
import br.com.fretex.domain.model.NegociacaoCarga;
import br.com.fretex.domain.model.StatusNegocicacao;
import br.com.fretex.domain.model.Usuario;
import br.com.fretex.domain.model.Veiculo;

@Repository
public interface NegociacaoCargaRepository extends JpaRepository<NegociacaoCarga, Long> {

	public boolean existsByCargaAndVeiculo(Carga carga, Veiculo veiculo);

	public boolean existsByCargaAndStatus(Carga carga, StatusNegocicacao status);
	
	public List<NegociacaoCarga> findByCargaId(Long cargaId);
	
	public Optional<NegociacaoCarga> findByIdAndCarga(Long id, Carga carga);
	
	@Query("SELECT usuario \r\n" 
			+ "FROM NegociacaoCarga negociacao \r\n" 
			+ "INNER JOIN negociacao.carga carga \r\n" 
			+ "INNER JOIN carga.cliente cliente \r\n" 
			+ "INNER JOIN cliente.usuario usuario \r\n" 
			+ "WHERE negociacao.id = :negociacaoCargaId")
	public Usuario getClienteFromNegociacao(Long negociacaoCargaId);
	
	@Query("SELECT usuario \r\n" 
			+ "FROM NegociacaoCarga negociacao \r\n" 
			+ "INNER JOIN negociacao.veiculo veiculo \r\n" 
			+ "INNER JOIN veiculo.prestadorServico prestador \r\n" 
			+ "INNER JOIN prestador.usuario usuario \r\n" 
			+ "WHERE negociacao.id = :negociacaoCargaId")
	public Usuario getPrestadorFromNegociacao(Long negociacaoCargaId);
	
	
}
