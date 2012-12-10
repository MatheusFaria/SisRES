package control;

import java.util.Vector;

import persistence.ProfessorDAO;
import exception.ClienteException;
import model.Professor;

public class ManterProfessor {

	private static ManterProfessor instance;
	private Vector<Professor> professores_vet = ProfessorDAO.getInstance().buscarTodos();
	
	private ManterProfessor() {
	}
	public static ManterProfessor getInstance() {
		if(instance == null)
			instance = new ManterProfessor();
		return instance;
	}

	
	public Vector<Professor> getProfessores_vet(){
		return professores_vet;
	}
	
	public void inserir(String nome, String cpf, String matricula,
			String telefone, String email) throws ClienteException {
		Professor prof = new Professor(nome, cpf, matricula, telefone, email);
		ProfessorDAO.getInstance().incluir(prof);
		this.professores_vet.add(prof);
	}

	public void alterar(String nome, String cpf, String matricula,
			String telefone, String email, Professor prof) throws ClienteException {
		prof.setNome(nome);
		prof.setCpf(cpf);
		prof.setMatricula(matricula);
		prof.setTelefone(telefone);
		prof.setEmail(email);
		ProfessorDAO.getInstance().alterar(prof);
	}

	public void excluir(Professor professor) {
		ProfessorDAO.getInstance().excluir(professor);
		this.professores_vet.remove(professor);
	}

}
