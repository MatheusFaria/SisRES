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
		private static final String SALA_NAO_EXISTENTE = "Sala nao cadastrada.";
	
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
		if(this.inDB(sala)){
			throw new PatrimonioException(SALA_JA_EXISTENTE);
		}
		else {
			this.updateQuery("INSERT INTO " +
					"sala (codigo, descricao, capacidade) VALUES (" +
					"\"" + sala.getCodigo() + "\", " +
					"\"" + sala.getDescricao() + "\", " +
					"\"" + sala.getCapacidade() +
					"\"); "
				);
		}
	}


	public void alterar(Sala old_sala, Sala new_sala) throws SQLException, PatrimonioException {
	
		if(old_sala == null || new_sala == null)
			throw new PatrimonioException("Equipamento invalido");
		
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst;
		
		if(!this.inDB(old_sala))
			throw new PatrimonioException(SALA_NAO_EXISTENTE);
		
		else if(!this.inDB(new_sala)){
			String msg = "UPDATE sala SET " +				
					"codigo = \"" + new_sala.getCodigo() + "\", " +
					"descricao = \"" + new_sala.getDescricao() + "\", " +
					"capacidade = \"" + new_sala.getCapacidade() + "\"" +
					" WHERE " +
					"sala.codigo = \"" + old_sala.getCodigo() + "\" and " +
					"sala.descricao = \"" + old_sala.getDescricao() +  "\" and " +
					"sala.capacidade = \"" + old_sala.getCapacidade() + "\";";
			con.setAutoCommit(false);
			pst = con.prepareStatement(msg);
			pst.executeUpdate();
			con.commit();
		}
		else {
			throw new PatrimonioException(SALA_JA_EXISTENTE);
		}
		
		pst.close();
		con.close();
	}

	public void excluir(Sala sala) throws SQLException, PatrimonioException {
		if(this.inDB(sala)){
			this.updateQuery("DELETE FROM sala WHERE " +
				"sala.codigo = \"" + sala.getCodigo() + "\" and " +
				"sala.descricao = \"" + sala.getDescricao() +  "\" and " +
				"sala.capacidade = \"" + sala.getCapacidade() + "\";"				
				);
		}
		else {
			throw new PatrimonioException(SALA_NAO_EXISTENTE);
		}
	}

	public Vector<Sala> buscarTodos() throws SQLException, PatrimonioException {
		Vector<Sala> vet = new Vector<Sala>();
		
		Connection con =  FactoryConnection.getInstance().getConnection();
		
		PreparedStatement pst = con.prepareStatement("SELECT * FROM sala;");
		ResultSet rs = pst.executeQuery();
		
		while(rs.next())
			vet.add(this.fetchSala(rs));
		
		pst.close();
		rs.close();
		con.close();
		return vet;
	}

	public boolean inDB(Sala sala) throws SQLException{
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM sala WHERE " +
				"sala.codigo = \"" + sala.getCodigo() + "\" and " +
				"sala.descricao = \"" + sala.getDescricao() + "\" and " +
				"sala.capacidade = \"" + sala.getCapacidade() +
				"\";");
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
	
	public Sala buscar() {
		//TODO
		return null;
	}

	private Sala fetchSala(ResultSet rs) throws PatrimonioException, SQLException{
		return new Sala(rs.getString("codigo"), rs.getString("descricao"), rs.getString("capacidade"));
	}
	
	private void updateQuery(String msg) throws SQLException{
		Connection con =  FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(msg);
		pst.executeUpdate();		
		pst.close();
		con.close();
	}

}
