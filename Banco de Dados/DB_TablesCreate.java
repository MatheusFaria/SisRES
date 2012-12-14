import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JanelaPrincipal {

	public static void uptodate(String msg)
	{
		String local = "jdbc:mysql://localhost/sisres_db";
		String user = "testuser";
		String password = "password";
		
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = null;
			con = DriverManager.getConnection(local, user, password);
			pst = con.prepareStatement(msg);
			pst.executeUpdate();
			pst.close();
			con.close();
			System.out.println("Ok!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
	}
	
	public static void main(String[] args) {
		
		
		uptodate("CREATE TABLE IF NOT EXISTS Cliente (" +
				"id_cliente INT NOT NULL AUTO_INCREMENT," +
				"nome VARCHAR(100) NOT NULL," +
				"cpf VARCHAR(14) NOT NULL," +
				"telefone VARCHAR(17)," +
				"email VARCHAR(50)," +
				"matricula VARCHAR(15) NOT NULL," +
				"PRIMARY KEY (id_cliente)" + ")");
		uptodate("CREATE TABLE IF NOT EXISTS Patrimonio ("+
				 "id_patrimonio INT NOT NULL AUTO_INCREMENT,"+
				 "codigo VARCHAR(20) NOT NULL,"+
				 "descricao VARCHAR(255) NOT NULL,"+
				 "PRIMARY KEY (id_patrimonio)"+
				")");
		uptodate("\nCREATE TABLE IF NOT EXISTS Professor ("+
				 "id_professor INT NOT NULL AUTO_INCREMENT,"+
				 "id_cliente INT NOT NULL,"+
				 "PRIMARY KEY (id_professor,id_cliente)"+
				")");
		uptodate("CREATE TABLE IF NOT EXISTS Sala ("+
				 "id_sala INT NOT NULL AUTO_INCREMENT,"+
				 "id_patrimonio INT NOT NULL,"+
				 "capacidade INT NOT NULL,"+
				 "PRIMARY KEY (id_sala,id_patrimonio)"+
				")");
		uptodate("ALTER TABLE Professor ADD CONSTRAINT FK_Professor_0 FOREIGN KEY (id_cliente) REFERENCES Cliente (id_cliente)");
		uptodate("ALTER TABLE Sala ADD CONSTRAINT FK_Sala_0 FOREIGN KEY (id_patrimonio) REFERENCES Patrimonio (id_patrimonio);");
	}

}