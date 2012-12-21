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
		PreparedStatement pst = con.prepareStatement("SELECT * FROM patrimonio WHERE " +
				"patrimonio.codigo = \"" + sala.getCodigo() + "\" and " +
				"patrimonio.descricao = \"" + sala.getDescricao() + "\";");
		ResultSet rs = pst.executeQuery();//armazena os dados de execu��o do comando sql acima
		
		if(!rs.next())//se n�o houver, insere um 'patrimonio'
		{
			this.updateQuery("INSERT INTO " +
					"patrimonio (codigo, descricao) VALUES (" +
					"\"" + sala.getCodigo() + "\", " +
					"\"" + sala.getDescricao() + "\"); "
				);
		}
		else {
			pst = con.prepareStatement("SELECT * FROM sala WHERE " +
				"id_patrimonio = " + rs.getString("id_patrimonio"));
			rs = pst.executeQuery();
		}
		
		if(!rs.next())
		{
			this.updateQuery("INSERT INTO sala (capacidade, id_patrimonio) VALUES (" +
				"\"" + sala.getCapacidade() + "\", (" +
				"SELECT id_patrimonio FROM patrimonio WHERE " +
				"patrimonio.codigo = \"" + sala.getCodigo() + "\" and " +
				"patrimonio.descricao = \"" + sala.getDescricao() +  "\");"
			);
/**INSERT INTO sala (capacidade, id_patrimonio) VALUES ("30",
 *  (SELECT id_patrimonio FROM patrimonio 
 *  WHERE patrimonio.codigo = "123" and 
 *  patrimonio.descricao = "sem descricao"));*/
		}
		else{
			throw new PatrimonioException(SALA_JA_EXISTENTE);
		}
		
		rs.close();
		pst.close();
		con.close();
	}


	public void alterar(Sala old_sala, Sala new_sala) throws SQLException {
		this.updateQuery("UPDATE sala SET " +
				"sala.capacidade = \"" + new_sala.getCapacidade() + "\"" +
				" WHERE " +
				"sala.id_patrimonio = \"(" +
				"SELECT id_patrimonio FROM patrimonio WHERE " +
				"patrimonio.codigo = \"" + old_sala.getCodigo() + "\" and " +
				"patrimonio.descricao = \"" + old_sala.getDescricao() +  "\") and" +
				"sala.capacidade = \" " + old_sala.getCapacidade() + "\";" +
				
				"UPDATE patrimonio SET" +				
				"patrimonio.codigo = \"" + new_sala.getCodigo() + "\", " +
				"patrimonio.descricao = \"" + new_sala.getDescricao() + "\", " +
				" WHERE " +
				"patrimonio.codigo = \"" + old_sala.getCodigo() + "\" and " +
				"patrimonio.descricao = \"" + old_sala.getDescricao() +  "\");" 
				);
	}

	public void excluir(Sala sala) throws SQLException {
		this.updateQuery("DELETE FROM sala WHERE " +
				"sala.id_patrimonio = \"(" +
				"SELECT id_patrimonio FROM patrimonio WHERE " +
				"patrimonio.codigo = \"" + sala.getCodigo() + "\" and " +
				"patrimonio.descricao = \"" + sala.getDescricao() +  "\") and" +
				"sala.capacidade = \" " + sala.getCapacidade() + "\";"				
				);
		this.updateQuery("DELETE FROM patrimonio WHERE " +
				"patrimonio.codigo = \"" + sala.getCodigo() + "\" and " +
				"patrimonio.descricao = \"" + sala.getDescricao() +  "\");" 
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
			pst = con.prepareStatement("SELECT * FROM patrimonio WHERE id_patrimonio = " 
															+ rs.getString("id_patrimonio"));
			rs2 = pst.executeQuery();
			
			vet.add(this.fetchSala(rs2, rs));
		}
		
		pst.close();
		rs.close();
                if(rs2 != null)
                    rs2.close();
		con.close();
		return vet;
	}
	
	private Sala fetchSala(ResultSet rs2, ResultSet rs) throws PatrimonioException, SQLException{
		rs.next();
		return new Sala(rs2.getString("codigo"), rs2.getString("descricao"), rs.getString("capacidade"));
	}
	
	private void updateQuery(String msg) throws SQLException{
		Connection con =  FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(msg);
		pst.executeUpdate();		
		pst.close();
		con.close();
	}

}
