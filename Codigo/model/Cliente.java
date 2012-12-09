package model;

import exception.ClienteException;

//TODO Validacao de Dados

public class Cliente {
	private String nome;
	private int cpf;
	private int matricula;
	private int telefone;
	private String email;
	
	public Cliente(String nome, int cpf, int matricula, int telefone,
			String email) throws ClienteException{
		this.nome = nome;
		this.cpf = cpf;
		this.matricula = matricula;
		this.telefone = telefone;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}
	
	public int getCpf() {
		return cpf;
	}
	
	public int getMatricula() {
		return matricula;
	}
	
	public int getTelefone() {
		return telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", cpf=" + cpf + ", matricula="
				+ matricula + ", telefone=" + telefone + ", email=" + email
				+ "]";
	}

}
