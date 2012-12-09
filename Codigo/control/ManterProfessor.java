package control;

import model.Professor;

public class ManterProfessor {

	private static ManterProfessor instance;

	private ManterProfessor() {
	}

	public static ManterProfessor getInstance() {
		if(instance == null)
			instance = new ManterProfessor();
		return instance;
	}

	
	
	public void inserir(String nome, int cpf, int matricula, int telefone, String email) {
		//TODO
	}

	public void alterar(String nome, int cpf, int matricula, int telefone, String email, Professor professor) {
		//TODO
	}

	public void excluir(Professor professor) {
		//TODO
	}

}
