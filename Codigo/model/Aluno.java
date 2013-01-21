package model;

import exception.ClienteException;

public class Aluno extends Cliente {
	
	//Mensagens de Erro e Alertas
		private final String MATRICULA_INVALIDO = "Matricula Invalido.";
		private final String MATRICULA_BRANCO = "Matricula em Branco.";
		
	
	public Aluno(String nome, String cpf, String matricula,
			String telefone, String email) throws ClienteException {
		super(nome, cpf, matricula, telefone, email);
	}

	public void setMatricula(String matricula) throws ClienteException {
		try{	
			if("".equals(matricula) || matricula.isEmpty())
				throw new ClienteException(MATRICULA_BRANCO);
			//else if(matricula.matches("PATTERN"))//TODO colocar o pattern
				//this.matricula = matricula;
			//else
				//throw new ClienteException(MATRICULA_INVALIDO);
			super.matricula = matricula;//
		} catch(StringIndexOutOfBoundsException e)
		{
			throw new ClienteException(MATRICULA_INVALIDO);
		}
	}

	
}

