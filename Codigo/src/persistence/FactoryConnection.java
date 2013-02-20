package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class FactoryConnection {
	static String statusConnection = "";
	
	private String local = "jdbc:mysql://localhost/sisres_db";
	private String user = "testuser";
	private String password = "password";
	
	//Singleton
		private static FactoryConnection instance;
		private FactoryConnection(){
		}
		public static FactoryConnection getInstance(){
			if(instance == null)
				instance = new FactoryConnection();
			return instance;
		}
	//
		
		
	public Connection getConnection() throws SQLException{
		Connection con = null;
		con = DriverManager.getConnection(local, user, password);
		return con;
	}

}
