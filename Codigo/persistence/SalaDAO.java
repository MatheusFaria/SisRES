package persistence;

import model.Sala;

import java.sql.Connection;
import java.util.Vector;

public class SalaDAO {

	private Connection con = FactoryConnection.getConnection();
	
	//Singleton
		private static SalaDAO instance;
		private SalaDAO(){
		}
		public static SalaDAO getInstance(){
			if(instance == null)
				instance = new SalaDAO();
			return instance;
		}
	//

	public void incluir(Sala sala) {
		//TODO
	}

	public void alterar(Sala sala) {
		//TODO
	}

	public void excluir(Sala sala) {
		//TODO
	}

	public Sala buscar() {
		//TODO
		return null;
	}

	public Vector<Sala> buscarTodos() {
		//TODO
		return null;
	}

}
