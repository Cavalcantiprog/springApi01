package br.com.cotiinformatica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "br.com.cotiinformatica" })
public class SpringApi01Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringApi01Application.class, args);
	}

}
