package persistence;

import model.Professor;

import java.sql.*;
import java.util.Vector;

import exception.ClienteException;

public class ProfessorDAO {

	private static final String PROFESSOR_JA_EXISTENTE = "O Professor ja esta cadastrado.";
	
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
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM cliente WHERE " +
				"cliente.nome = \"" + prof.getNome() + "\" and " +
				"cliente.cpf = \"" + prof.getCpf() + "\" and " +
				"cliente.telefone = \"" + prof.getTelefone() + "\" and " +
				"cliente.email = \"" + prof.getEmail() + "\" and " +
				"cliente.matricula = \"" + prof.getMatricula() + "\";");
		ResultSet rs = pst.executeQuery();
		
		if(!rs.next())
		{
			this.updateQuery("INSERT INTO " +
					"cliente (nome, cpf, telefone, email, matricula) VALUES (" +
					"\"" + prof.getNome() + "\", " +
					"\"" + prof.getCpf()+ "\", " +
					"\"" + prof.getTelefone() + "\", " +
					"\"" + prof.getEmail() + "\", " +
					"\"" + prof.getMatricula() + "\"); "
				);
		}
		else {
			pst = con.prepareStatement("SELECT * FROM professor WHERE " +
				"id_cliente = " + rs.getString("id_cliente"));
			rs = pst.executeQuery();
		}
		
		if(!rs.next())
		{
			this.updateQuery("INSERT INTO professor (id_cliente) " +
				"SELECT id_cliente FROM cliente WHERE " +
				"cliente.nome = \"" + prof.getNome() + "\" and " +
				"cliente.cpf = \"" + prof.getCpf() + "\" and " +
				"cliente.telefone = \"" + prof.getTelefone() + "\" and " +
				"cliente.email = \"" + prof.getEmail() + "\" and " +
				"cliente.matricula = \"" + prof.getMatricula() + "\";"
			);
		}
		else{
			throw new ClienteException(PROFESSOR_JA_EXISTENTE);
		}
		
		rs.close();
		pst.close();
		con.close();
	}

	public void alterar(Professor prof_velho, Professor prof_novo) throws SQLException {			
		this.updateQuery("UPDATE cliente SET " +
				"nome = \"" + prof_novo.getNome() + "\", " +
				"cpf = \"" + prof_novo.getCpf() + "\", " +
				"telefone = \"" + prof_novo.getTelefone() + "\", " +
				"email = \"" + prof_novo.getEmail() + "\", " +
				"matricula = \"" + prof_novo.getMatricula() + "\""+
				" WHERE " +
				"cliente.nome = \"" + prof_velho.getNome() + "\" and " +
				"cliente.cpf = \"" + prof_velho.getCpf() + "\" and " +
				"cliente.telefone = \"" + prof_velho.getTelefone() + "\" and " +
				"cliente.email = \"" + prof_velho.getEmail() + "\" and " +
				"cliente.matricula = \"" + prof_velho.getMatricula() + "\";"
				);
	}

	public void excluir(Professor prof) throws SQLException {
		this.updateQuery("DELETE FROM professor WHERE id_cliente = " +
				"(SELECT id_cliente FROM cliente WHERE " +
				"cliente.nome = \"" + prof.getNome() + "\" and " +
				"cliente.cpf = \"" + prof.getCpf() + "\" and " +
				"cliente.telefone = \"" + prof.getTelefone() + "\" and " +
				"cliente.email = \"" + prof.getEmail() + "\" and " +
				"cliente.matricula = \"" + prof.getMatricula() + "\");"
				);
		
		this.updateQuery("DELETE FROM cliente WHERE " +
				"cliente.nome = \"" + prof.getNome() + "\" and " +
				"cliente.cpf = \"" + prof.getCpf() + "\" and " +
				"cliente.telefone = \"" + prof.getTelefone() + "\" and " +
				"cliente.email = \"" + prof.getEmail() + "\" and " +
				"cliente.matricula = \"" + prof.getMatricula() + "\";"
				);
	}

	public Professor buscar(Professor prof) throws SQLException {
		//TODO
		return null;
	}

	public Vector<Professor> buscarTodos() throws SQLException, ClienteException {
		Vector<Professor> vet = new Vector<Professor>();
		
		Connection con =  FactoryConnection.getInstance().getConnection();
		
		PreparedStatement pst = con.prepareStatement("SELECT * FROM professor;");
		ResultSet rs = pst.executeQuery();
		ResultSet rs2 = null;
		
		while(rs.next())
		{
			pst = con.prepareStatement("SELECT * FROM cliente WHERE id_cliente = " 
															+ rs.getString("id_cliente"));
			rs2 = pst.executeQuery();
			vet.add(this.fetchProfessor(rs2));
		}
		
		pst.close();
		rs.close();
		if(rs2 != null)
			rs2.close();
		con.close();
		return vet;
	}
	
	private Professor fetchProfessor(ResultSet rs) throws ClienteException, SQLException{
		rs.next();
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
