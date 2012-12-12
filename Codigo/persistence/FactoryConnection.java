package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//TODO conexao com o MySQL
public class FactoryConnection {
		static String statusConnection = "";
		
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/teste?user=matheus&password=1234");
			System.out.println("OK!");	
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return con;
	}

}
