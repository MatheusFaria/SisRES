package persistence;

import model.Patrimonio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import exception.PatrimonioException;

public class PatrimonioDAO {

  	private static final String PATRIMONIO_JA_EXISTENTE = "Patrimonio ja cadastrado.";
	
		//Singleton
		private static PatrimonioDAO instance;
		private PatrimonioDAO(){
		}
		public static PatrimonioDAO getInstance(){
			if(instance == null)
				instance = new PatrimonioDAO();
			return instance;
		}
		//

	public void incluir(Patrimonio equipamento) throws SQLException, PatrimonioException {
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM equipamento WHERE " +
				"equipamento.codigo = \"" + equipamento.getCodigo() + "\" and " +
				"equipamento.descricao = \"" + equipamento.getDescricao() + "\";");
		ResultSet rs = pst.executeQuery();
		
		if(!rs.next())
		{
			this.updateQuery("INSERT INTO " +
					"equipamento (codigo, descricao) VALUES (" +
					"\"" + equipamento.getCodigo() + "\", " +
					"\"" + equipamento.getDescricao()+ "\");"
				);
		}
		else {
			throw new PatrimonioException(PATRIMONIO_JA_EXISTENTE);
		}
				
		rs.close();
		pst.close();
		con.close();
	}


	public void alterar(Patrimonio old_equipamento, Patrimonio new_equipamento) throws SQLException {			
		String msg = 	"UPDATE equipamento SET " +				
						"codigo = \"" + new_equipamento.getCodigo() + "\", " +
						"descricao = \"" + new_equipamento.getDescricao() + "\"" +
						" WHERE " +
						"equipamento.codigo = \"" + old_equipamento.getCodigo() + "\" and " +
						"equipamento.descricao = \"" + old_equipamento.getDescricao() +  "\";";
		
		Connection con =  FactoryConnection.getInstance().getConnection();
		con.setAutoCommit(false);
		PreparedStatement pst = con.prepareStatement(msg);
		pst.executeUpdate();
		con.commit();
		pst.close();
		con.close();
	}

	public void excluir(Patrimonio equipamento) throws SQLException {
		this.updateQuery
		(	
			"DELETE FROM equipamento WHERE " +
			"equipamento.codigo = \"" + equipamento.getCodigo() + "\" and " +
			"equipamento.descricao = \"" + equipamento.getDescricao() +  "\";"				
		);		
	}

	public Patrimonio buscar() {
		//TODO
		return null;
	}

	public Vector<Patrimonio> buscarTodos() throws SQLException, PatrimonioException {
		Vector<Patrimonio> vet = new Vector<Patrimonio>();
		
		Connection con =  FactoryConnection.getInstance().getConnection();
		
		PreparedStatement pst = con.prepareStatement("SELECT * FROM equipamento;");
		ResultSet rs = pst.executeQuery();
		ResultSet rs2 = null;
		
		while(rs.next())
		{
			pst = con.prepareStatement("SELECT * FROM equipamento WHERE id_equipamento = " 
															+ rs.getString("id_equipamento"));
			rs2 = pst.executeQuery();
			vet.add(this.fetchEquipamento(rs2));
		}
		
		pst.close();
		rs.close();
        if(rs2 != null)
        	rs2.close();
		con.close();
		return vet;
	}
	
	private Patrimonio fetchEquipamento(ResultSet rs2) throws PatrimonioException, SQLException{
		rs2.next();
		return new Patrimonio(rs2.getString("codigo"), rs2.getString("descricao"));
	}
	
	private void updateQuery(String msg) throws SQLException{
		Connection con =  FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(msg);
		pst.executeUpdate();		
		pst.close();
		con.close();
	}

}
