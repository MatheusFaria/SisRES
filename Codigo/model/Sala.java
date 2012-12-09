package model;

import exception.PatrimonioException;

public class Sala extends Patrimonio {

	private int capacidade;
	
	
	public Sala(int codigo, String descricao, int capacidade) throws PatrimonioException {
		super(codigo, descricao);
		this.capacidade = capacidade;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

}
