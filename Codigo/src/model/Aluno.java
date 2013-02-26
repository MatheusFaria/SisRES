package model;

import exception.ClienteException;

public class Aluno extends Cliente {
	
	//Mensagens de Erro e Alertas
		//private final String MATRICULA_INVALIDO = "Matricula Invalido.";
		private final String MATRICULA_BRANCO = "Matricula em Branco.";
		private final String MATRICULA_NULO = "Matricula esta Nula.";
		
	
	public Aluno(String nome, String cpf, String matricula,
			String telefone, String email) throws ClienteException {
		super(nome, cpf, matricula, telefone, email);
	}

	public void setMatricula(String matricula) throws ClienteException {
		if(matricula == null)
			throw new ClienteException(MATRICULA_NULO);
		else if("".equals(matricula.trim()))
			throw new ClienteException(MATRICULA_BRANCO);
		//else if(matricula.matches("^[\\d]{2,2}/[\\d]{5,7}$"))
			//super.matricula = matricula;
		//else
			//throw new ClienteException(MATRICULA_INVALIDO);
		super.matricula = matricula;//
	}
}

