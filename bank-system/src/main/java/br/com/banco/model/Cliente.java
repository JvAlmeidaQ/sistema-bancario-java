package br.com.banco.model;

import java.util.ArrayList;
import java.util.List;

import br.com.banco.util.IdGenerator;

public class Cliente {
	private Integer id;
	private String nome;
	private String cpf;
	private String senhaHash;
	
	List<Conta> contas = new ArrayList<>();
	
	public Cliente(Integer id, String nome, String cpf, String hash) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.senhaHash = hash;
	}

	
	public Cliente(Integer id, String nome, String cpf) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
	}


	public Integer getId() {
		return id;
	}

	public void setId() {
		this.id = IdGenerator.geraId();
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public String getSenhaHash() {
		return senhaHash;
	}
	
	public boolean autenticar(String Hash) {
		return senhaHash.equals(Hash);
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + "]";
	}
		
	
				
}
