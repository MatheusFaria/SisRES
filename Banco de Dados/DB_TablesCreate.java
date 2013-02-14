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
		
		
		uptodate("CREATE TABLE Aluno ( id_aluno INT NOT NULL AUTO_INCREMENT, " +
				"nome VARCHAR(100) NOT NULL, " +
				"cpf VARCHAR(14) NOT NULL, " +
				"telefone VARCHAR(15), " +
				"email VARCHAR(60), " +
				"matricula VARCHAR(15) NOT NULL, " +
				"PRIMARY KEY (id_aluno));");
		
		uptodate("CREATE TABLE Equipamento ( " +
				"id_equipamento INT NOT NULL AUTO_INCREMENT, " +
				"codigo VARCHAR(15) NOT NULL, " +
				"descricao VARCHAR(120), " +
				"PRIMARY KEY (id_equipamento));");


		uptodate("CREATE TABLE Professor (" +
				"id_professor INT NOT NULL AUTO_INCREMENT, " +
				"nome VARCHAR(100) NOT NULL, " +
				"cpf VARCHAR(14) NOT NULL, " +
				"telefone VARCHAR(15), " +
				"email VARCHAR(60), " +
				"matricula VARCHAR(15) NOT NULL, " +
				"PRIMARY KEY (id_professor));");


		uptodate("CREATE TABLE Reserva_Equipamento ( " +
				"id_reserva_equipamento INT NOT NULL AUTO_INCREMENT, " +
				"id_professor INT NOT NULL, " +
				"id_equipamento INT NOT NULL, " +
				" hora VARCHAR(5) NOT NULL, " +
				"data VARCHAR(10) NOT NULL, " +
				"PRIMARY KEY (id_reserva_equipamento));");


		uptodate("CREATE TABLE Sala ( " +
				"id_sala INT NOT NULL AUTO_INCREMENT, " +
				"codigo VARCHAR(10) NOT NULL, " +
				"descricao VARCHAR(120), " +
				"capacidade INT, PRIMARY KEY (id_sala) );");


		uptodate("CREATE TABLE Reserva_Sala_Aluno ( " +
				"id_reserva_sala_aluno INT NOT NULL AUTO_INCREMENT, " +
				"id_aluno INT NOT NULL, " +
				"id_sala INT NOT NULL, " +
				"finalidade VARCHAR(150) NOT NULL, " +
				"hora VARCHAR(5) NOT NULL, " +
				"data VARCHAR(10) NOT NULL, " +
				"cadeiras_reservadas INT NOT NULL, " +
				"PRIMARY KEY (id_reserva_sala_aluno));");


		uptodate("CREATE TABLE Reserva_Sala_Professor (" +
				"id_reserva_sala_professor INT NOT NULL AUTO_INCREMENT, " +
				"id_professor INT NOT NULL, " +
				"id_sala INT NOT NULL, " +
				"finalidade VARCHAR(150) NOT NULL, " +
				"hora VARCHAR(5) NOT NULL, " +
				"data VARCHAR(10) NOT NULL, " +
				"PRIMARY KEY (id_reserva_sala_professor));");


		uptodate("ALTER TABLE Reserva_Equipamento ADD CONSTRAINT FK_Reserva_Equipamento_0 FOREIGN KEY (id_professor) REFERENCES Professor (id_professor);");
		uptodate("ALTER TABLE Reserva_Equipamento ADD CONSTRAINT FK_Reserva_Equipamento_1 FOREIGN KEY (id_equipamento) REFERENCES Equipamento (id_equipamento);");


		uptodate("ALTER TABLE Reserva_Sala_Aluno ADD CONSTRAINT FK_Reserva_Sala_Aluno_0 FOREIGN KEY (id_aluno) REFERENCES Aluno (id_aluno);");
		uptodate("ALTER TABLE Reserva_Sala_Aluno ADD CONSTRAINT FK_Reserva_Sala_Aluno_1 FOREIGN KEY (id_sala) REFERENCES Sala (id_sala);");


		uptodate("ALTER TABLE Reserva_Sala_Professor ADD CONSTRAINT FK_Reserva_Sala_Professor_0 FOREIGN KEY (id_professor) REFERENCES Professor (id_professor);");
		uptodate("ALTER TABLE Reserva_Sala_Professor ADD CONSTRAINT FK_Reserva_Sala_Professor_1 FOREIGN KEY (id_sala) REFERENCES Sala (id_sala);");
		
		
	}

}