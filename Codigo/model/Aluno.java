package model;

import exception.ClienteException;

public class Aluno extends Cliente {
	
	//Mensagens de Erro e Alertas
		private final String MATRICULA_INVALIDO = "Matricula Invalido.";
		private final String MATRICULA_BRANCO = "Matricula em Branco.";
		private final String MATRICULA_NULO = "Matricula esta Nula.";
		
	
	public Aluno(String nome, String cpf, String matricula,
			String telefone, String email) throws ClienteException {
		super(nome, cpf, matricula, telefone, email);
	}

	public void setMatricula(String matricula) throws ClienteException {
		try{
			if(matricula == null)
				throw new ClienteException(MATRICULA_NULO);
			else if("".equals(matricula) || matricula.isEmpty())
				throw new ClienteException(MATRICULA_BRANCO);
			//else if(matricula.matches("^[\\d]{2,2}/[\\d]{5,7}$"))
				//super.matricula = matricula;
			//else
				//throw new ClienteException(MATRICULA_INVALIDO);
			super.matricula = matricula;//
		} catch(StringIndexOutOfBoundsException e)
		{
			throw new ClienteException(MATRICULA_INVALIDO);
		}
	}

	public boolean equals(Aluno b){
		if(	super.getNome().equals(b.getNome()) &&
			super.getCpf().equals(b.getCpf()) &&
			super.getMatricula().equals(b.getMatricula()) &&
			super.getEmail().equals(b.getEmail()) &&
			super.getTelefone().equals(b.getTelefone())){
			
			return true;
		}
		return false;
	}
}

