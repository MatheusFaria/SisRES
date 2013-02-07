package model;

import exception.PatrimonioException;

public class Sala extends Patrimonio {

	private String capacidade;
	

	//Mensagens de Erro e Alertas
		private final String CAPACIDADE_INVALIDO = "Codigo Invalido.";
		private final String CAPACIDADE_BRANCO = "Codigo em Branco.";
		private final String CAPACIDADE_NULA = "Codigo esta nula.";
			
		
	public Sala(String codigo, String descricao, String capacidade) throws PatrimonioException {
		super(codigo, descricao);
		this.capacidade = capacidade;
	}

	public String getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(String capacidade) throws PatrimonioException {
		try{
			if(capacidade == null)
				throw new PatrimonioException(CAPACIDADE_NULA);
			else if(capacidade.isEmpty())
				throw new PatrimonioException(CAPACIDADE_BRANCO);
			else if(capacidade.matches("[\\d]+"))
				this.capacidade = capacidade;
			else
				throw new PatrimonioException(CAPACIDADE_INVALIDO);
		} catch(StringIndexOutOfBoundsException e)
		{
			throw new PatrimonioException(CAPACIDADE_INVALIDO);
		}
		this.capacidade = capacidade;
	}

	public boolean equals(Sala b){
		if( super.equals(b) &&
			this.getDescricao().equals(b.getDescricao())){
			return true;
		}
		
		return false;
	}
}
