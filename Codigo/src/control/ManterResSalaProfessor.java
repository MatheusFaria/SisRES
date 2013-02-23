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

	public Vector<ReservaSalaProfessor> getReservasHora(String hora) throws SQLException, PatrimonioException, ClienteException, ReservaException{
		return ResSalaProfessorDAO.getInstance().buscarPorHora(hora);
		
	}
	
	public Vector<ReservaSalaProfessor> getReservasMes(int mes) throws SQLException, PatrimonioException, ClienteException, ReservaException{
		return ResSalaProfessorDAO.getInstance().buscarPorMes(mes);
	}
		
		
	public Vector<Object> getResProfessorSala_vet() throws SQLException, ClienteException, PatrimonioException, ReservaException {
		this.rev_sala_professor_vet = ResSalaProfessorDAO.getInstance().buscarTodos();
		return this.rev_sala_professor_vet;
	}

	public void inserir(Sala sala, Professor prof,
						String data, String hora, String finalidade) 
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

	public void excluir(ReservaSalaProfessor reserva) throws SQLException, ReservaException {
		ResSalaProfessorDAO.getInstance().excluir(reserva);
		this.rev_sala_professor_vet.remove(reserva);
	}
}