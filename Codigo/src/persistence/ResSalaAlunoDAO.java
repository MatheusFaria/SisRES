package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;

import model.Aluno;
import model.ReservaSalaAluno;
import model.Sala;

public class ResSalaAlunoDAO extends DAO{
	
	//Mensagens e Alertas
		private final String NULA = "Termo nulo.";
		private final String ALUNO_INDISPONIVEL = "O aluno possui uma reserva no mesmo dia e horario.";
		private final String SALA_INDISPONIVEL = "A Sala esta reservada no mesmo dia e horario.";
		private final String ALUNO_INEXISTENTE = "Aluno inexistente.";
		private final String SALA_INEXISTENTE = "Sala inexistente";
		private final String RESERVA_INEXISTENTE = "Reserva inexistente";
		private final String RESERVA_EXISTENTE = "A reserva ja existe.";
		private final String CADEIRAS_INDISPONIVEIS = "O numero de cadeiras reservadas esta indisponivel para esta sala.";

	
	//Singleton
		private static ResSalaAlunoDAO instance;
		private ResSalaAlunoDAO(){
		}
		public static ResSalaAlunoDAO getInstance(){
			if(instance == null)
				instance = new ResSalaAlunoDAO();
			return instance;
		}
	//
		
		
	//Querys de Reuso
		private String select_id_aluno(Aluno a){
			return "SELECT id_aluno FROM aluno WHERE " +
					"aluno.nome = \"" + a.getNome() + "\" and " +
					"aluno.cpf = \"" + a.getCpf() + "\" and " +
					"aluno.telefone = \"" + a.getTelefone() + "\" and " +
					"aluno.email = \"" + a.getEmail() + "\" and " +
					"aluno.matricula = \"" + a.getMatricula() + "\"";
		}
		private String select_id_sala(Sala sala){
			return "SELECT id_sala FROM sala WHERE " +
					"sala.codigo = \"" + sala.getCodigo() + "\" and " +
					"sala.descricao = \"" + sala.getDescricao() +  "\" and " +
					"sala.capacidade = " + sala.getCapacidade();
		}
		private String where_reserva_sala_aluno(ReservaSalaAluno r){
			return " WHERE " +
			"id_aluno = ( " + select_id_aluno(r.getAluno()) + " ) and " +
			"id_sala = ( " + select_id_sala(r.getSala()) + " ) and " +
			"finalidade = \"" + r.getFinalidade() + "\" and " +
			"hora = \"" + r.getHora() + "\" and " +
			"data = \"" + r.getData() + "\" and " +
			"cadeiras_reservadas = " + r.getCadeiras_reservadas();
		}
		private String values_reserva_sala_aluno(ReservaSalaAluno r){
			return "( " + select_id_aluno(r.getAluno()) + " ), " +
			"( " + select_id_sala(r.getSala()) + " ), " +
			"\"" + r.getFinalidade() + "\", " +
			"\"" + r.getHora() + "\", " +
			"\"" + r.getData() + "\", " +
			r.getCadeiras_reservadas();
		}
		private String atibutes_value_reserva_sala_aluno(ReservaSalaAluno r){
			return "id_aluno = ( " + select_id_aluno(r.getAluno()) + " ), " +
			"id_sala = ( " + select_id_sala(r.getSala()) + " ), " +
			"finalidade = \"" + r.getFinalidade() + "\", " +
			"hora = \"" + r.getHora() + "\", " +
			"data = \"" + r.getData() + "\", " +
			"cadeiras_reservadas = " + r.getCadeiras_reservadas();
		}
		private String insert_into(ReservaSalaAluno r){
			return "INSERT INTO " +
					"reserva_sala_aluno (id_aluno, id_sala, finalidade, hora, data, cadeiras_reservadas) " +
					"VALUES ( " + values_reserva_sala_aluno(r) + " );";
		}
		private String update(ReservaSalaAluno r, ReservaSalaAluno r2){
			return "UPDATE reserva_sala_aluno SET " + 
					this.atibutes_value_reserva_sala_aluno(r2) +
					this.where_reserva_sala_aluno(r) + " ;";
		}
		private String delete_from(ReservaSalaAluno r){
			return "DELETE FROM reserva_sala_aluno " + this.where_reserva_sala_aluno(r) + " ;";
		}

		
		
	public void incluir(ReservaSalaAluno r) throws ReservaException, SQLException, ClienteException, PatrimonioException {
		if(r == null)
			throw new ReservaException(NULA);
		else if(!this.alunoinDB(r.getAluno()))
			throw new ReservaException(ALUNO_INEXISTENTE);
		else if(!this.salainDB(r.getSala()))
			throw new ReservaException(SALA_INEXISTENTE);
		else if(this.salainReservaProfessorDB(r.getSala(), r.getData(), r.getHora()))
			throw new ReservaException(SALA_INDISPONIVEL);
		else if(this.alunoinReservaDB(r.getAluno(), r.getData(), r.getHora()))
			throw new ReservaException(ALUNO_INDISPONIVEL);
		else if(!this.haCadeiras(r.getCadeiras_reservadas(), r.getSala()))
			throw new ReservaException(CADEIRAS_INDISPONIVEIS);
		else
			super.executeQuery(this.insert_into(r));
	}
	
	public void alterar(ReservaSalaAluno r, ReservaSalaAluno r_new) throws ReservaException, SQLException, ClienteException, PatrimonioException{
		if(r == null)
			throw new ReservaException(NULA);
		else if(r_new == null)
			throw new ReservaException(NULA);
		
		else if(!this.reservainDB(r))
			throw new ReservaException(RESERVA_INEXISTENTE);
		else if(this.reservainDB(r_new))
			throw new ReservaException(RESERVA_EXISTENTE);
		else if(!this.alunoinDB(r_new.getAluno()))
			throw new ReservaException(ALUNO_INEXISTENTE);
		else if(!this.salainDB(r_new.getSala()))
			throw new ReservaException(SALA_INEXISTENTE);
		else if(!r.getData().equals(r_new.getData()) || !r.getHora().equals(r_new.getHora())){
			if(this.alunoinReservaDB(r_new.getAluno(), r_new.getData(), r_new.getHora()))
				throw new ReservaException(ALUNO_INDISPONIVEL);
			else if(this.salainReservaProfessorDB(r_new.getSala(), r_new.getData(), r_new.getHora()))
				throw new ReservaException(SALA_INDISPONIVEL);
		}
		if(!this.haCadeiras(""+(Integer.parseInt(r_new.getCadeiras_reservadas()) - 
				Integer.parseInt(r.getCadeiras_reservadas()))
				, r_new.getSala()))
			throw new ReservaException(CADEIRAS_INDISPONIVEIS);
		else
			super.updateQuery(this.update(r, r_new));
			
	}
	
	public void excluir(ReservaSalaAluno r) throws ReservaException, SQLException {
		if(r == null)
			throw new ReservaException(NULA);
		else if(!this.reservainDB(r))
			throw new ReservaException(RESERVA_INEXISTENTE);
		else
			super.executeQuery(this.delete_from(r));
	}
	
	//TODO implementar todos os tipos de Busca, uma para cada campo. PUBLIC
	public Vector<Object> buscarTodos() throws SQLException, ClienteException, PatrimonioException, ReservaException{
		return super.buscar("SELECT * FROM reserva_sala_aluno " +
				"INNER JOIN sala ON sala.id_sala = reserva_sala_aluno.id_sala " +
				"INNER JOIN aluno ON aluno.id_aluno = reserva_sala_aluno.id_aluno;");
	}

	
	private boolean haCadeiras(String cadeiras_reservadas, Sala sala) throws SQLException, ClienteException, PatrimonioException, ReservaException {
		Vector<Object> vet = this.buscarTodos();
		Iterator<Object> it = vet.iterator();
		int total = Integer.parseInt(sala.getCapacidade());
		while(it.hasNext()){
			ReservaSalaAluno r = (ReservaSalaAluno) it.next();
			if(r.getSala().equals(sala))
				total -= Integer.parseInt(r.getCadeiras_reservadas());
		}
		int c = Integer.parseInt(cadeiras_reservadas);
		if(Integer.parseInt(cadeiras_reservadas) < 0)
			c *= -1;
		if(total >= c)
			return true;
		return false;
	}
	
	@Override
	protected Object fetch(ResultSet rs) throws SQLException, ClienteException, PatrimonioException, ReservaException {
		Aluno a = new Aluno(rs.getString("nome"), rs.getString("cpf"), rs.getString("matricula"),
				rs.getString("telefone"), rs.getString("email"));
		
		Sala s = new Sala(rs.getString("codigo"), rs.getString("descricao"), rs.getString("capacidade"));
		
		ReservaSalaAluno r = new ReservaSalaAluno(rs.getString("data"),rs.getString("hora"),
				s ,rs.getString("finalidade"),rs.getString("cadeiras_reservadas"), a);
		
		return r;
	}
	
	private boolean alunoinDB(Aluno aluno) throws SQLException{
		return super.inDBGeneric("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + aluno.getNome() + "\" and " +
				"aluno.cpf = \"" + aluno.getCpf() + "\" and " +
				"aluno.telefone = \"" + aluno.getTelefone() + "\" and " +
				"aluno.email = \"" + aluno.getEmail() + "\" and " +
				"aluno.matricula = \"" + aluno.getMatricula() + "\";");
	}
	
	private boolean salainDB(Sala sala) throws SQLException{
		return super.inDBGeneric("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + sala.getCodigo() + "\" and " +
				"sala.descricao = \"" + sala.getDescricao() + "\" and " +
				"sala.capacidade = " + sala.getCapacidade() +
				";");
	}
	
	private boolean alunoinReservaDB(Aluno aluno, String data, String hora) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_aluno WHERE " +
				"data = \"" + data + "\" and " +
				"hora = \"" + hora + "\" and " +
				"id_aluno = (SELECT id_aluno FROM aluno WHERE " +
				"aluno.nome = \"" + aluno.getNome() + "\" and " +
				"aluno.cpf = \"" + aluno.getCpf() + "\" and " +
				"aluno.telefone = \"" + aluno.getTelefone() + "\" and " +
				"aluno.email = \"" + aluno.getEmail() + "\" and " +
				"aluno.matricula = \"" + aluno.getMatricula() + "\");");
	}
	private boolean salainReservaSalaDB(Sala sala, String data, String hora) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_aluno WHERE " +
				"data = \"" + data + "\" and " +
				"hora = \"" + hora + "\" and " +
				"id_sala = (SELECT id_sala FROM sala WHERE " +
				"sala.codigo = \"" + sala.getCodigo() + "\" and " +
				"sala.descricao = \"" + sala.getDescricao() +  "\" and " +
				"sala.capacidade = " + sala.getCapacidade() +" );");
	}
	private boolean salainReservaProfessorDB(Sala sala, String data, String hora) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_professor WHERE " +
				"data = \"" + data + "\" and " +
				"hora = \"" + hora + "\" and " +
				"id_sala = (SELECT id_sala FROM sala WHERE " +
				"sala.codigo = \"" + sala.getCodigo() + "\" and " +
				"sala.descricao = \"" + sala.getDescricao() +  "\" and " +
				"sala.capacidade = " + sala.getCapacidade() +" );");
	}
	
	private boolean reservainDB(ReservaSalaAluno r) throws SQLException {
		return super.inDBGeneric("SELECT * FROM reserva_sala_aluno WHERE " +
					"id_aluno = (SELECT id_aluno FROM aluno WHERE " +
							"aluno.nome = \"" + r.getAluno().getNome() + "\" and " +
							"aluno.cpf = \"" + r.getAluno().getCpf() + "\" and " +
							"aluno.telefone = \"" + r.getAluno().getTelefone() + "\" and " +
							"aluno.email = \"" + r.getAluno().getEmail() + "\" and " +
							"aluno.matricula = \"" + r.getAluno().getMatricula() + "\") and " +
					"id_sala = (SELECT id_sala FROM sala WHERE " +
									"sala.codigo = \"" + r.getSala().getCodigo() + "\" and " +
									"sala.descricao = \"" + r.getSala().getDescricao() +  "\" and " +
									"sala.capacidade = " + r.getSala().getCapacidade() +" ) and " +
					"finalidade = \"" + r.getFinalidade() + "\" and " +
					"hora = \"" + r.getHora() + "\" and " +
					"data = \"" + r.getData() + "\" and " +
					"cadeiras_reservadas = " + r.getCadeiras_reservadas() + ";");
	}

}
