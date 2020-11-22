package br.com.fretex.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fretex.domain.model.Carga;
import br.com.fretex.domain.model.Cliente;
import br.com.fretex.domain.model.PrestadorServico;
import br.com.fretex.domain.model.StatusNegocicacao;

@Repository
public interface CargaRepository extends JpaRepository<Carga, Long> {


	public List<Carga> findByNegociacoesIsNullAndCliente(Cliente cliente);

	public List<Carga> findByNegociacoesStatusAndCliente(StatusNegocicacao statusNegocicacao, Cliente cliente);

	public List<Carga> findByDataRetiradaIsNullAndNegociacoesStatusAndCliente(StatusNegocicacao statusNegocicacao,
			Cliente cliente);

	public List<Carga> findByDataEntregaIsNullAndDataRetiradaIsNotNullAndNegociacoesStatusAndCliente(
			StatusNegocicacao statusNegocicacao, Cliente cliente);

	public List<Carga> findByDataEntregaIsNotNullAndNegociacoesStatusAndCliente(
			StatusNegocicacao statusNegocicacao, Cliente cliente);
	
	
	public List<Carga> findByNegociacoesIsNullOrNegociacoesStatusAndNegociacoesVeiculoPrestadorServicoNot(StatusNegocicacao status, PrestadorServico prestadorServico);

	public List<Carga> findByNegociacoesStatusAndNegociacoesVeiculoPrestadorServico(StatusNegocicacao statusNegocicacao, PrestadorServico prestadorServico);

	public List<Carga> findByDataRetiradaIsNullAndNegociacoesStatusAndNegociacoesVeiculoPrestadorServico(StatusNegocicacao statusNegocicacao, PrestadorServico prestadorServico);

	public List<Carga> findByDataEntregaIsNullAndDataRetiradaIsNotNullAndNegociacoesStatusAndNegociacoesVeiculoPrestadorServico(
			StatusNegocicacao statusNegocicacao, PrestadorServico prestadorServico);

	public List<Carga> findByDataEntregaIsNotNullAndNegociacoesStatusAndNegociacoesVeiculoPrestadorServico(
			StatusNegocicacao statusNegocicacao, PrestadorServico prestadorServico);
	
	@Query( "SELECT carga												    \r\n" 
			+ "FROM Carga carga                                             \r\n" 
			+ "WHERE                                                        \r\n" 
			+ "carga NOT IN                                                 \r\n" 
			+ "(                                                            \r\n" 
			+ " SELECT neg.carga	                                        \r\n" 
			+ " FROM NegociacaoCarga neg                                    \r\n" 
			+ " WHERE                                                       \r\n" 
			+ " neg.carga = carga		                                    \r\n" 
			+ " AND neg.status IN ('ABERTA', 'FINALIZADA_COM_ACORDO')       \r\n" 
			+ ")                                                            \r\n" 
			+ "AND carga.cliente = :cliente                                ")
	public List<Carga> listarFinalizadasSemAcordoCliente(Cliente cliente);
}
