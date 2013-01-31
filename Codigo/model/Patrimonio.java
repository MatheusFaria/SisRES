package model;

import exception.PatrimonioException;

public class Patrimonio {

	private String codigo;
	private String descricao;
	//Mensagens de Erro e Alertas
	private final String CODIGO_INVALIDO = "Codigo Invalido.";
	private final String CODIGO_BRANCO = "Codigo em Branco.";
	private final String DESCRICAO_INVALIDO = "Descricao Invalido.";
	private final String DESCRICAO_BRANCO = "Descricao em Branco.";

	public Patrimonio(String codigo, String descricao) throws PatrimonioException {
		this.setCodigo(codigo);
		this.setDescricao(descricao);
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setCodigo(String codigo) throws PatrimonioException {
		try {
			if ("".equals(codigo)) {
				throw new PatrimonioException(CODIGO_BRANCO);
			}
			//else if(codigo.matches("PATTERN"))
			//this.codigo = codigo;
			//else
			//throw new PatrimonioException(CODIGO_INVALIDO);
			this.codigo = codigo;//
		} catch (StringIndexOutOfBoundsException e) {
			throw new PatrimonioException(CODIGO_INVALIDO);
		} catch (NullPointerException e) {
			throw new PatrimonioException(CODIGO_BRANCO);
		}
	}

	public void setDescricao(String descricao) throws PatrimonioException {
		try {
			if ("".equals(descricao)) {
				throw new PatrimonioException(DESCRICAO_BRANCO);
			}
			this.descricao = descricao;
		} catch (StringIndexOutOfBoundsException e) {
			throw new PatrimonioException(DESCRICAO_INVALIDO);
		} catch (NullPointerException e) {
			throw new PatrimonioException(DESCRICAO_BRANCO);
		}
	}

	@Override
	public String toString() {
		return "Patrimonio [codigo=" + codigo + ", descricao=" + descricao
			+ "]";
	}
}
