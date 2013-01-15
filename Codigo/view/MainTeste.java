package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import persistence.FactoryConnection;
import persistence.SalaDAO;

import control.ManterProfessor;
import control.ManterSala;
import exception.ClienteException;
import exception.PatrimonioException;
import model.Professor;
import model.Sala;

public class MainTeste {
	
	public static void main(String[] args) throws ClienteException, SQLException, PatrimonioException {
		
		Sala s = new Sala("121212", "SalaG", "23");
		
		ManterSala.getInstance().alterar("121212", "SalaG", "54", s);
		

		System.out.println("Ok!");
	}

}
