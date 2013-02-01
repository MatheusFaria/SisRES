package control;

import java.sql.SQLException;
import java.util.Vector;

import persistence.AlunoDAO;
import exception.ClienteException;
import model.Aluno;

public class ManterAluno {

	private static ManterAluno instance;
	private Vector<Aluno> alunos_vet = new Vector<Aluno>();

	private ManterAluno() {
	}

	public static ManterAluno getInstance() {
		if (instance == null) {
			instance = new ManterAluno();
		}
		return instance;
	}

	public Vector<Aluno> getAluno_vet() throws SQLException, ClienteException {
		this.alunos_vet = AlunoDAO.getInstance().buscarTodos();
		return this.alunos_vet;
	}

	public void inserir(String nome, String cpf, String matricula,
		String telefone, String email) throws ClienteException, SQLException {
		Aluno aluno = new Aluno(nome, cpf, matricula, telefone, email);
		AlunoDAO.getInstance().incluir(aluno);
		this.alunos_vet.add(aluno);
	}

	public void alterar(String nome, String cpf, String matricula,
		String telefone, String email, Aluno aluno) throws ClienteException, SQLException {
		Aluno aluno_velho = new Aluno(
			aluno.getNome(),
			aluno.getCpf(),
			aluno.getMatricula(),
			aluno.getTelefone(),
			aluno.getEmail());
		aluno.setNome(nome);
		aluno.setCpf(cpf);
		aluno.setMatricula(matricula);
		aluno.setTelefone(telefone);
		aluno.setEmail(email);
		AlunoDAO.getInstance().alterar(aluno_velho, aluno);
	}

	public void excluir(Aluno aluno) throws SQLException, ClienteException {
		AlunoDAO.getInstance().excluir(aluno);
		this.alunos_vet.remove(aluno);
	}
}
