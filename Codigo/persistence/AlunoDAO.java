package persistence;

import model.Aluno;

import java.sql.*;
import java.util.Vector;

import exception.ClienteException;

public class AlunoDAO {

	private static final String ALUNO_JA_EXISTENTE = "O Aluno ja esta cadastrado.";
	
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
			Connection con = FactoryConnection.getInstance().getConnection();
			PreparedStatement pst = con.prepareStatement("SELECT * FROM aluno WHERE " +
					"aluno.nome = \"" + aluno.getNome() + "\" and " +
					"aluno.cpf = \"" + aluno.getCpf() + "\" and " +
					"aluno.telefone = \"" + aluno.getTelefone() + "\" and " +
					"aluno.email = \"" + aluno.getEmail() + "\" and " +
					"aluno.matricula = \"" + aluno.getMatricula() + "\";");
			ResultSet rs = pst.executeQuery();
			
			if(!rs.next())
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
			else {
				throw new ClienteException(ALUNO_JA_EXISTENTE);
			}
			
			rs.close();
			pst.close();
			con.close();
		}

	public void alterar(Aluno aluno_velho, Aluno aluno_novo) throws SQLException, ClienteException {
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM aluno WHERE " +
				"aluno.nome = \"" + aluno_novo.getNome() + "\" and " +
				"aluno.cpf = \"" + aluno_novo.getCpf() + "\" and " +
				"aluno.telefone = \"" + aluno_novo.getTelefone() + "\" and " +
				"aluno.email = \"" + aluno_novo.getEmail() + "\" and " +
				"aluno.matricula = \"" + aluno_novo.getMatricula() + "\";");
		ResultSet rs = pst.executeQuery();
		
		if(!rs.next())
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
		else {
			throw new ClienteException(ALUNO_JA_EXISTENTE);
		}
		
		rs.close();
		pst.close();
		con.close();
	}

	public void excluir(Aluno aluno) throws SQLException {
		this.updateQuery("DELETE FROM aluno WHERE " +
				"aluno.nome = \"" + aluno.getNome() + "\" and " +
				"aluno.cpf = \"" + aluno.getCpf() + "\" and " +
				"aluno.telefone = \"" + aluno.getTelefone() + "\" and " +
				"aluno.email = \"" + aluno.getEmail() + "\" and " +
				"aluno.matricula = \"" + aluno.getMatricula() + "\";"
				);
	}

	public Aluno buscar(Aluno aluno) throws SQLException {
		//TODO
		return null;
	}

	public Vector<Aluno> buscarTodos() throws SQLException, ClienteException {
		Vector<Aluno> vet = new Vector<Aluno>();
		
		Connection con =  FactoryConnection.getInstance().getConnection();
		
		PreparedStatement pst = con.prepareStatement("SELECT * FROM aluno;");
		ResultSet rs = pst.executeQuery();
		ResultSet rs2 = null;
		
		while(rs.next())
		{
			pst = con.prepareStatement("SELECT * FROM aluno WHERE id_aluno = " 
															+ rs.getString("id_aluno"));
			rs2 = pst.executeQuery();
			vet.add(this.fetchAluno(rs2));
		}
		
		pst.close();
		rs.close();
                if(rs2 != null)
                    rs2.close();
		con.close();
		return vet;
	}
	
	private Aluno fetchAluno(ResultSet rs) throws ClienteException, SQLException{
		rs.next();
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
