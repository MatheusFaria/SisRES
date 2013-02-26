package model;

import exception.ReservaException;

public class ReservaSalaAluno extends ReservaSala{
	
	private Aluno aluno;
	private String cadeiras_reservadas;
	
	//Mensagens
		private final String ALUNO_NULO = "O aluno esta nulo.";
		private final String CADEIRAS_NULA = "O numero de cadeiras esta nulo.";
		private final String CADEIRAS_BRANCO = "O numero de cadeiras esta em branco.";
		private final String CADEIRAS_INVALIDA = "O numero de cadeira eh invalido.";
		private final String CADEIRAS_ACIMA_DO_LIMITE = "A sala nao possui este numero de cadeiras para reservar.";
		private final String CADEIRAS_PATTERN = "^[\\d]+$";

	public ReservaSalaAluno(String data, String hora, Sala sala,
			String finalidade, String cadeiras_reservadas, Aluno aluno) throws ReservaException {
		super(data, hora, sala, finalidade);
		this.setAluno(aluno);
		this.setCadeiras_reservadas(cadeiras_reservadas);
	}
	
	public Aluno getAluno() {
		return this.aluno;
	}

	public String getCadeiras_reservadas() {
		return this.cadeiras_reservadas;
	}

	public void setAluno(Aluno aluno) throws ReservaException {
		if(aluno == null)
			throw new ReservaException(ALUNO_NULO);
		this.aluno = aluno;
	}

	public void setCadeiras_reservadas(String cadeiras_reservadas) throws ReservaException {
		String c = cadeiras_reservadas;
		if(c == null)
			throw new ReservaException(CADEIRAS_NULA);
		c = c.trim();
		if(c.equals(""))
			throw new ReservaException(CADEIRAS_BRANCO);
		else if(c.matches(CADEIRAS_PATTERN)){
			if(Integer.parseInt(super.getSala().getCapacidade()) < Integer.parseInt(cadeiras_reservadas))
				throw new ReservaException(CADEIRAS_ACIMA_DO_LIMITE);
			else
				this.cadeiras_reservadas = cadeiras_reservadas;
		}
		else
			throw new ReservaException(CADEIRAS_INVALIDA);
	}


	public boolean equals(ReservaSalaAluno obj) {
		return (super.equals(obj) &&
				this.getAluno().equals(obj.getAluno()) &&
				this.getCadeiras_reservadas().equals(obj.getCadeiras_reservadas())
				);
	}

	@Override
	public String toString() {
		return "Aluno: " + this.getAluno().toString()
			+ "\nCadeiras Reservadas: " + this.getCadeiras_reservadas() 
			+ super.toString();
	}

}
