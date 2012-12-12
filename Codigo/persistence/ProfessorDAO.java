package persistence;

import model.Professor;

import java.sql.*;
import java.util.Vector;

public class ProfessorDAO {

	private Connection con = FactoryConnection.getConnection();
	
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
	
	public void incluir(Professor prof) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = con.prepareStatement("INSERT INTO cliente(nome, cpf, telefone, email, matricula) " +
					"VALUES (\"" + prof.getNome() + 
							"\", \"" + prof.getCpf()+ "\"," +
							" \"" + prof.getTelefone() +
							"\", \"" + prof.getEmail() +
							"\", \"" + prof.getMatricula() +
					"\"");
			rs = pst.executeQuery();
			
			pst = con.prepareStatement("INSERT INTO professor(id_cliente) (SELECT id_cliente From cliente WHERE " +
					"cliente.nome = \"" + prof.getNome() + 
							"\" and cliente.cpf = \"" + prof.getCpf()+ "\" " +
							" \" and cliente.telefone = " + prof.getTelefone() +
							"\" and cliente.email = \"" + prof.getEmail() +
							"\"and cliente.matricula = \"" + prof.getMatricula() +
					"\")");
			rs = pst.executeQuery();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void alterar(Professor prof) {
		
		Statement st = null;
				
		try {
			st = con.createStatement();
			st.executeUpdate("UPDATE cliente SET nome=\"" + prof.getNome() + 
					"\", cpf=\"" + prof.getCpf() +
					"\", telefone= \"" + prof.getTelefone() +
					"\", email=\"" + prof.getEmail() +
					"\", matricula=\"" + prof.getMatricula() + "\""+
					
					"WHERE nome=\"" + prof.getNome() + 
					"\"and cpf=\"" + prof.getCpf() +
					"\"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void excluir(Professor prof) {
		
	}

	public Professor buscar(Professor prof) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = con.prepareStatement("INSERT INTO cliente(nome, cpf, telefone, email, matricula) " +
					"VALUES (\""+ prof.getCpf()+ "\"," +
							" \"" + prof.getTelefone() +
							"\", \"" + prof.getEmail() +
							"\", \"" + prof.getMatricula() +
					"\"");
			rs = pst.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public Vector<Professor> buscarTodos() {
		//TODO
		return null;
	}

}
