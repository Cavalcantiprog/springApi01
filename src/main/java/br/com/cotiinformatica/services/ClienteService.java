package br.com.cotiinformatica.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.interfaces.IClienteRepository;

@Service
@Transactional
public class ClienteService {

	@Autowired
	private IClienteRepository clienteRepository;

	public void saveOrUpdate(Cliente cliente) throws Exception {

		if (cliente.getIdCliente() == null || cliente.getIdCliente() == 0) {

			// verificar se o email do cliente já está cadastrado..
			if (clienteRepository.findByEmail(cliente.getEmail()) != null)
				throw new Exception("O email informado já encontra-se cadastrado.");

			// verificar se o cpf do cliente já está cadastrado..
			if (clienteRepository.findByCpf(cliente.getCpf()) != null)
				throw new Exception("O cpf informado já encontra-se cadastrado.");

		}

		clienteRepository.save(cliente);
	}

	public void delete(Integer idCliente) throws Exception {

		Cliente cliente = clienteRepository.findById(idCliente).get();

		if (cliente == null)
			throw new Exception("Cliente não encontrado.");

		clienteRepository.delete(cliente);
	}

	public List<Cliente> findAll() throws Exception {

		return (List<Cliente>) clienteRepository.findJoinPlano();
	}

	public Cliente findById(Integer id) throws Exception {

		return  clienteRepository.findByIdJoinPlano(id);
		
		
	}
}
