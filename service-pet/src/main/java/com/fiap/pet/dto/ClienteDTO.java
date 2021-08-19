package com.fiap.pet.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fiap.pet.domain.Cliente;
import com.fiap.pet.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Nome não pode ser vazio")
	@Length(min = 5, max = 120, message = "Tamanho deve estar entre 5 e 120")
	private String nome;

	@NotEmpty(message = "Preenchimento Obrigatório")
	@Email(message = "Em-mail inválido")
	private String email;

	public ClienteDTO() {
	}

	public ClienteDTO(Cliente obj) {

		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
