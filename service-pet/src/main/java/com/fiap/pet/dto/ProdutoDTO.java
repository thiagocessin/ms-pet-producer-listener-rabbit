package com.fiap.pet.dto;

import java.io.Serializable;

import com.fiap.pet.domain.Produto;

public class ProdutoDTO implements Serializable {// serializable - objetos podem ser convertidos para array de bytes

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Double preco;

	public ProdutoDTO() {
	}

	public ProdutoDTO(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	
	public ProdutoDTO(Produto o) {
		
		this.id = o.getId();
		this.nome = o.getNome();
		this.preco = o.getPreco();
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
