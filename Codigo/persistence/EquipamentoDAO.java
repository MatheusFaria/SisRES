package persistence;

import model.Equipamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import exception.PatrimonioException;

public class EquipamentoDAO {

  	private static final String EQUIPAMENTO_JA_EXISTENTE = "Equipamento ja cadastrado.";
  	private static final String EQUIPAMENTO_NAO_EXISTENTE = "Equipamento nao cadastrado.";
	
	//Singleton
		private static EquipamentoDAO instance;
		private EquipamentoDAO(){
		}
		public static EquipamentoDAO getInstance(){
			if(instance == null)
				instance = new EquipamentoDAO();
			return instance;
		}
	//

	public void incluir(Equipamento equipamento) throws SQLException, PatrimonioException {	
		if(equipamento == null)
			throw new PatrimonioException("Equipamento invalido");
		
		if(!this.inDB(equipamento)){
			this.updateQuery("INSERT INTO " +
					"equipamento (codigo, descricao) VALUES (" +
					"\"" + equipamento.getCodigo() + "\", " +
					"\"" + equipamento.getDescricao()+ "\");"
					);
		}
		else {
			throw new PatrimonioException(EQUIPAMENTO_JA_EXISTENTE);
		}
	}


	public void alterar(Equipamento old_equipamento, Equipamento new_equipamento) throws SQLException, PatrimonioException {
		if(old_equipamento == null || new_equipamento == null)
			throw new PatrimonioException("Equipamento invalido");
		
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst;
		
		if(!this.inDB(old_equipamento))
			throw new PatrimonioException(EQUIPAMENTO_NAO_EXISTENTE);
		else if(!this.inDB(new_equipamento))
		{
			String msg = "UPDATE equipamento SET " +				
						"codigo = \"" + new_equipamento.getCodigo() + "\", " +
						"descricao = \"" + new_equipamento.getDescricao() + "\"" +
						" WHERE " +
						"equipamento.codigo = \"" + old_equipamento.getCodigo() + "\" and " +
						"equipamento.descricao = \"" + old_equipamento.getDescricao() +  "\";";
		
			con.setAutoCommit(false);
			pst = con.prepareStatement(msg);
			pst.executeUpdate();
			con.commit();
		}
		else {
			throw new PatrimonioException(EQUIPAMENTO_JA_EXISTENTE);
		}
		
		pst.close();
		con.close();
	}

	public void excluir(Equipamento equipamento) throws SQLException, PatrimonioException {
		if(equipamento == null)
			throw new PatrimonioException("Equipamento invalido");
		
		
		if(this.inDB(equipamento)){
			this.updateQuery("DELETE FROM equipamento WHERE " +
				"equipamento.codigo = \"" + equipamento.getCodigo() + "\" and " +
				"equipamento.descricao = \"" + equipamento.getDescricao() +  "\";"				
				);
		}
		else {
			throw new PatrimonioException(EQUIPAMENTO_NAO_EXISTENTE);
		}
	}

	public Vector<Equipamento> buscarTodos() throws SQLException, PatrimonioException {
		Vector<Equipamento> vet = new Vector<Equipamento>();
		
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
	
	public boolean inDB(Equipamento e) throws SQLException, PatrimonioException	{
		if(e == null)
			throw new PatrimonioException("Equipamento invalido");
		
		
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement("SELECT * FROM equipamento WHERE " +
				"equipamento.codigo = \"" + e.getCodigo() + "\" and " +
				"equipamento.descricao = \"" + e.getDescricao() + "\";");
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

 	public Equipamento buscar() {
		//TODO
		return null;
	}
	
	private Equipamento fetchEquipamento(ResultSet rs2) throws PatrimonioException, SQLException{
		rs2.next();
		return new Equipamento(rs2.getString("codigo"), rs2.getString("descricao"));
	}
	
	private void updateQuery(String msg) throws SQLException{
		Connection con =  FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(msg);
		pst.executeUpdate();		
		pst.close();
		con.close();
	}

}
