package br.com.cotiinformatica.adapters;

import br.com.cotiinformatica.dtos.ClienteGetDTO;
import br.com.cotiinformatica.dtos.PlanoGetDTO;
import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.entities.Plano;

/*
 * Classe para transferencia de dados
 * de objetos Entidade para DTO
 */
public class EntityDTOAdapter {

	// ClienteGetDTO -> Cliente
	public static ClienteGetDTO getDTO(Cliente cliente) {

		ClienteGetDTO dto = new ClienteGetDTO();

		dto.setIdCliente(cliente.getIdCliente());
		dto.setNome(cliente.getNome());
		dto.setEmail(cliente.getEmail());
		dto.setCpf(cliente.getCpf());

		return dto;
	}

	// PlanoGetDTO -> Plano
	public static PlanoGetDTO getDTO(Plano plano) {

		PlanoGetDTO dto = new PlanoGetDTO();

		dto.setIdPlano(plano.getIdPlano());
		dto.setNome(plano.getNome());
		dto.setDescricao(plano.getDescricao());

		return dto;
	}
}
