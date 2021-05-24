package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.dtos.RecuperarSenhaPostDTO;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.security.Criptografia;
import br.com.cotiinformatica.senders.MailSender;
import br.com.cotiinformatica.services.UsuarioService;

@Controller
public class RecuperarSenhaController {

	private static final String RESOURCE = "/api/recuperarsenha";

	@Autowired // injeção de dependência
	private UsuarioService usuarioService;

	@Autowired // injeção de dependência
	private MailSender mailSender;

	@CrossOrigin
	@RequestMapping(value = RESOURCE, method = RequestMethod.POST)
	@ResponseBody // indica que o método irá retornar dados no serviço
	public ResponseEntity<String> post(@RequestBody RecuperarSenhaPostDTO dto) {
		
		try {
			
			//verificar se o email pertence a um usuario da base de dados..
			Usuario usuario = usuarioService.findByEmail(dto.getEmail());
			
			//verificar se o usuario foi encontrado
			if(usuario != null) {
				
				//gerando uma nova senha para o usuario..
				String novaSenha = generatePassword();
				
				//atualizando a senha no banco de dados..
				usuarioService.updatePassword(usuario.getIdUsuario(), Criptografia.criptografar(novaSenha));
				
				//enviar a nova senha para o email do usuario..
				String subject = "Recuperação de Senha - Sistema COTI Informática (JAVA Domingo)";
				String text = "Olá, " + usuario.getNome() + "\n\nSua nova senha é: " + novaSenha;
				
				//realizando o envio do email
				mailSender.sendMessage(usuario.getEmail(), subject, text);
				
				return ResponseEntity
						.status(HttpStatus.OK)
						.body("Uma nova senha foi gerada com sucesso e enviada para o email: " + usuario.getEmail());
			}
			else {
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body("Email de usuário inválido.");
			}
		}
		catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}
		
	}

	// método para gerar uma nova senha para o usuario..
	private String generatePassword() {

		StringBuilder password = new StringBuilder(8);
		Random random = new Random(System.nanoTime());

		List<String> charCategories = new ArrayList<String>();

		charCategories.add("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		charCategories.add("abcdefghijklmnopqrstuvwxyz");
		charCategories.add("0123456789");

		for (int i = 0; i < 8; i++) {
			String charCategory = charCategories.get(random.nextInt(charCategories.size()));
			int position = random.nextInt(charCategory.length());
			password.append(charCategory.charAt(position));
		}

		return new String(password);
	}
}


