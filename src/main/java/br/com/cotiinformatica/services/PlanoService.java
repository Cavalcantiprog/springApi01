package br.com.cotiinformatica.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.entities.Plano;
import br.com.cotiinformatica.interfaces.IPlanoRepository;

@Service
@Transactional
public class PlanoService {

	@Autowired
	private IPlanoRepository planoRepository;

	public void saveOrUpdate(Plano plano) throws Exception {
		planoRepository.save(plano);
	}

	public void delete(Integer idPlano) throws Exception {

		Plano plano = planoRepository.findById(idPlano).get();

		if (plano == null)
			throw new Exception("Plano não encontrado.");

		planoRepository.delete(plano);
	}

	public List<Plano> findAll() throws Exception {

		return (List<Plano>) planoRepository.findAll();
	}

	public Plano findById(Integer id) throws Exception {

		Optional<Plano> result = planoRepository.findById(id);

		if (!result.isEmpty())
			return result.get();

		return null;
	}
}
