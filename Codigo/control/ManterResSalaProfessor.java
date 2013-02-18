package control;

import java.sql.SQLException;
import java.util.Vector;

import model.Professor;
import model.ReservaSalaProfessor;
import model.Sala;
import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;

public class ManterResSalaProfessor {
	private Vector<Object> rev_sala_professor_vet = new Vector<Object>();
	
	//Singleton
		private static ManterResSalaProfessor instance;
		private ManterResSalaProfessor() {
		}
		public static ManterResSalaProfessor getInstance() {
		if(instance == null)
			instance = new ManterResSalaProfessor();
		return instance;
	}
	//
		
	public Vector<Object> getResProfessorSala_vet() {
		return null;
	}

	public void inserir() throws PatrimonioException, SQLException, ClienteException, ReservaException {
		//TODO Com todos os parametros como String.
	}
	
	public void inserir(Sala sala, Professor prof,
						String data, String hora, String finalidade, String cadeiras_reservadas) 
					throws SQLException, ReservaException {

		//TODO Falta o corpo deste metodo
	}

	public void alterar(String finalidade, ReservaSalaProfessor r) 
				throws SQLException, ReservaException {
		//TODO Falta o corpo deste metodo
		
	}

	public void excluir(ReservaSalaProfessor r) throws SQLException, ReservaException {
		//TODO Falta o corpo deste metodo
	}
}
