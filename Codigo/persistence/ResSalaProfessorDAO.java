package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;

public class ResSalaProfessorDAO extends DAO{

	//Mensagens e Alertas
	
	
	//Singleton
		private static ResSalaProfessorDAO instance;
		private ResSalaProfessorDAO(){
		}
		public static ResSalaProfessorDAO getInstance(){
			if(instance == null)
				instance = new ResSalaProfessorDAO();
			return instance;
		}
	//
	
	public void incluir() {
		// TODO Auto-generated method stub
	}
	
	public void alterar() {
		// TODO Auto-generated method stub
	}
	
	public void excluir() {
		// TODO Auto-generated method stub
	}
	
	//TODO implementar todos os tipos de Busca, uma para cada campo. PUBLIC
	public Vector<Object> buscarTodos() throws SQLException, ClienteException, PatrimonioException, ReservaException{
		return super.buscar("SELECT * FROM reserva_sala_professor " +
				"INNER JOIN sala ON sala.id_sala = reserva_sala_aluno.id_sala " +
				"INNER JOIN professor ON professor.id_professor = reserva_sala_professor.id_professor;");
	}
	
	//TODO implementar os inDB necessarios. PRIVATE
	
	
	@Override
	protected Object fetch(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}