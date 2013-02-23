package control;

import java.sql.SQLException;
import java.util.Vector;

import model.Aluno;
import model.ReservaSalaAluno;
import model.Sala;
import persistence.ResSalaAlunoDAO;
import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;

public class ManterResSalaAluno {

	private Vector<ReservaSalaAluno> rev_sala_aluno_vet = new Vector<ReservaSalaAluno>();
	//Singleton
	private static ManterResSalaAluno instance;

	private ManterResSalaAluno() {
	}

	public static ManterResSalaAluno getInstance() {
		if (instance == null) {
			instance = new ManterResSalaAluno();
		}
		return instance;
	}
	//

	public Vector<ReservaSalaAluno> getReservasHora(String hora) throws SQLException, PatrimonioException, ClienteException, ReservaException{
		return ResSalaAlunoDAO.getInstance().buscarPorHora(hora);
		
	}
	
	public Vector<ReservaSalaAluno> getReservasMes(String data) throws SQLException, PatrimonioException, ClienteException, ReservaException{
		return ResSalaAlunoDAO.getInstance().buscarPorDia(data);
	}
	
	public Vector<ReservaSalaAluno> getResAlunoSala_vet() throws SQLException, PatrimonioException, ClienteException, ReservaException {
		this.rev_sala_aluno_vet = ResSalaAlunoDAO.getInstance().buscarTodos();
		return this.rev_sala_aluno_vet;
	}

	public int cadeirasDisponveis(Sala sala, String data, String hora) throws SQLException, PatrimonioException, ClienteException, ReservaException {
		return ResSalaAlunoDAO.getInstance().cadeirasDisponiveis(sala, data, hora);
	}

	public void inserir(Sala sala, Aluno aluno,
		String data, String hora, String finalidade, String cadeiras_reservadas)
		throws SQLException, ReservaException, ClienteException, PatrimonioException {

		ReservaSalaAluno r = new ReservaSalaAluno(data, hora, sala, finalidade, cadeiras_reservadas, aluno);
		ResSalaAlunoDAO.getInstance().incluir(r);
		this.rev_sala_aluno_vet.add(r);
	}

	public void alterar(String finalidade, String cadeiras_reservadas, ReservaSalaAluno r)
		throws SQLException, ReservaException, ClienteException, PatrimonioException {

		ReservaSalaAluno res_old = new ReservaSalaAluno(r.getData(), r.getHora(), r.getSala(),
			r.getFinalidade(), r.getCadeiras_reservadas(), r.getAluno());
		r.setFinalidade(finalidade);
		r.setCadeiras_reservadas(cadeiras_reservadas);
		ResSalaAlunoDAO.getInstance().alterar(res_old, r);
	}

	public void excluir(ReservaSalaAluno r) throws SQLException, ReservaException {
		ResSalaAlunoDAO.getInstance().excluir(r);
		this.rev_sala_aluno_vet.remove(r);
	}
}
