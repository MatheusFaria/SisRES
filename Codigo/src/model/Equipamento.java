package model;

import exception.PatrimonioException;

public class Equipamento extends Patrimonio{

	public Equipamento(String codigo, String descricao) throws PatrimonioException {
		super(codigo, descricao);
	}

}
