package persistence;

import model.Professor;

import java.sql.*;
import java.util.Vector;

import exception.ClienteException;

public class ProfessorDAO {

	//Mensagens
		private static final String PROFESSOR_JA_EXISTENTE = "O Professor ja esta cadastrado.";
		private static final String PROFESSOR_NAO_EXISTENTE = "O Professor nao esta cadastrado.";
		private static final String PROFESSOR_NULO = "O Aluno esta nulo.";
		private static final String PROFESSOR_EM_USO = "Sala esta sendo utilizada em uma reserva.";
		private static final String CPF_JA_EXISTENTE = "Ja existe um aluno cadastrado com esse CPF.";
		private static final String MATRICULA_JA_EXISTENTE = "Ja existe um aluno cadastrado com essa matricula.";
	
	//Singleton
		private static ProfessorDAO instance;
		private ProfessorDAO(){
		}
		public static ProfessorDAO getInstance(){
			if(instance == null)
				instance = new ProfessorDAO();
			return instance;
		}
	//
	
	
	public void incluir(Professor prof) throws SQLException, ClienteException {
			if(prof == null)
				throw new ClienteException(PROFESSOR_NULO);
			else if(this.inDBCpf(prof.getCpf()))
				throw new ClienteException(CPF_JA_EXISTENTE);
			else if(this.inDBMatricula(prof.getMatricula()))
				throw new ClienteException(MATRICULA_JA_EXISTENTE);
			else if(!this.inDB(prof))
			{
				this.updateQuery("INSERT INTO " +
						"professor (nome, cpf, telefone, email, matricula) VALUES (" +
						"\"" + prof.getNome() + "\", " +
						"\"" + prof.getCpf()+ "\", " +
						"\"" + prof.getTelefone() + "\", " +
						"\"" + prof.getEmail() + "\", " +
						"\"" + prof.getMatricula() + "\"); "
					);
			}
			else
				throw new ClienteException(PROFESSOR_JA_EXISTENTE);
			
	}

	public void alterar(Professor prof_velho, Professor prof_novo) throws SQLException, ClienteException {
		if(prof_velho == null)
			throw new ClienteException(PROFESSOR_NULO);
		if(prof_novo == null)
			throw new ClienteException(PROFESSOR_NULO);
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst;
		
		if(!this.inDB(prof_velho))
			throw new ClienteException(PROFESSOR_NAO_EXISTENTE);
		if(this.inOtherDB(prof_velho))
			throw new ClienteException(PROFESSOR_EM_USO);
		else if(this.inDBCpf(prof_novo.getCpf()))
			throw new ClienteException(CPF_JA_EXISTENTE);
		else if(this.inDBMatricula(prof_novo.getMatricula()))
			throw new ClienteException(MATRICULA_JA_EXISTENTE);
		else if(!this.inDB(prof_novo)){
			String msg = "UPDATE professor SET " +
					"nome = \"" + prof_novo.getNome() + "\", " +
					"cpf = \"" + prof_novo.getCpf() + "\", " +
					"telefone = \"" + prof_novo.getTelefone() + "\", " +
					"email = \"" + prof_novo.getEmail() + "\", " +
					"matricula = \"" + prof_novo.getMatricula() + "\""+
					" WHERE " +
					"professor.nome = \"" + prof_velho.getNome() + "\" and " +
					"professor.cpf = \"" + prof_velho.getCpf() + "\" and " +
					"professor.telefone = \"" + prof_velho.getTelefone() + "\" and " +
					"professor.email = \"" + prof_velho.getEmail() + "\" and " +
					"professor.matricula = \"" + prof_velho.getMatricula() + "\";";
			con.setAutoCommit(false);
			pst = con.prepareStatement(msg);
			pst.executeUpdate();
			con.commit();
		}else
			throw new ClienteException(PROFESSOR_JA_EXISTENTE);
		
		pst.close();
		con.close();
	}

	public void excluir(Professor prof) throws SQLException, ClienteException {
		if(prof == null)
			throw new ClienteException(PROFESSOR_NULO);
		if(this.inOtherDB(prof))
			throw new ClienteException(PROFESSOR_EM_USO);
		else if(this.inDB(prof)){
			this.updateQuery("DELETE FROM professor WHERE " +
				"professor.nome = \"" + prof.getNome() + "\" and " +
				"professor.cpf = \"" + prof.getCpf() + "\" and " +
				"professor.telefone = \"" + prof.getTelefone() + "\" and " +
				"professor.email = \"" + prof.getEmail() + "\" and " +
				"professor.matricula = \"" + prof.getMatricula() + "\";"
				);
		}
		else
			throw new ClienteException(PROFESSOR_NAO_EXISTENTE);
	}

	
	
	public Vector<Professor> buscarTodos() throws SQLException, ClienteException {
		return this.buscar("SELECT * FROM professor;");
	}
	public Vector<Professor> buscarNome(String valor) throws SQLException, ClienteException {
		return this.buscar("SELECT * FROM professor WHERE nome = " + "\"" + valor + "\";");
	}
	public Vector<Professor> buscarCpf(String valor) throws SQLException, ClienteException {
		return this.buscar("SELECT * FROM professor WHERE cpf = " + "\"" + valor + "\";");
	}
	public Vector<Professor> buscarMatricula(String valor) throws SQLException, ClienteException {
		return this.buscar("SELECT * FROM professor WHERE matricula = " + "\"" + valor + "\";");
	}
	public Vector<Professor> buscarEmail(String valor) throws SQLException, ClienteException {
		return this.buscar("SELECT * FROM professor WHERE email = " + "\"" + valor + "\";");
	}
	public Vector<Professor> buscarTelefone(String valor) throws SQLException, ClienteException {
		return this.buscar("SELECT * FROM professor WHERE telefone = " + "\"" + valor + "\";");
	}

	
	/**
	 * Metodos Privados
	 * */
	
	private Vector<Professor> buscar(String query) throws SQLException, ClienteException {
		Vector<Professor> vet = new Vector<Professor>();
		
		Connection con =  FactoryConnection.getInstance().getConnection();
		
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next())
			vet.add(this.fetchProfessor(rs));
		
		pst.close();
		rs.close();
		con.close();
		return vet;
	}
	
	
	private boolean inDB(Professor prof) throws SQLException{
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM professor WHERE " +
				"professor.nome = \"" + prof.getNome() + "\" and " +
				"professor.cpf = \"" + prof.getCpf() + "\" and " +
				"professor.telefone = \"" + prof.getTelefone() + "\" and " +
				"professor.email = \"" + prof.getEmail() + "\" and " +
				"professor.matricula = \"" + prof.getMatricula() + "\";");
		ResultSet rs = pst.executeQuery();
		
		if(!rs.next()){
			rs.close();
			pst.close();
			con.close();
			return false;
		}
		else {
			rs.close();
			pst.close();
			con.close();
			return true;
		}
	}
	private boolean inDBCpf(String codigo) throws SQLException{
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM professor WHERE " +
				"cpf = \"" + codigo + "\";");
		ResultSet rs = pst.executeQuery();
		
		if(!rs.next())
		{
			rs.close();
			pst.close();
			con.close();
			return false;
		}
		else {
			rs.close();
			pst.close();
			con.close();
			return true;
		}
	}
	private boolean inDBMatricula(String codigo) throws SQLException{
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM professor WHERE " +
				"matricula = \"" + codigo + "\";");
		ResultSet rs = pst.executeQuery();
		
		if(!rs.next())
		{
			rs.close();
			pst.close();
			con.close();
			return false;
		}
		else {
			rs.close();
			pst.close();
			con.close();
			return true;
		}
	}
	private boolean inOtherDB(Professor prof) throws SQLException{
		Connection con =  FactoryConnection.getInstance().getConnection();
		
		PreparedStatement pst = con.prepareStatement(
				"SELECT * FROM reserva_sala_professor WHERE " +
				"id_professor = (SELECT id_professor FROM professor WHERE " +
				"professor.nome = \"" + prof.getNome() + "\" and " +
				"professor.cpf = \"" + prof.getCpf() + "\" and " +
				"professor.telefone = \"" + prof.getTelefone() + "\" and " +
				"professor.email = \"" + prof.getEmail() + "\" and " +
				"professor.matricula = \"" + prof.getMatricula() + "\");");
		ResultSet rs = pst.executeQuery();
		
		if(rs.next())
		{
			rs.close();
			pst.close();
			con.close();
			return true;
		}
		
		pst = con.prepareStatement(
				"SELECT * FROM reserva_equipamento WHERE " +
				"id_professor = (SELECT id_professor FROM professor WHERE " +
				"professor.nome = \"" + prof.getNome() + "\" and " +
				"professor.cpf = \"" + prof.getCpf() + "\" and " +
				"professor.telefone = \"" + prof.getTelefone() + "\" and " +
				"professor.email = \"" + prof.getEmail() + "\" and " +
				"professor.matricula = \"" + prof.getMatricula() + "\");");
		rs = pst.executeQuery();
		
		if(rs.next())
		{
			rs.close();
			pst.close();
			con.close();
			return true;
		}
		
		rs.close();
		pst.close();
		con.close();
		return false;
	}
	
	
	private Professor fetchProfessor(ResultSet rs) throws ClienteException, SQLException{
		return new Professor(rs.getString("nome"), rs.getString("cpf"), rs.getString("matricula"),
				rs.getString("telefone"), rs.getString("email"));
	}
	
	private void updateQuery(String msg) throws SQLException{
		Connection con =  FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(msg);
		pst.executeUpdate();		
		pst.close();
		con.close();
	}

}
