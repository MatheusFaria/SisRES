package persistence;

import model.Aluno;

import java.sql.*;
import java.util.Vector;

import exception.ClienteException;

public class AlunoDAO {

	//Mensagens
		private static final String ALUNO_JA_EXISTENTE = "O Aluno ja esta cadastrado.";
		private static final String ALUNO_NULO = "O Aluno esta nulo.";
		private static final String ALUNO_NAO_EXISTENTE = "O Aluno nao esta cadastrado.";
		private static final String ALUNO_EM_USO = "Sala esta sendo utilizada em uma reserva.";
		private static final String CPF_JA_EXISTENTE = "Ja existe um aluno cadastrado com esse CPF.";
		private static final String MATRICULA_JA_EXISTENTE = "Ja existe um aluno cadastrado com essa matricula.";
	
	//Singleton
		private static AlunoDAO instance;
		private AlunoDAO(){
		}
		public static AlunoDAO getInstance(){
			if(instance == null)
				instance = new AlunoDAO();
			return instance;
		}
	//
	
		
	public void incluir(Aluno aluno) throws SQLException, ClienteException {
		if(aluno == null)
			throw new ClienteException(ALUNO_NULO);
		else if(this.inDBCpf(aluno.getCpf()))
			throw new ClienteException(CPF_JA_EXISTENTE);
		else if(this.inDBMatricula(aluno.getMatricula()))
				throw new ClienteException(MATRICULA_JA_EXISTENTE);
		else if(!this.inDB(aluno))
		{
			this.updateQuery("INSERT INTO " +
					"aluno (nome, cpf, telefone, email, matricula) VALUES (" +
					"\"" + aluno.getNome() + "\", " +
					"\"" + aluno.getCpf()+ "\", " +
					"\"" + aluno.getTelefone() + "\", " +
					"\"" + aluno.getEmail() + "\", " +
					"\"" + aluno.getMatricula() + "\"); "
					);
		}
		else
			throw new ClienteException(ALUNO_JA_EXISTENTE);
	}

	public void alterar(Aluno aluno_velho, Aluno aluno_novo) throws SQLException, ClienteException {
		if(aluno_velho == null)
			throw new ClienteException(ALUNO_NULO);
		if(aluno_novo == null)
			throw new ClienteException(ALUNO_NULO);
		
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst;
		
		if(!this.inDB(aluno_velho))
			throw new ClienteException(ALUNO_NAO_EXISTENTE);
		else if(this.inOtherDB(aluno_velho))
			throw new ClienteException(ALUNO_EM_USO);
		else if(!aluno_velho.getCpf().equals(aluno_novo.getCpf()) && this.inDBCpf(aluno_novo.getCpf()))
			throw new ClienteException(CPF_JA_EXISTENTE);
		else if(!aluno_velho.getMatricula().equals(aluno_novo.getMatricula()) && this.inDBMatricula(aluno_novo.getMatricula()))
				throw new ClienteException(MATRICULA_JA_EXISTENTE);
		else if(!this.inDB(aluno_novo))
		{
			String msg = "UPDATE aluno SET " +
				"nome = \"" + aluno_novo.getNome() + "\", " +
				"cpf = \"" + aluno_novo.getCpf() + "\", " +
				"telefone = \"" + aluno_novo.getTelefone() + "\", " +
				"email = \"" + aluno_novo.getEmail() + "\", " +
				"matricula = \"" + aluno_novo.getMatricula() + "\""+
				" WHERE " +
				"aluno.nome = \"" + aluno_velho.getNome() + "\" and " +
				"aluno.cpf = \"" + aluno_velho.getCpf() + "\" and " +
				"aluno.telefone = \"" + aluno_velho.getTelefone() + "\" and " +
				"aluno.email = \"" + aluno_velho.getEmail() + "\" and " +
				"aluno.matricula = \"" + aluno_velho.getMatricula() + "\";";
			con.setAutoCommit(false);
			pst = con.prepareStatement(msg);
			pst.executeUpdate();
			con.commit();
		}
		else
			throw new ClienteException(ALUNO_JA_EXISTENTE);

		pst.close();
		con.close();
	}

	public void excluir(Aluno aluno) throws SQLException, ClienteException {
		if(aluno == null)
			throw new ClienteException(ALUNO_NULO);
		else if(this.inOtherDB(aluno))
			throw new ClienteException(ALUNO_EM_USO);
		else if(this.inDB(aluno)){
			this.updateQuery("DELETE FROM aluno WHERE " +
				"aluno.nome = \"" + aluno.getNome() + "\" and " +
				"aluno.cpf = \"" + aluno.getCpf() + "\" and " +
				"aluno.telefone = \"" + aluno.getTelefone() + "\" and " +
				"aluno.email = \"" + aluno.getEmail() + "\" and " +
				"aluno.matricula = \"" + aluno.getMatricula() + "\";"
				);
		}
		else
			throw new ClienteException(ALUNO_NAO_EXISTENTE);
	}

	
	
	public Vector<Aluno> buscarTodos() throws SQLException, ClienteException {
		return this.buscar("SELECT * FROM aluno;");
	}
	public Vector<Aluno> buscarNome(String valor) throws SQLException, ClienteException {
		return this.buscar("SELECT * FROM aluno WHERE nome = " + "\"" + valor + "\";");
	}
	public Vector<Aluno> buscarCpf(String valor) throws SQLException, ClienteException {
		return this.buscar("SELECT * FROM aluno WHERE cpf = " + "\"" + valor + "\";");
	}
	public Vector<Aluno> buscarMatricula(String valor) throws SQLException, ClienteException {
		return this.buscar("SELECT * FROM aluno WHERE matricula = " + "\"" + valor + "\";");
	}
	public Vector<Aluno> buscarEmail(String valor) throws SQLException, ClienteException {
		return this.buscar("SELECT * FROM aluno WHERE email = " + "\"" + valor + "\";");
	}
	public Vector<Aluno> buscarTelefone(String valor) throws SQLException, ClienteException {
		return this.buscar("SELECT * FROM aluno WHERE telefone = " + "\"" + valor + "\";");
	}
	
	
	/**
	 * Metodos Privados
	 * */
	
	private Vector<Aluno> buscar(String query) throws SQLException, ClienteException {
		Vector<Aluno> vet = new Vector<Aluno>();
		
		Connection con =  FactoryConnection.getInstance().getConnection();
		
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next())
			vet.add(this.fetchAluno(rs));
		
		pst.close();
		rs.close();
		con.close();
		return vet;
	}
	
	
	private boolean inDBGeneric(String query) throws SQLException{
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(query);
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
	private boolean inDB(Aluno aluno) throws SQLException{
		return this.inDBGeneric("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + aluno.getNome() + "\" and " +
				"aluno.cpf = \"" + aluno.getCpf() + "\" and " +
				"aluno.telefone = \"" + aluno.getTelefone() + "\" and " +
				"aluno.email = \"" + aluno.getEmail() + "\" and " +
				"aluno.matricula = \"" + aluno.getMatricula() + "\";");
	}
	private boolean inDBCpf(String codigo) throws SQLException{
		return this.inDBGeneric("SELECT * FROM aluno WHERE " +
				"aluno.cpf = \"" + codigo + "\";");
	}
	private boolean inDBMatricula(String codigo) throws SQLException{
		return this.inDBGeneric("SELECT * FROM aluno WHERE " +
				"aluno.matricula = \"" + codigo + "\";");
	}
	private boolean inOtherDB(Aluno aluno) throws SQLException, ClienteException{
		return this.inDBGeneric(
				"SELECT * FROM reserva_sala_aluno WHERE " +
				"id_aluno = (SELECT id_aluno FROM aluno WHERE " +
				"aluno.nome = \"" + aluno.getNome() + "\" and " +
				"aluno.cpf = \"" + aluno.getCpf() + "\" and " +
				"aluno.telefone = \"" + aluno.getTelefone() + "\" and " +
				"aluno.email = \"" + aluno.getEmail() + "\" and " +
				"aluno.matricula = \"" + aluno.getMatricula() + "\");");
	}
	
	
	private Aluno fetchAluno(ResultSet rs) throws ClienteException, SQLException{
		return new Aluno(rs.getString("nome"), rs.getString("cpf"), rs.getString("matricula"),
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
