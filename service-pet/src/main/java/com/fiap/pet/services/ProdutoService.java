package com.fiap.pet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fiap.pet.domain.Categoria;
import com.fiap.pet.domain.Produto;
import com.fiap.pet.repositories.CategoriaRepository;
import com.fiap.pet.repositories.ProdutoRepository;
import com.fiap.pet.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {

		// obj que prepara as informações para que eu faça a conulta que me retorna a
		// página de dados
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		//return repo.search(nome,categorias,pageRequest);
		
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome,categorias,pageRequest);

		

	}
}
