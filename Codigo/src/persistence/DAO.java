package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;

public abstract class DAO {
	//Esta classe nao sera testada diretamente.
	
	
	/**
	 * O vetor obtido deste metodo deve ser convertido pra o vetor
	 * do tipo que se vai utilizar, se necessario.
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Vector buscar(String query) throws SQLException, ClienteException, 
													PatrimonioException, ReservaException{
		Vector vet = new Vector();
		
		Connection con =  FactoryConnection.getInstance().getConnection();
		
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next())
			vet.add(this.fetch(rs));
		
		pst.close();
		rs.close();
		con.close();
		return vet;
	}
	
	/**
	 * Continua funcionando como antes, checa se o resgistro esta no banco.
	 * */
	protected boolean inDBGeneric(String query) throws SQLException{
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

	/**
	 * Funcao utilizada no buscar, por isso precisa ser implementada
	 * Ja foi implementada nas outras classes DAO. A implementacao eh
	 * semelhante.
	 * */
	protected abstract Object fetch(ResultSet rs) throws SQLException, ClienteException,
														PatrimonioException, ReservaException;
	
	
	/**
	 * Este metodo eh utilizado para Incluir e Excluir algum registro do
	 * banco, dependendo da query.
	 * */
	protected void executeQuery(String msg) throws SQLException{
		Connection con =  FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(msg);
		pst.executeUpdate();		
		pst.close();
		con.close();
	}
	
	/**
	 * Este metodo eh utilizado para Alterar alguma coisa no Banco
	 * */
	protected void updateQuery(String msg) throws SQLException{
		Connection con =  FactoryConnection.getInstance().getConnection();
		con.setAutoCommit(false);
		PreparedStatement pst = con.prepareStatement(msg);
		pst.executeUpdate();
		con.commit();
		pst.close();
		con.close();
	}
}
