package br.com.cotiinformatica.adapters;

import java.util.Date;

import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.entities.Plano;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.responses.ClienteDeleteResponse;
import br.com.cotiinformatica.responses.ClientePostResponse;
import br.com.cotiinformatica.responses.ClientePutResponse;
import br.com.cotiinformatica.responses.PlanoDeleteResponse;
import br.com.cotiinformatica.responses.PlanoPostResponse;
import br.com.cotiinformatica.responses.PlanoPutResponse;
import br.com.cotiinformatica.responses.UsuarioPostResponse;

public class EntityResponseAdapter {

	public static ClientePostResponse getPostResponse(Cliente cliente) {

		ClientePostResponse response = new ClientePostResponse();

		response.setIdCliente(cliente.getIdCliente());
		response.setNome(cliente.getNome());
		response.setEmail(cliente.getEmail());
		response.setCpf(cliente.getCpf());
		response.setDataCadastro(new Date());

		return response;
	}

	public static ClientePutResponse getPutResponse(Cliente cliente) {

		ClientePutResponse response = new ClientePutResponse();

		response.setIdCliente(cliente.getIdCliente());
		response.setNome(cliente.getNome());
		response.setEmail(cliente.getEmail());
		response.setCpf(cliente.getCpf());
		response.setDataAlteracao(new Date());

		return response;
	}

	public static ClienteDeleteResponse getDeleteResponse(Cliente cliente) {

		ClienteDeleteResponse response = new ClienteDeleteResponse();

		response.setIdCliente(cliente.getIdCliente());
		response.setNome(cliente.getNome());
		response.setEmail(cliente.getEmail());
		response.setCpf(cliente.getCpf());
		response.setDataExclusao(new Date());

		return response;
	}

	public static UsuarioPostResponse getUsuarioPostResponse(Usuario usuario) {

		UsuarioPostResponse response = new UsuarioPostResponse();

		response.setIdUsuario(usuario.getIdUsuario());
		response.setNome(usuario.getNome());
		response.setEmail(usuario.getEmail());

		return response;
	}

	public static PlanoPostResponse getPlanoPostResponse(Plano plano) {

		PlanoPostResponse response = new PlanoPostResponse();

		response.setIdPlano(plano.getIdPlano());
		response.setNome(plano.getNome());
		response.setDescricao(plano.getDescricao());
		response.setDataCadastro(new Date());

		return response;
	}

	public static PlanoPutResponse getPlanoPutResponse(Plano plano) {

		PlanoPutResponse response = new PlanoPutResponse();

		response.setIdPlano(plano.getIdPlano());
		response.setNome(plano.getNome());
		response.setDescricao(plano.getDescricao());
		response.setDataAlteracao(new Date());

		return response;
	}

	public static PlanoDeleteResponse getPlanoDeleteResponse(Plano plano) {

		PlanoDeleteResponse response = new PlanoDeleteResponse();

		response.setIdPlano(plano.getIdPlano());
		response.setNome(plano.getNome());
		response.setDescricao(plano.getDescricao());
		response.setDataExclusao(new Date());

		return response;
	}

}