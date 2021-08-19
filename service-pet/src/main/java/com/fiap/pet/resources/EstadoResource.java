package com.fiap.pet.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.pet.domain.Cidade;
import com.fiap.pet.domain.Estado;
import com.fiap.pet.dto.CidadeDTO;
import com.fiap.pet.dto.EstadoDTO;
import com.fiap.pet.services.CidadeService;
import com.fiap.pet.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService cidadeService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll(){
		List<Estado> list = service.findAll();
		List<EstadoDTO> listDto = list.stream()
				.map(obj -> new EstadoDTO(obj))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(listDto);
		
	}
	
	
	@RequestMapping(value = "/{estadoId}/cidades",method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId){
		List<Cidade> list = cidadeService.findByEstado(estadoId);
		List<CidadeDTO> listDto = list.stream()
				.map(obj -> new CidadeDTO(obj))
				.collect(Collectors.toList());	
		
		return ResponseEntity.ok(listDto);
	}
	
	
	
}
