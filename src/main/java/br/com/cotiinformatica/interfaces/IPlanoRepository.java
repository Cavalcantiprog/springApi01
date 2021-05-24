package br.com.cotiinformatica.interfaces;

import org.springframework.data.repository.CrudRepository;

import br.com.cotiinformatica.entities.Plano;

public interface IPlanoRepository extends CrudRepository<Plano, Integer> {

}
