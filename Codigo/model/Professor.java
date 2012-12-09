package model;

import exception.ClienteException;

public class Professor extends Cliente {

	public Professor(String nome, int cpf, int matricula, int telefone,
			String email) throws ClienteException {
		super(nome, cpf, matricula, telefone, email);
	}

}
