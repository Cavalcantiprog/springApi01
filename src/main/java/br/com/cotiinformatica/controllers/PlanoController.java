package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.adapters.DTOEntityAdapter;
import br.com.cotiinformatica.adapters.EntityDTOAdapter;
import br.com.cotiinformatica.adapters.EntityResponseAdapter;
import br.com.cotiinformatica.dtos.PlanoGetDTO;
import br.com.cotiinformatica.dtos.PlanoPostDTO;
import br.com.cotiinformatica.dtos.PlanoPutDTO;
import br.com.cotiinformatica.entities.Plano;
import br.com.cotiinformatica.responses.PlanoDeleteResponse;
import br.com.cotiinformatica.responses.PlanoPostResponse;
import br.com.cotiinformatica.responses.PlanoPutResponse;
import br.com.cotiinformatica.services.PlanoService;

@Controller
public class PlanoController {

	private static final String RESOURCE = "/api/planos";

	@Autowired
	private PlanoService planoService;

	@CrossOrigin
	@RequestMapping(value = RESOURCE, method = RequestMethod.POST)
	public ResponseEntity<PlanoPostResponse> 
  post(@RequestBody PlanoPostDTO dto) {

		try {

			Plano plano = DTOEntityAdapter.getPlano(dto);
			planoService.saveOrUpdate(plano);

			return ResponseEntity.status(HttpStatus.CREATED)
.body(EntityResponseAdapter
.getPlanoPostResponse(plano));

		} catch (Exception e) {

			return ResponseEntity.status
(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@CrossOrigin
	@RequestMapping(value = RESOURCE, method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<PlanoPutResponse> put
(@RequestBody PlanoPutDTO dto) {

		try {

			Plano plano = planoService
.findById(dto.getIdPlano());

			if (plano == null)
				return ResponseEntity.status
(HttpStatus.UNPROCESSABLE_ENTITY).body(null);

			plano.setNome(dto.getNome());
			plano.setDescricao(dto.getDescricao());

			planoService.saveOrUpdate(plano);

			return ResponseEntity.status
(HttpStatus.OK).body
(EntityResponseAdapter
.getPlanoPutResponse(plano));

		} catch (Exception e) {

			return ResponseEntity.status
(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@CrossOrigin
	@RequestMapping(value = RESOURCE + "/{id}", 
method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<PlanoDeleteResponse> 
delete(@PathVariable("id") Integer id) {

		try {

			Plano plano = planoService.findById(id);

			if (plano == null)
				return ResponseEntity.status
(HttpStatus.UNPROCESSABLE_ENTITY).body(null);

			planoService.delete(id);

			return ResponseEntity.status
(HttpStatus.OK).body
(EntityResponseAdapter
.getPlanoDeleteResponse(plano));

		} catch (Exception e) {

			return ResponseEntity.status
(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@CrossOrigin
	@RequestMapping(value = RESOURCE, method = RequestMethod.GET)
	@ResponseBody


	public ResponseEntity<List<PlanoGetDTO>> getAll() {

		try {

			List<PlanoGetDTO> result 
= new ArrayList<PlanoGetDTO>();

			for (Plano plano : planoService.findAll()) {
				result.add(EntityDTOAdapter.getDTO(plano));
			}

			if (result == null || result.size() == 0)
				return ResponseEntity.status
(HttpStatus.NO_CONTENT).body(null);
			else
				return ResponseEntity.status
(HttpStatus.OK).body(result);

		} catch (Exception e) {

			return ResponseEntity.status
(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	@CrossOrigin
	@RequestMapping(value = RESOURCE + "/{id}", 
method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<PlanoGetDTO> getById
(@PathVariable("id") Integer id) {

		try {

			Plano plano = planoService.findById(id);

			if (plano == null)
				return ResponseEntity.status
(HttpStatus.NO_CONTENT).body(null);
			else
				return ResponseEntity.status
(HttpStatus.OK).body
(EntityDTOAdapter.getDTO(plano));

		} catch (Exception e) {

			return ResponseEntity.status
(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}

