package model;

import exception.ClienteException;


/*Para fazer uma melhor validacoa e captura do dados
 * se pega todos os dados como string.
 * 
 * 
 */
public abstract class Cliente {
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	protected String matricula;
	
	//Mensagens de Erro e Alertas
		private final String NOME_INVALIDO = "Nome Invalido.";
		private final String NOME_BRANCO = "Nome em Branco.";
		private final String NOME_NULO = "Nome esta Nulo.";
		private final String CPF_INVALIDO = "CPF Invalido.";
		private final String CPF_BRANCO = "CPF em Branco.";
		private final String CPF_NULO = "CPF esta Nulo.";
		private final String TELEFONE_INVALIDO = "Telefone Invalido.";
		//private final String TELEFONE_BRANCO = "Telefone em Branco.";
		private final String TELEFONE_NULO = "Telefone esta Nulo.";
		private final String EMAIL_INVALIDO = "E-mail Invalido.";
		//private final String EMAIL_BRANCO = "E-mail em Branco.";
		private final String EMAIL_NULO = "E-mail esta Nulo.";
	
	
	public Cliente(String nome, String cpf, String matricula,
			String telefone, String email) throws ClienteException{
		this.setNome(nome);
		this.setCpf(cpf);
        this.setMatricula(matricula);
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
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setNome(String nome) throws ClienteException{
		try{
			if(nome == null)
				throw new ClienteException(NOME_NULO);
			else if("".equals(nome))
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
			if(cpf == null)
				throw new ClienteException(CPF_NULO);
			else if("".equals(cpf))
				throw new ClienteException(CPF_BRANCO);
			else if(cpf.matches("[\\d]{3,3}.[\\d]{3,3}.[\\d]{3,3}-[\\d]{2,2}$"))
			{
				if(this.validarCpf(
						cpf.split("[\\. | -]")[0] + 
						cpf.split("[\\. | -]")[1] + 
						cpf.split("[\\. | -]")[2] + 
						cpf.split("[\\. | -]")[3]))
					this.cpf = cpf;
				else
					throw new ClienteException(CPF_INVALIDO);
			}
			else
				throw new ClienteException(CPF_INVALIDO);
		} catch(StringIndexOutOfBoundsException e)
		{
			throw new ClienteException(CPF_INVALIDO);
		} catch(NumberFormatException e)
		{
			throw new ClienteException(CPF_INVALIDO);
		} 
	}
	
	public void setTelefone(String telefone) throws ClienteException {
		try{
			if(telefone == null)
				throw new ClienteException(TELEFONE_NULO);
			else if("".equals(telefone))
				this.telefone = telefone;
			//Telefone ser� guardado sem espa�os.
			else if(telefone.matches("(\\([ ]*[\\d]{2,3}[ ]*\\))?[ ]*[\\d]{4,4}[ ]*-?[ ]*[\\d]{4,4}[ ]*$"))
				this.telefone = telefone.replaceAll(" ", "");
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
				throw new ClienteException(EMAIL_NULO);
		} catch(StringIndexOutOfBoundsException e)
		{
			throw new ClienteException(EMAIL_INVALIDO);
		}
	}
	
	public abstract void setMatricula(String matricula) throws ClienteException;
	
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", cpf=" + cpf + ", telefone="
				+ telefone + ", email=" + email + ", matricula=" + matricula
				+ "]";
	}

	public boolean equals(Cliente b){
		if(	this.getNome().equals(b.getNome()) &&
			this.getCpf().equals(b.getCpf()) &&
			this.getMatricula().equals(b.getMatricula()) &&
			this.getEmail().equals(b.getEmail()) &&
			this.getTelefone().equals(b.getTelefone())){
			
			return true;
		}
		return false;
	}
	
	private boolean validarCpf(String cpf) {

		int d1, d2;
		int digito1, digito2, resto;
		int digitoCPF;
		String	nDigResult;

		d1 = d2 = 0;
		digito1 = digito2 = resto = 0;

		for (int nCount = 1; nCount < cpf.length() -1; nCount++)
		{
			 digitoCPF = Integer.valueOf (cpf.substring(nCount -1, nCount)).intValue();

			 //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
			 d1 = d1 + ( 11 - nCount ) * digitoCPF;

			 //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
			 d2 = d2 + ( 12 - nCount ) * digitoCPF;
		};

		//Primeiro resto da divisão por 11.
		resto = (d1 % 11);

		//Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
		if (resto < 2)
			 digito1 = 0;
		else
			 digito1 = 11 - resto;

		d2 += 2 * digito1;

		//Segundo resto da divisão por 11.
		resto = (d2 % 11);

		//Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
		if (resto < 2)
			 digito2 = 0;
		else
			 digito2 = 11 - resto;

		//Digito verificador do CPF que está sendo validado.
		String nDigVerific = cpf.substring (cpf.length()-2, cpf.length());

		//Concatenando o primeiro resto com o segundo.
		nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

		//comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
		return nDigVerific.equals(nDigResult);

	} // fim do método validarCpf

}
