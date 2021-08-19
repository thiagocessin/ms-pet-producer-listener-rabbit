package com.fiap.pet.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fiap.pet.domain.Cliente;
import com.fiap.pet.repositories.ClienteRepository;
import com.fiap.pet.services.exceptions.ObjectNotFoundException;


@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	private Random random = new Random();
	
	public void sendNewPassword(String email){
		Cliente cliente = clienteRepository.findByEmail(email);
		
		if(cliente == null) {
			throw new ObjectNotFoundException("E-mail não encontrado");
		}
		
		
		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));
		
		clienteRepository.save(cliente);
		
		emailService.sendNewPasswordEmail(cliente,newPass);
		
		
	}

	private String newPassword() {
		
		char[] vet = new char[10];
		
		for(int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		
		return new String(vet);
		
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		// gera digito 0 a 9
		if (opt == 0) {
			return (char) (random.nextInt(10) + 48);
		} else if (opt == 1) {// gera letra maiuscula
			return (char) (random.nextInt(26) + 65);
		} else {// gera letra minuscula
			return (char) (random.nextInt(26) + 97);

		}
	}
}
