package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Professor;
import model.ReservaSalaProfessor;
import model.Sala;

import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;

public class ResSalaProfessorDAO extends DAO{

	//Mensagens e Alertas
	private final String NULA = "Termo nulo.";
	private final String SALA_INDISPONIVEL = "A Sala esta reservada no mesmo dia e horario.";
	private final String PROFESSOR_INEXISTENTE = "Professor inexistente.";
	private final String SALA_INEXISTENTE = "Sala inexistente";
	private final String RESERVA_INEXISTENTE = "Reserva inexistente";
	private final String RESERVA_EXISTENTE = "A reserva ja existe.";
	//private final String RESERVA_CANCELADA = "Havia uma reserva feita por aluno que foi substituída.";
	
	
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
		//Querys de Reuso
				private String select_id_professor(Professor p){
					return "SELECT id_professor FROM professor WHERE " +
							"professor.nome = \"" + p.getNome() + "\" and " +
							"professor.cpf = \"" + p.getCpf() + "\" and " +
							"professor.telefone = \"" + p.getTelefone() + "\" and " +
							"professor.email = \"" + p.getEmail() + "\" and " +
							"professor.matricula = \"" + p.getMatricula() + "\"";
				}
				private String select_id_sala(Sala sala){
					return "SELECT id_sala FROM sala WHERE " +
							"sala.codigo = \"" + sala.getCodigo() + "\" and " +
							"sala.descricao = \"" + sala.getDescricao() +  "\" and " +
							"sala.capacidade = " + sala.getCapacidade();
				}
				private String where_reserva_sala_professor(ReservaSalaProfessor r){
					return " WHERE " +
					"id_professor = ( " + select_id_professor(r.getProfessor()) + " ) and " +
					"id_sala = ( " + select_id_sala(r.getSala()) + " ) and " +
					"finalidade = \"" + r.getFinalidade() + "\" and " +
					"hora = \"" + r.getHora() + "\" and " +
					"data = \"" + r.getData();
				}
				private String values_reserva_sala_professor(ReservaSalaProfessor r){
					return "( " + select_id_professor(r.getProfessor()) + " ), " +
					"( " + select_id_sala(r.getSala()) + " ), " +
					"\"" + r.getFinalidade() + "\", " +
					"\"" + r.getHora() + "\", " +
					"\"" + r.getData();
				}
				private String atributes_value_reserva_sala_professor(ReservaSalaProfessor r){
					return "id_professor = ( " + select_id_professor(r.getProfessor()) + " ), " +
					"id_sala = ( " + select_id_sala(r.getSala()) + " ), " +
					"finalidade = \"" + r.getFinalidade() + "\", " +
					"hora = \"" + r.getHora() + "\", " +
					"data = \"" + r.getData();
				}
				private String insert_into(ReservaSalaProfessor r){
					return "INSERT INTO " +
							"reserva_sala_professor (id_professor, id_sala, finalidade, hora, data) " +
							"VALUES ( " + values_reserva_sala_professor(r) + " );";
				}
				private String update(ReservaSalaProfessor r, ReservaSalaProfessor r2){
					return "UPDATE reserva_sala_professor SET " + 
							this.atributes_value_reserva_sala_professor(r2) +
							this.where_reserva_sala_professor(r) + " ;";
				}
				private String delete_from_professor(ReservaSalaProfessor r){
					return "DELETE FROM reserva_sala_professor " + this.where_reserva_sala_professor(r) + " ;";
				}
				
				private String delete_from_aluno(ReservaSalaProfessor r){
					return "DELETE FROM reserva_sala_aluno WHERE " +
							"hora = \"" + r.getHora() + "\" and " +
							"data = \"" + r.getData() +  " ;";
				}
				
	public void incluir(ReservaSalaProfessor r) throws ReservaException, SQLException {
		if(r == null)
			throw new ReservaException(NULA);
		else if(!this.professorinDB(r.getProfessor()))
			throw new ReservaException(PROFESSOR_INEXISTENTE);
		else if(!this.salainDB(r.getSala()))
			throw new ReservaException(SALA_INEXISTENTE);
		else if(this.salainReservaDB(r.getSala(), r.getData(), r.getHora()))
			throw new ReservaException(SALA_INDISPONIVEL);
		else if(this.professorinReservaDB(r.getProfessor(), r.getData(), r.getHora()))
			throw new ReservaException(RESERVA_EXISTENTE);
		else {
			super.executeQuery(this.delete_from_aluno(r));
			super.executeQuery(this.insert_into(r));
		}
		/*
		if(this.reserva_alunoinDB(r)) {	
			throw new ReservaException(RESERVA_CANCELADA);
		}
		*/
	}
	
	public void alterar(ReservaSalaProfessor r, ReservaSalaProfessor r_new) throws ReservaException, SQLException {
		if(r == null)
			throw new ReservaException(NULA);
		else if(r_new == null)
			throw new ReservaException(NULA);
		
		else if(!this.reservainDB(r))
			throw new ReservaException(RESERVA_INEXISTENTE);
		else if(this.reservainDB(r_new))
			throw new ReservaException(RESERVA_EXISTENTE);
		else if(!r.getData().equals(r_new.getData()) || !r.getHora().equals(r_new.getHora())) {
			if(this.professorinReservaDB(r_new.getProfessor(), r_new.getData(), r_new.getHora()))
				throw new ReservaException(RESERVA_EXISTENTE);//Perguntar pro Matheus
			else if(this.salainReservaDB(r_new.getSala(), r_new.getData(), r_new.getHora()))
				throw new ReservaException(SALA_INDISPONIVEL);
		}
		else if(!this.professorinDB(r_new.getProfessor()))
			throw new ReservaException(PROFESSOR_INEXISTENTE);
		else if(!this.salainDB(r_new.getSala()))
			throw new ReservaException(SALA_INEXISTENTE);
		else
			super.updateQuery(this.update(r, r_new));
	}
	
	public void excluir(ReservaSalaProfessor r) throws ReservaException, SQLException {
		if(r == null)
			throw new ReservaException(NULA);
		else if(!this.reservainDB(r))
			throw new ReservaException(RESERVA_INEXISTENTE);
		else
			super.executeQuery(this.delete_from_professor(r));
	}
	
	//TODO implementar todos os tipos de Busca, uma para cada campo. PUBLIC
	public Vector<Object> buscarTodos() throws SQLException, ClienteException, PatrimonioException, ReservaException{
		return super.buscar("SELECT * FROM reserva_sala_professor " +
				"INNER JOIN sala ON sala.id_sala = reserva_sala_professor.id_sala " +
				"INNER JOIN professor ON professor.id_professor = reserva_sala_professor.id_professor;");
	}
		
	
	@Override
	protected Object fetch(ResultSet rs) throws SQLException, ClienteException, PatrimonioException, ReservaException {
		Professor p = new Professor(rs.getString("nome"), rs.getString("cpf"), rs.getString("matricula"),
				rs.getString("telefone"), rs.getString("email"));
		
		Sala s = new Sala(rs.getString("codigo"), rs.getString("descricao"), rs.getString("capacidade"));
		
		ReservaSalaProfessor r = new ReservaSalaProfessor(rs.getString("data"),rs.getString("hora"),
				s ,rs.getString("finalidade"), p);
		
		return r;
	}
	
	private boolean professorinDB(Professor professor) throws SQLException{
		return super.inDBGeneric("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + professor.getNome() + "\" and " +
				"professor.cpf = \"" + professor.getCpf() + "\" and " +
				"professor.telefone = \"" + professor.getTelefone() + "\" and " +
				"professor.email = \"" + professor.getEmail() + "\" and " +
				"professor.matricula = \"" + professor.getMatricula() + "\";");
	}
	
	private boolean salainDB(Sala sala) throws SQLException{
		return super.inDBGeneric("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + sala.getCodigo() + "\" and " +
				"sala.descricao = \"" + sala.getDescricao() + "\" and " +
				"sala.capacidade = " + sala.getCapacidade() +
				";");
	}
	
	private boolean professorinReservaDB(Professor professor, String data, String hora) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_professor WHERE " +
				"data = \"" + data + "\" and " +
				"hora = \"" + hora + "\" and " +
				"id_professor = (SELECT id_professor FROM professor WHERE " +
				"professor.nome = \"" + professor.getNome() + "\" and " +
				"professor.cpf = \"" + professor.getCpf() + "\" and " +
				"professor.telefone = \"" + professor.getTelefone() + "\" and " +
				"professor.email = \"" + professor.getEmail() + "\" and " +
				"professor.matricula = \"" + professor.getMatricula() + "\");");
	}
	private boolean salainReservaDB(Sala sala, String data, String hora) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_professor WHERE " +
				"data = \"" + data + "\" and " +
				"hora = \"" + hora + "\" and " +
				"id_sala = (SELECT id_sala FROM sala WHERE " +
				"sala.codigo = \"" + sala.getCodigo() + "\" and " +
				"sala.descricao = \"" + sala.getDescricao() +  "\" and " +
				"sala.capacidade = " + sala.getCapacidade() +" );");
	}
	
	private boolean reservainDB(ReservaSalaProfessor r) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_professor WHERE " +
					"id_professor = (SELECT id_professor FROM professor WHERE " +
							"professor.nome = \"" + r.getProfessor().getNome() + "\" and " +
							"professor.cpf = \"" + r.getProfessor().getCpf() + "\" and " +
							"professor.telefone = \"" + r.getProfessor().getTelefone() + "\" and " +
							"professor.email = \"" + r.getProfessor().getEmail() + "\" and " +
							"professor.matricula = \"" + r.getProfessor().getMatricula() + "\") and " +
					"id_sala = (SELECT id_sala FROM sala WHERE " +
									"sala.codigo = \"" + r.getSala().getCodigo() + "\" and " +
									"sala.descricao = \"" + r.getSala().getDescricao() +  "\" and " +
									"sala.capacidade = " + r.getSala().getCapacidade() +" ) and " +
					"finalidade = \"" + r.getFinalidade() + "\" and " +
					"hora = \"" + r.getHora() + "\" and " +
					"data = \"" + r.getData() + ";");
	}
	/*
	private boolean reserva_alunoinDB(ReservaSalaProfessor r) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_aluno WHERE " +
					"id_sala = (SELECT id_sala FROM sala WHERE " +
									"sala.codigo = \"" + r.getSala().getCodigo() + "\" and " +
									"sala.descricao = \"" + r.getSala().getDescricao() +  "\" and " +
									"sala.capacidade = " + r.getSala().getCapacidade() +" ) and " +
					"finalidade = \"" + r.getFinalidade() + "\" and " +
					"hora = \"" + r.getHora() + "\" and " +
					"data = \"" + r.getData() + ";");
	}
	*/

}