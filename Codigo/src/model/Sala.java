package model;

import exception.PatrimonioException;

public class Sala extends Patrimonio {

	private String capacidade;
	

	//Mensagens de Erro e Alertas
		private final String CAPACIDADE_INVALIDO = "Capacidade Invalida.";
		private final String CAPACIDADE_BRANCO = "Capacidade em Branco.";
		private final String CAPACIDADE_NULA = "Capacidade esta nula.";
		//private final String CAPACIDADE_NEGATIVA = "Capacidade negativa.";
			
		
	public Sala(String codigo, String descricao, String capacidade) throws PatrimonioException {
		super(codigo, descricao);
		this.setCapacidade(capacidade);
	}

	public String getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(String capacidade) throws PatrimonioException {
		if(capacidade == null)
			throw new PatrimonioException(CAPACIDADE_NULA);
		else if("".equals(capacidade.trim()))
			throw new PatrimonioException(CAPACIDADE_BRANCO);
		else if(capacidade.matches("[\\d]+")){
				this.capacidade = capacidade;
		}
		else
		{
			throw new PatrimonioException(CAPACIDADE_INVALIDO);
		}
	}

	public boolean equals(Sala b){
		if( super.equals(b) &&
			this.getCapacidade().equals(b.getCapacidade())){
			return true;
		}
		
		return false;
	}
}
