package model;

import exception.ClienteException;

public class Professor extends Cliente {

	private String matricula;
	
	
	//Mensagens de Erro e Alertas
		private final String MATRICULA_INVALIDO = "Matricula Invalido.";
		private final String MATRICULA_BRANCO = "Matricula em Branco.";
		
	
	public Professor(String nome, String cpf, String matricula,
			String telefone, String email) throws ClienteException {
		super(nome, cpf, telefone, email);
		this.setMatricula(matricula);
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) throws ClienteException {
		try{	
			if("".equals(matricula))
				throw new ClienteException(MATRICULA_BRANCO);
			//else if(matricula.matches("PATTERN"))//TODO colocar o pattern
				//this.matricula = matricula;
			//else
				//throw new ClienteException(MATRICULA_INVALIDO);
			this.matricula = matricula;//
		} catch(StringIndexOutOfBoundsException e)
		{
			throw new ClienteException(MATRICULA_INVALIDO);
		}
	}

	
}
