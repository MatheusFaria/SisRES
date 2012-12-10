package model;

import exception.ClienteException;
import util.Util;

/*Para fazer uma melhor validacoa e captura do dados
 * se pega todos os dados como string.
 * 
 * 
 */
public class Cliente {
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	
	//Mensagens de Erro e Alertas
		private final String NOME_INVALIDO = "Nome Invalido.";
		private final String NOME_BRANCO = "Nome em Branco.";
		private final String CPF_INVALIDO = "CPF Invalido.";
		private final String CPF_BRANCO = "CPF em Branco.";
		private final String TELEFONE_INVALIDO = "Telefone Invalido.";
		private final String TELEFONE_BRANCO = "Telefone em Branco.";
		private final String EMAIL_INVALIDO = "E-mail Invalido.";
		//private final String EMAIL_BRANCO = "E-mail em Branco.";
	
	
	public Cliente(String nome, String cpf, String telefone,
			String email) throws ClienteException{
		this.setNome(nome);
		this.setCpf(cpf);
		this.setTelefone(telefone);
		this.setEmail(email);
	}

	public String getNome() {
		return nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setNome(String nome) throws ClienteException{
		try{	
			if("".equals(nome))
				throw new ClienteException(NOME_BRANCO);
			else if(nome.matches("[a-zA-Z\\s]+"))
				this.nome = nome;
			else
				throw new ClienteException(NOME_INVALIDO);
		} catch(StringIndexOutOfBoundsException e)
		{
			throw new ClienteException(NOME_INVALIDO);
		}
	}
	
	public void setCpf(String cpf) throws ClienteException {
		try{	
			if("".equals(cpf))
				throw new ClienteException(CPF_BRANCO);
			else if(Util.validarCpf(cpf))
				this.cpf = cpf;
			else
				throw new ClienteException(CPF_INVALIDO);
		} catch(StringIndexOutOfBoundsException e)
		{
			throw new ClienteException(CPF_INVALIDO);
		}
	}
	
	public void setTelefone(String telefone) throws ClienteException {
		try{	
			if("".equals(telefone))
				throw new ClienteException(TELEFONE_BRANCO);
			else if(telefone.matches("(\\([\\d]{2,3}\\))?[ ]*[\\d]{4,4}[ ]*-[ ]*[\\d]{4,4}[ ]*$"))
				this.telefone = telefone;
			else
				throw new ClienteException(TELEFONE_INVALIDO);
		} catch(StringIndexOutOfBoundsException e)
		{
			throw new ClienteException(TELEFONE_INVALIDO);
		}
	}
	
	public void setEmail(String email) throws ClienteException {
		try{
			if(email != null)
				this.email = email;
			else
				throw new ClienteException(EMAIL_INVALIDO);
		} catch(StringIndexOutOfBoundsException e)
		{
			throw new ClienteException(EMAIL_INVALIDO);
		}
	}
	
	
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", cpf=" + cpf +
				", telefone=" + telefone + ", email=" + email
				+ "]";
	}

}
