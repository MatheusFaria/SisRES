package view;


import java.sql.SQLException;
import java.util.Scanner;

import control.ManterSala;
import exception.PatrimonioException;
import exception.PatrimonioException;
import model.Sala;

public class MainTeste {
	
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args){
		
		Sala p = null;
		Sala p2 = null;
		try {
			p = new Sala("S3", "Sala de Estudos", "30");
			p2 = new Sala("S10", "Laboratorio", "40");
		} catch (PatrimonioException e) {
			e.printStackTrace();
		}
		
		
		
		//Cadastrar
		try {
			ManterSala.getInstance().inserir("S3", "Sala de Estudos", "30");
		} catch (PatrimonioException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		System.out.println("Ok! 1");
		scan.nextLine();//Parada para a conferencia direto no Banco
		//SELECT * FROM TABLE Sala;
		
		
		
		
		//Alterar
		try {
			ManterSala.getInstance().alterar("S10", "Laboratorio", "40", p);
		} catch (PatrimonioException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Ok! 2");
		scan.nextLine();//Parada para a conferencia direto no Banco
		//SELECT * FROM TABLE Sala;
		
		
		
		
		//Excluir
		try {
			try {
				ManterSala.getInstance().excluir(p2);
			} catch (PatrimonioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		//SELECT * FROM TABLE Sala;
		
		//Chegando aqui sem erros e as alterações ocorrendo no banco, as funções estão funcionado corretamente;
		System.out.println("Ok! 3");
	}

}
