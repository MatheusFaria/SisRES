package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import exception.ReservaException;

public class Reserva {

	private String hora;
	private String data;
	
	//Mesagens, Alertas e Padroes
		private final String HORA_NULA = "A hora esta nula.";
		private final String HORA_INVALIDA = "A hora eh invalida.";
		private final String HORA_BRANCA = "A hora esta em branco.";
		private final String HORA_JA_PASSOU = "A hora escolhida ja passou.";
		private final String HORA_PATTERN = "^[012][\\d]:[\\d][\\d]$";
		private final String DATA_NULA = "A data esta nula.";
		private final String DATA_INVALIDA = "A data eh invalida.";
		private final String DATA_BRANCA = "A data esta em branco.";
		private final String DATA_JA_PASSOU = "A data escolhida ja passou.";
		private final String DATA_PATTERN = "^[0123]?[\\d]([./-])[01]?[\\d]\\1[\\d]{2,4}$";
	
	public Reserva(String data, String hora) throws ReservaException {
		this.setData(data);
		this.setHora(hora);
	}

	public String getHora() {
		return this.hora;
	}

	public String getData() {
		return this.data;
	}

	public void setHora(String hora) throws ReservaException {
		if(hora == null)
			throw new ReservaException(HORA_NULA);
		
		hora = hora.trim();
		if(hora.equals(""))
			throw new ReservaException(HORA_BRANCA);
		else if(hora.matches(HORA_PATTERN)){
			if(this.horaPassou(hora))
				throw new ReservaException(HORA_JA_PASSOU);
			else
				this.hora = hora;
		}
		else
			throw new ReservaException(HORA_INVALIDA);
	}

	public void setData(String data) throws ReservaException {
		if(data == null)
			throw new ReservaException(DATA_NULA);
		
		data = data.trim();
		if(data.equals(""))
			throw new ReservaException(DATA_BRANCA);
		else if(data.matches(DATA_PATTERN)){
			if(this.dataPassou(data))
				throw new ReservaException(DATA_JA_PASSOU);
			else
				this.data = data;
		}
		else
			throw new ReservaException(DATA_INVALIDA);
		this.data = data;
	}

	public boolean equals(Reserva obj) {
		return (this.hora.equals(obj.getHora()) &&
			this.data.equals(obj.getData()));
	}
	
	@Override
	public String toString() {
		return "Reserva [hora=" + this.hora + ", data=" + this.data + "]";
	}
	
	private String dataAtual(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		return formatador.format(date);
	}
	
	private String horaAtual(){
		Date date = new Date(System.currentTimeMillis());
		return date.toString().substring(11, 16);
	}
	
	private boolean dataPassou(String d){
		String agora[] = this.dataAtual().split("[./-]");
		String data[] = d.split("[./-]");
		
		int dif = agora[2].length() - data[2].length();
		data[2] = agora[2].substring(0, dif) + data[2];
		
		if(Integer.parseInt(agora[2]) > Integer.parseInt(data[2]))
			return true;
		
		dif = agora[1].length() - data[1].length();
		data[1] = agora[1].substring(0, dif) + data[1];
		
		if(Integer.parseInt(agora[1]) > Integer.parseInt(data[1]))
			return true;
		
		dif = agora[0].length() - data[0].length();
		data[0] = agora[0].substring(0, dif) + data[0];
		
		if(Integer.parseInt(agora[0]) > Integer.parseInt(data[0]))
			return true;
		
		return false;
	}
	
	private boolean horaPassou(String hora){
		String agora = this.horaAtual();
		if(Integer.parseInt(agora.substring(0, 2)) > Integer.parseInt(hora.substring(0, 2)))
			return true;
		else if(Integer.parseInt(agora.substring(0, 2)) == Integer.parseInt(hora.substring(0, 2))){
			if(Integer.parseInt(agora.substring(3, 5)) > Integer.parseInt(hora.substring(3, 5)))
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	
}
