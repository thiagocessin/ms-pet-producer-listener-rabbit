package com.fiap.pet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.pet.domain.Endereco;
import com.fiap.pet.repositories.EnderecoRepository;
import com.fiap.pet.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repo;

	public Endereco find(Integer id) {
		Optional<Endereco> obj = repo.findById(id);
		 return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
	}
	
	public List<Endereco> findAll() {
		return repo.findAll();
	}
	
	
	public Endereco save(Endereco categoria) {
		return repo.save(categoria);
	}

}
