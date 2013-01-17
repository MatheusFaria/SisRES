package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB_TablesCreate {

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
		
		
		uptodate("CREATE TABLE IF NOT EXISTS Aluno (" +
					"id_aluno INT NOT NULL AUTO_INCREMENT," +
					"nome VARCHAR(100) NOT NULL," +
					"cpf VARCHAR(11) NOT NULL," +
					"telefone VARCHAR(10)," +
					"email VARCHAR(60)," +
					"matricula VARCHAR(15) NOT NULL," +
					"PRIMARY KEY (id_aluno));");
		
		uptodate("CREATE TABLE IF NOT EXISTS Equipamento (" +
					"id_equipamento INT NOT NULL AUTO_INCREMENT," +
					"codigo VARCHAR(15) NOT NULL," +
					"descricao VARCHAR(120)," +
					"PRIMARY KEY (id_equipamento));");
		
		uptodate("CREATE TABLE IF NOT EXISTS Professor (" +
				 "id_professor INT NOT NULL AUTO_INCREMENT," +
				 "nome VARCHAR(10) NOT NULL," +
				 "cpf VARCHAR(11) NOT NULL," +
				 "telefone VARCHAR(10)," +
				 "email VARCHAR(60)," +
				 "matricula VARCHAR(15) NOT NULL," +
				 "PRIMARY KEY (id_professor))");
		
		uptodate("CREATE TABLE IF NOT EXISTS Sala (" +
				 "id_sala INT NOT NULL AUTO_INCREMENT," +
				 "codigo VARCHAR(10) NOT NULL," +
				 "descricao VARCHAR(120)," +
				 "capacidade INT NOT NULL," +
				"PRIMARY KEY (id_sala));");
		
		uptodate("ALTER TABLE Aluno ADD CONSTRAINT PK_Aluno PRIMARY KEY (id_aluno);");
		uptodate("ALTER TABLE Equipamento ADD CONSTRAINT PK_Equipamento PRIMARY KEY (id_equipamento);");
		uptodate("ALTER TABLE Professor ADD CONSTRAINT PK_Professor PRIMARY KEY (id_professor);");
		uptodate("ALTER TABLE Sala ADD CONSTRAINT PK_Sala PRIMARY KEY (id_sala);");
	}

}