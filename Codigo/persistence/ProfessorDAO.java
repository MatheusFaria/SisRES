package persistence;

import model.Professor;

import java.sql.Connection;
import java.util.Vector;

public class ProfessorDAO {

	private Connection con = FactoryConnection.getConnection();
	
	//Singleton
		private static ProfessorDAO instance;
		private ProfessorDAO(){
		}
		public static ProfessorDAO getInstance(){
			if(instance == null)
				instance = new ProfessorDAO();
			return instance;
		}
	//
	
	public void incluir(Professor prof) {
		//TODO
	}

	public void alterar(Professor prof) {
		//TODO
	}

	public void excluir(Professor prof) {
		//TODO
	}

	public Professor buscar() {
		//TODO
		return null;
	}

	public Vector<Professor> buscarTodos() {
		//TODO
		return null;
	}

}
