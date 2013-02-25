package user_stories;

import java.awt.Dimension;
import java.sql.SQLException;

import model.Aluno;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.AlunoDAO;
import view.Main2;
import exception.ClienteException;

/**
 * US5 T�tulo: Excluir Aluno. Como aluno Eu quero solicitar a exclus�o do meu
 * cadastro Para n�o utilizar o servi�o.
 * 
 * Cen�rio 1: Aluno cadastrado. Dado que o aluno est� cadastrado; Quando o
 * usu�rio solicita a exclus�o do registro; Ent�o o sistema deve eliminar os
 * registros do aluno, E informar o sucesso da exclus�o.
 * 
 * Cen�rio 2: N�o existe aluno cadastrado. Dado que n�o existe o registro do
 * aluno, Quando o usu�rio solicita a exclus�o do registro, Ent�o o sistema n�o
 * exclui nenhum registro de aluno, E informa que n�o h� o registro.
 */

public class US05_ExcluirAluno {
	private FrameFixture window;
	private Robot robot;
	private Aluno aluno;
	private DialogFixture dialog;
	private int index;
	
	@Before
	public void setUp() throws ClienteException, SQLException {
		robot = BasicRobot.robotWithNewAwtHierarchy();
		robot.settings().delayBetweenEvents(5);

		window = new FrameFixture(robot, new Main2());
		window.show(new Dimension(900, 500)); // shows the frame to test

		aluno = new Aluno("Teste", "658.535.144-40", "110038096","9211-2144", "teste incluir repetido");
		AlunoDAO.getInstance().incluir(aluno);

		index = AlunoDAO.getInstance().buscarTodos().size() - 1;
		
		window.button("Aluno").click();
		dialog = window.dialog("AlunoView");

	}
	
	@After
	public void tearDown() throws SQLException, ClienteException {
		if(aluno != null)
			AlunoDAO.getInstance().excluir(aluno);
		window.cleanUp();
	}

	public void sleep(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testCenario1() throws SQLException, ClienteException{
		dialog.button("Excluir").click();
		dialog.optionPane().requireMessage("Selecione uma linha!");
		sleep();
		dialog.optionPane().okButton().click();
	}
	
	@Test
	public void testCenario2() throws SQLException, ClienteException{
		dialog.table("tabelaCliente").selectRows(index);
		dialog.button("Excluir").click();
		dialog.optionPane().requireMessage("Deseja mesmo excluir Aluno: " + aluno.getNome() + "?");
		sleep();
		dialog.optionPane().yesButton().click();
		sleep();
		dialog.optionPane().requireMessage("Aluno excluido com sucesso");
		dialog.optionPane().okButton().click();
		aluno = null;
	}
}
