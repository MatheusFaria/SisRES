package model;

import exception.PatrimonioException;

//TODO Validacao de Dados

public class Patrimonio {

	private int codigo;
	private String descricao;	
	
	public Patrimonio(int codigo, String descricao) throws PatrimonioException{
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Patrimonio [codigo=" + codigo + ", descricao=" + descricao
				+ "]";
	}

}
