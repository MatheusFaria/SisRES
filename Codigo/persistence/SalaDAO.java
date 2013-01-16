package persistence;

import model.Sala;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import exception.PatrimonioException;

public class SalaDAO {

		private static final String SALA_JA_EXISTENTE = "Sala ja cadastrada.";
	
	//Singleton
		private static SalaDAO instance;
		private SalaDAO(){
		}
		public static SalaDAO getInstance(){
			if(instance == null)
				instance = new SalaDAO();
			return instance;
		}
	//

	public void incluir(Sala sala) throws SQLException, PatrimonioException {
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + sala.getCodigo() + "\" and " +
				"sala.descricao = \"" + sala.getDescricao() + "\" and " +
				"sala.capacidade = \"" + sala.getCapacidade() +
				"\";");
		ResultSet rs = pst.executeQuery();
		
		if(!rs.next())
		{
			this.updateQuery("INSERT INTO " +
					"sala (codigo, descricao, capacidade) VALUES (" +
					"\"" + sala.getCodigo() + "\", " +
					"\"" + sala.getDescricao() + "\", " +
					"\"" + sala.getCapacidade() +
					"\"); "
				);
		}
		else {
			throw new PatrimonioException(SALA_JA_EXISTENTE);
		}
				
		rs.close();
		pst.close();
		con.close();
	}


	public void alterar(Sala old_sala, Sala new_sala) throws SQLException {			
		String msg = "UPDATE sala SET " +				
				"codigo = \"" + new_sala.getCodigo() + "\", " +
				"descricao = \"" + new_sala.getDescricao() + "\", " +
				"capacidade = " + new_sala.getCapacidade()+
				"\"" +
				" WHERE " +
				"sala.codigo = \"" + old_sala.getCodigo() + "\" and " +
				"sala.descricao = \"" + old_sala.getDescricao() +  "\";";

		Connection con =  FactoryConnection.getInstance().getConnection();
		con.setAutoCommit(false);
		PreparedStatement pst = con.prepareStatement(msg);
		pst.executeUpdate();
		con.commit();
		pst.close();
		con.close();
	}

	public void excluir(Sala sala) throws SQLException {
		this.updateQuery("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + sala.getCodigo() + "\" and " +
				"sala.descricao = \"" + sala.getDescricao() +  "\" and " +
				"sala.capacidade = \"" + sala.getCapacidade() + "\";"				
				);		
	}

	public Sala buscar() {
		//TODO
		return null;
	}

	public Vector<Sala> buscarTodos() throws SQLException, PatrimonioException {
		Vector<Sala> vet = new Vector<Sala>();
		
		Connection con =  FactoryConnection.getInstance().getConnection();
		
		PreparedStatement pst = con.prepareStatement("SELECT * FROM sala;");
		ResultSet rs = pst.executeQuery();
		ResultSet rs2 = null;
		
		while(rs.next())
		{
			pst = con.prepareStatement("SELECT * FROM sala WHERE id_sala = " 
															+ rs.getString("id_sala"));
			rs2 = pst.executeQuery();
			vet.add(this.fetchSala(rs2));
		}
		
		pst.close();
		rs.close();
        if(rs2 != null)
        	rs2.close();
		con.close();
		return vet;
	}
	
	private Sala fetchSala(ResultSet rs2) throws PatrimonioException, SQLException{
		rs2.next();
		return new Sala(rs2.getString("codigo"), rs2.getString("descricao"), rs2.getString("capacidade"));
	}
	
	private void updateQuery(String msg) throws SQLException{
		Connection con =  FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(msg);
		pst.executeUpdate();		
		pst.close();
		con.close();
	}

}
