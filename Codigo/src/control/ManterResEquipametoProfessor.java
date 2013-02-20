package control;

import java.sql.SQLException;
import java.util.Vector;

import model.Equipamento;
import model.Professor;
import model.ReservaEquipamentoProfessor;
import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;

public class ManterResEquipametoProfessor {
	private Vector<Object> rev_equipamento_professor_vet = new Vector<Object>();
	
	//Singleton
		private static ManterResEquipametoProfessor instance;
		private ManterResEquipametoProfessor() {
		}
		public static ManterResEquipametoProfessor getInstance() {
		if(instance == null)
			instance = new ManterResEquipametoProfessor();
		return instance;
	}
	//
		
	public Vector<Object> getResEquipamentoProfessor_vet() {
		return null;
	}

	public void inserir() throws PatrimonioException, SQLException, ClienteException, ReservaException {
		//TODO Com todos os parametros como String.
	}
	
	public void inserir(Equipamento e, Professor prof,
						String data, String hora) 
					throws SQLException, ReservaException {

		//TODO Falta o corpo deste metodo
	}

	public void alterar(ReservaEquipamentoProfessor r) 
				throws SQLException, ReservaException {
		//TODO Falta o corpo deste metodo
		
	}

	public void excluir(ReservaEquipamentoProfessor r) throws SQLException, ReservaException {
		//TODO Falta o corpo deste metodo
	}
}
