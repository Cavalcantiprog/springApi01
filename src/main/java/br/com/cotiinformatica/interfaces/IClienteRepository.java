package br.com.cotiinformatica.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.entities.Cliente;

public interface IClienteRepository extends CrudRepository<Cliente, Integer> {

	/*
	 * JPQL -> Java Persistence Query Language
	 */

	@Query("select c from Cliente c where c.cpf = :param")
	Cliente findByCpf(@Param("param") String cpf);

	@Query("select c from Cliente c where c.email = :param")
	Cliente findByEmail(@Param("param") String email);
	
	@Query("select c from Cliente c join c.plano")
	List<Cliente> findJoinPlano();
	
	@Query("select c from Cliente c join c.plano where c.idCliente = :param")
	Cliente findByIdJoinPlano(@Param("param") Integer idCliente);
	
}
