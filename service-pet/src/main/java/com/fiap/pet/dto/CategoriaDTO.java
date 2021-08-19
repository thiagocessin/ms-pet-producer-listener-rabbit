package com.fiap.pet.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fiap.pet.domain.Categoria;


public class CategoriaDTO  implements Serializable {// serializable - objetos podem ser convertidos para array de bytes

	private static final long serialVersionUID = 1L;

	
	private Integer id;

	@NotEmpty(message = "Nome não pode ser vazio")
	@Length(min = 5, max = 50, message = "Tamanho deve estar entre 5 e 50")
	private String nome;
	
	
	public CategoriaDTO() {}
	
	public CategoriaDTO(Categoria obj) {
		
		this.id = obj.getId();
		this.nome = obj.getNome();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
