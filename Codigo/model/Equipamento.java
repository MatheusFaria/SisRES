package model;

import exception.PatrimonioException;

public class Equipamento extends Patrimonio{

	public Equipamento(String codigo, String descricao) throws PatrimonioException {
		super(codigo, descricao);
	}
	
	@Override
	public boolean equals(Equipamento e){
		if(this.getCodigo().equals(e.getCodigo())
				&& this.getDescricao().equals(e.getDescricao()))
			return true;
		
		return false;
	}

}
