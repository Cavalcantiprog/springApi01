package br.com.cotiinformatica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.adapters.DTOEntityAdapter;
import br.com.cotiinformatica.adapters.EntityResponseAdapter;
import br.com.cotiinformatica.dtos.UsuarioPostDTO;
import br.com.cotiinformatica.dtos.UsuarioSenhaPutDTO;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.responses.UsuarioPostResponse;
import br.com.cotiinformatica.security.Criptografia;
import br.com.cotiinformatica.services.UsuarioService;

@Controller
public class UsuarioController {

	private static final String RESOURCE = "/api/usuarios";

	@Autowired
	private UsuarioService usuarioService;

	// Método para disponibilizar um serviço de cadastro de usuario na API.
	@CrossOrigin
	@RequestMapping(value = RESOURCE, method = RequestMethod.POST)
	@ResponseBody // indica que o método irá retornar dados no serviço
	public ResponseEntity<UsuarioPostResponse> post(@RequestBody UsuarioPostDTO dto) {

		try {

			//verificar se as senhas são iguais
			if( ! dto.getSenha().equals(dto.getSenhaConfirmacao())) {
				throw new IllegalArgumentException();
			}
			//verificar se o email informado já está cadastrado
			else if(usuarioService.findByEmail(dto.getEmail()) != null) {
				throw new IllegalArgumentException();
			}
			
			Usuario usuario = DTOEntityAdapter.getUsuario(dto);	
			
			//criptografar a senha do usuário..
			usuario.setSenha(Criptografia.criptografar(usuario.getSenha()));
			
			usuarioService.saveOrUpdate(usuario); //cadastrar o usuário

			return ResponseEntity
					.status(HttpStatus.CREATED) //201 - Criado!
					.body(EntityResponseAdapter.getUsuarioPostResponse(usuario));

		} 
		catch(IllegalArgumentException e) {
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST) //400 (validação!)
					.body(null);
		}
		catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}
		
	@CrossOrigin
	@RequestMapping(value = RESOURCE + "/senha", method = RequestMethod.PUT)
	@ResponseBody // indica que o método irá retornar dados no serviço
	public ResponseEntity<String> put(@RequestBody UsuarioSenhaPutDTO dto) {
		
		try {
			
			//verificar se o usuario informou as 2 senhas novas iguais..
			if(!dto.getNovaSenha().equals(dto.getNovaSenhaConfirmacao())) {
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST) //400 (validação!)
						.body("Nova senha não confere. Por favor confirme-a novamente.");
			}
			
			//buscar o usuario no banco de dados atraves do email e senha atual..
			Usuario usuario = usuarioService.findByEmailAndSenha
					(dto.getEmail(), Criptografia.criptografar(dto.getSenhaAtual()));
			
			//verificar se o usuario foi encontrado..
			if(usuario != null) {
				
				//alterando a senha do usuário..
				usuarioService.updatePassword
					(usuario.getIdUsuario(), Criptografia.criptografar(dto.getNovaSenha()));
				
				return ResponseEntity
						.status(HttpStatus.OK) //200 (Sucesso!)
						.body("Senha de usuário alterada com sucesso!");
			}
			else {
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST) //400 (validação!)
						.body("Email e senha atual inválidos. Verifique os dados informados");
			}
		}
		catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
		
	}
	
}









