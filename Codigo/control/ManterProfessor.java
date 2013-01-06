package control;

import java.sql.SQLException;
import java.util.Vector;

import persistence.ProfessorDAO;
import exception.ClienteException;
import model.Professor;

public class ManterProfessor {

	private static ManterProfessor instance;
	private Vector<Professor> professores_vet = new Vector<Professor>();//eh necessario iniciar o vetor com o getProfessores_vet
	
	private ManterProfessor() {
	}
	public static ManterProfessor getInstance() {
		if(instance == null)
			instance = new ManterProfessor();
		return instance;
	}

	
	public Vector<Professor> getProfessores_vet() throws SQLException, ClienteException{
		this.professores_vet = ProfessorDAO.getInstance().buscarTodos();
		return this.professores_vet;
	}
	
	public void inserir(String nome, String cpf, String matricula,
			String telefone, String email) throws ClienteException, SQLException {
		Professor prof = new Professor(nome, cpf, matricula, telefone, email);
		ProfessorDAO.getInstance().incluir(prof);
		this.professores_vet.add(prof);
	}

	public void alterar(String nome, String cpf, String matricula,
			String telefone, String email, Professor prof) throws ClienteException, SQLException {
		Professor prof_velho = new Professor(
								prof.getNome(),
								prof.getCpf(),
								prof.getMatricula(),
								prof.getTelefone(),
								prof.getEmail());
		prof.setNome(nome);
		prof.setCpf(cpf);
		prof.setMatricula(matricula);
		prof.setTelefone(telefone);
		prof.setEmail(email);
		ProfessorDAO.getInstance().alterar(prof_velho, prof);
	}

	public void excluir(Professor professor) throws SQLException {
		ProfessorDAO.getInstance().excluir(professor);
		this.professores_vet.remove(professor);
	}

}
