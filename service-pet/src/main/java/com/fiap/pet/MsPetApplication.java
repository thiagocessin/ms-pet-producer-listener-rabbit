package com.fiap.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fiap.pet.services.S3Service;

@SpringBootApplication
public class MsPetApplication implements CommandLineRunner {
	
	@Autowired
	private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(MsPetApplication.class, args);
	}
	
	// executa alguma ação quando a aplicação inicia
		// usando MySql executar somente uma vez para carga na tabela
		@Override
		public void run(String... args) throws Exception {
			//s3Service.uploadFile("D:\\Thiago\\temp\\img1.jpg");
		}

}
