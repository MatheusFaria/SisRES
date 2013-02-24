package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import model.Professor;
import model.ReservaEquipamentoProfessor;
import model.Equipamento;

import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;

public class ResEquipamentoProfessorDAO  extends DAO{

	//Mensagens e Alertas
		private final String NULA = "Termo nulo.";
		private final String EQUIPAMENTO_INDISPONIVEL = "O Equipamento esta reservada no mesmo dia e horario.";
		private final String PROFESSOR_INEXISTENTE = "Professor inexistente.";
		private final String EQUIPAMENTO_INEXISTENTE = "Equipamento inexistente";
		private final String RESERVA_INEXISTENTE = "Reserva inexistente";
		private final String RESERVA_EXISTENTE = "A reserva ja existe.";
	
	
	//Singleton
		private static ResEquipamentoProfessorDAO instance;
		private ResEquipamentoProfessorDAO(){
		}
		public static ResEquipamentoProfessorDAO getInstance(){
			if(instance == null)
				instance = new ResEquipamentoProfessorDAO();
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
		private String select_id_equipamento(Equipamento equipamento){
			return "SELECT id_equipamento FROM equipamento WHERE " +
					"equipamento.codigo = \"" + equipamento.getCodigo() + "\" and " +
					"equipamento.descricao = \"" + equipamento.getDescricao();
		}
		private String where_reserva_equipamento_professor(ReservaEquipamentoProfessor r){
			return " WHERE " +
					"id_professor = ( " + select_id_professor(r.getProfessor()) + " ) and " +
					"id_equipamento = ( " + select_id_equipamento(r.getEquipamento()) + " ) and " +
					"hora = \"" + r.getHora() + "\" and " +
					"data = \"" + r.getData();
		}
		private String values_reserva_equipamento_professor(ReservaEquipamentoProfessor r){
			return "( " + select_id_professor(r.getProfessor()) + " ), " +
			"( " + select_id_equipamento(r.getEquipamento()) + " ), " +
			"\"" + r.getHora() + "\", " +
			"\"" + r.getData();
		}
		private String atributes_value_reserva_equipamento_professor(ReservaEquipamentoProfessor r){
			return "id_professor = ( " + select_id_professor(r.getProfessor()) + " ), " +
			"id_equipamento = ( " + select_id_equipamento(r.getEquipamento()) + " ), " +
			"hora = \"" + r.getHora() + "\", " +
			"data = \"" + r.getData();
		}
		private String insert_into(ReservaEquipamentoProfessor r){
			return "INSERT INTO " +
					"reserva_equipamento_professor (id_professor, id_equipamento, hora, data) " +
					"VALUES ( " + values_reserva_equipamento_professor(r) + " );";
		}
		private String update(ReservaEquipamentoProfessor r, ReservaEquipamentoProfessor r2){
			return "UPDATE reserva_equipamento_professor SET " + 
					this.atributes_value_reserva_equipamento_professor(r2) +
					this.where_reserva_equipamento_professor(r) + " ;";
		}
		private String delete_from_professor(ReservaEquipamentoProfessor r){
			return "DELETE FROM reserva_equipamento_professor " + this.where_reserva_equipamento_professor(r) + " ;";
		}

		private String delete_from_aluno(ReservaEquipamentoProfessor r){
			return "DELETE FROM reserva_equipamento_aluno WHERE " +
					"hora = \"" + r.getHora() + "\" and " +
					"data = \"" + r.getData() +  " ;";
		}

public void incluir(ReservaEquipamentoProfessor r) throws ReservaException, SQLException {
	if(r == null)
		throw new ReservaException(NULA);
	else if(!this.professorinDB(r.getProfessor()))
		throw new ReservaException(PROFESSOR_INEXISTENTE);
	else if(!this.equipamentoinDB(r.getEquipamento()))
		throw new ReservaException(EQUIPAMENTO_INEXISTENTE);
	else if(this.equipamentoinReservaDB(r.getEquipamento(), r.getData(), r.getHora()))
		throw new ReservaException(EQUIPAMENTO_INDISPONIVEL);
	else if(this.professorinReservaDB(r.getProfessor(), r.getData(), r.getHora()))
		throw new ReservaException(RESERVA_EXISTENTE);
	else {
		super.executeQuery(this.delete_from_aluno(r));
		super.executeQuery(this.insert_into(r));
	}
	
}

public void alterar(ReservaEquipamentoProfessor r, ReservaEquipamentoProfessor r_new) throws ReservaException, SQLException {
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
		else if(this.equipamentoinReservaDB(r_new.getEquipamento(), r_new.getData(), r_new.getHora()))
			throw new ReservaException(EQUIPAMENTO_INDISPONIVEL);
	}
	else if(!this.professorinDB(r_new.getProfessor()))
		throw new ReservaException(PROFESSOR_INEXISTENTE);
	else if(!this.equipamentoinDB(r_new.getEquipamento()))
		throw new ReservaException(EQUIPAMENTO_INEXISTENTE);
	else
		super.updateQuery(this.update(r, r_new));
}

public void excluir(ReservaEquipamentoProfessor r) throws ReservaException, SQLException {
	if(r == null)
		throw new ReservaException(NULA);
	else if(!this.reservainDB(r))
		throw new ReservaException(RESERVA_INEXISTENTE);
	else
		super.executeQuery(this.delete_from_professor(r));
}


}
