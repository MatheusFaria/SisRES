package control;

import java.sql.SQLException;
import java.util.Vector;

import persistence.ResSalaProfessorDAO;

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
		
	public Vector<Object> getResProfessorSala_vet() throws SQLException, ClienteException, PatrimonioException, ReservaException {
		this.rev_sala_professor_vet = ResSalaProfessorDAO.getInstance().buscarTodos();
		return this.rev_sala_professor_vet;
	}

	public void inserir(String codigo, String descricao, String capacidade, 
			String nome, String cpf, String matricula, String telefone, String email,
			String data, String hora, String finalidade) 
				throws PatrimonioException, SQLException, ClienteException, ReservaException {
		
		Sala sala = new Sala(codigo, descricao, capacidade);
		Professor professor = new Professor(nome, cpf, matricula, telefone, email);
		ReservaSalaProfessor reserva = new ReservaSalaProfessor(data, hora, sala, finalidade, professor);
		ResSalaProfessorDAO.getInstance().incluir(reserva);
		this.rev_sala_professor_vet.add(reserva);
	}
	
	public void inserir(Sala sala, Professor prof,
						String data, String hora, String finalidade, String cadeiras_reservadas) 
					throws SQLException, ReservaException {

		ReservaSalaProfessor reserva = new ReservaSalaProfessor(data, hora, sala , finalidade, prof);
		ResSalaProfessorDAO.getInstance().incluir(reserva);
		this.rev_sala_professor_vet.add(reserva);
	}

	public void alterar(String finalidade, ReservaSalaProfessor reserva) 
				throws SQLException, ReservaException {
		
		ReservaSalaProfessor reserva_old = new ReservaSalaProfessor(reserva.getData(), reserva.getHora(), reserva.getSala() , 
				reserva.getFinalidade(), reserva.getProfessor());
		
		reserva.setFinalidade(finalidade);
		ResSalaProfessorDAO.getInstance().alterar(reserva_old, reserva);
		
	}

	public void excluir(ReservaSalaProfessor r) throws SQLException, ReservaException {
		ResSalaProfessorDAO.getInstance().excluir(r);
		this.rev_sala_professor_vet.remove(r);
	}
}
