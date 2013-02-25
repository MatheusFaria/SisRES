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
 * US3 Título: Cadastrar Aluno. Como aluno Eu quero me cadastrar Para poder
 * realizar reservas de salas.
 * 
 * Cenário 1: Não há cadastro e dados inseridos são válidos. Dado que não há
 * cadastro do aluno, E os dados inseridos todos são válidos, Quando o usuário
 * solicitar o cadastro do aluno, Então o sistema deve registrar o novo
 * cadastro, E informar o sucesso da operação.
 * 
 * Cenário 2: Há cadastro e dados inseridos são válidos. Dado que há o cadastro
 * do aluno, E os dados do novo cadastro são válidos, Quando o usuário solicitar
 * o cadastro do aluno, Então o sistema deve informar que o aluno já está
 * cadastrado, E não deve registrar um novo cadastro.
 * 
 * Cenário 3: Não há cadastro e dados inseridos são inválidos. Dado que não há o
 * cadastro do aluno, E os dados do novo cadastro são inválidos, Quando o
 * usuário solicitar o cadastro do aluno, Então o sistema deve exibir a seguinte
 * mensagem: “O campo [campo] é inválido”, E não deve registrar um novo
 * cadastro.
 * 
 */

public class US03_CadastrarAluno {

	private FrameFixture window;
	private Robot robot;
	private Aluno aluno;
	private DialogFixture dialog;
	private int index;
	
	@Before
	public void setUp() {
		robot = BasicRobot.robotWithNewAwtHierarchy();
		robot.settings().delayBetweenEvents(5);

		window = new FrameFixture(robot, new Main2());
		window.show(new Dimension(900, 500)); // shows the frame to test
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
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testCancelar() {
		dialog.button("Cadastrar").click();
		DialogFixture cadastro = dialog.dialog("CadastroAluno");
		cadastro.button("Cancelar").click();
	}
		
	@Test
	public void testCenario1() throws SQLException, ClienteException {
		dialog.button("Cadastrar").click();
		DialogFixture cadastro = dialog.dialog("CadastroAluno");

		cadastro.textBox("Nome").enterText("Teste");
		cadastro.textBox("Telefone").enterText("9211-2144");
		cadastro.textBox("CPF").enterText("658.535.144-40");
		cadastro.textBox("Matricula").enterText("110038096");
		cadastro.textBox("E-mail").enterText("Teste automatizado");

		cadastro.button("Cadastrar").click();
		cadastro.optionPane().requireMessage("Aluno Cadastrado com sucesso");
		sleep();
		cadastro.optionPane().okButton().click();

		index = AlunoDAO.getInstance().buscarTodos().size() - 1;
		aluno = AlunoDAO.getInstance().buscarTodos().get(index);
	}
	
	@Test
	public void testCenario2() throws SQLException, ClienteException {

		aluno = new Aluno("Teste", "658.535.144-40", "110038096","9211-2144", "teste incluir repetido");
		AlunoDAO.getInstance().incluir(aluno);
		
		dialog.button("Cadastrar").click();
		DialogFixture cadastro = dialog.dialog("CadastroAluno");

		cadastro.textBox("Nome").enterText("Teste");
		cadastro.textBox("Telefone").enterText("9211-2144");
		cadastro.textBox("CPF").enterText("658.535.144-40");
		cadastro.textBox("Matricula").enterText("110038096");
		cadastro.textBox("E-mail").enterText("Teste automatizado");

		cadastro.button("Cadastrar").click();
		cadastro.optionPane().requireMessage("Ja existe um aluno cadastrado com esse CPF.");
		sleep();
		cadastro.optionPane().okButton().click();
	}

	@Test
	public void testCenario3NomeInvalido() throws SQLException,
			ClienteException {

		dialog.button("Cadastrar").click();
		DialogFixture cadastro = dialog.dialog("CadastroAluno");

		cadastro.textBox("Nome").enterText("123");
		cadastro.textBox("Telefone").enterText("9211-2144");
		cadastro.textBox("CPF").enterText("658.535.144-40");
		cadastro.textBox("Matricula").enterText("110038096");
		cadastro.textBox("E-mail").enterText("Teste automatizado");

		cadastro.button("Cadastrar").click();
		cadastro.optionPane().requireMessage("Nome Invalido.");
		sleep();
		cadastro.optionPane().okButton().click();

	}

	@Test
	public void testCenario3NomeBranco() throws SQLException,
			ClienteException {

		dialog.button("Cadastrar").click();
		DialogFixture cadastro = dialog.dialog("CadastroAluno");

		cadastro.textBox("Nome").enterText("");
		cadastro.textBox("Telefone").enterText("9211-2144");
		cadastro.textBox("CPF").enterText("658.535.144-40");
		cadastro.textBox("Matricula").enterText("110038096");
		cadastro.textBox("E-mail").enterText("Teste automatizado");

		cadastro.button("Cadastrar").click();
		cadastro.optionPane().requireMessage("Nome em Branco.");
		sleep();
		cadastro.optionPane().okButton().click();
	}

	@Test
	public void testCenario3CpfInvalido() throws SQLException,
			ClienteException {
		
		dialog.button("Cadastrar").click();
		DialogFixture cadastro = dialog.dialog("CadastroAluno");

		cadastro.textBox("Nome").enterText("Teste");
		cadastro.textBox("Telefone").enterText("9211-2144");
		cadastro.textBox("CPF").enterText("03435543132");
		cadastro.textBox("Matricula").enterText("110038096");
		cadastro.textBox("E-mail").enterText("Teste automatizado");

		cadastro.button("Cadastrar").click();
		cadastro.optionPane().requireMessage("CPF Invalido.");
		sleep();
		cadastro.optionPane().okButton().click();
	}
	
	@Test
	public void testCenario3CpfBranco() throws SQLException,
			ClienteException {
		
		dialog.button("Cadastrar").click();
		DialogFixture cadastro = dialog.dialog("CadastroAluno");

		cadastro.textBox("Nome").enterText("Teste");
		cadastro.textBox("Telefone").enterText("9211-2144");
		cadastro.textBox("CPF").enterText("");
		cadastro.textBox("Matricula").enterText("110038096");
		cadastro.textBox("E-mail").enterText("Teste automatizado");

		cadastro.button("Cadastrar").click();
		cadastro.optionPane().requireMessage("CPF em Branco.");
		sleep();
		cadastro.optionPane().okButton().click();
	}
	
	@Test
	public void testCenario3TelefoneInvalido() throws SQLException,
			ClienteException {
		
		dialog.button("Cadastrar").click();
		DialogFixture cadastro = dialog.dialog("CadastroAluno");

		cadastro.textBox("Nome").enterText("Teste");
		cadastro.textBox("Telefone").enterText("123");
		cadastro.textBox("CPF").enterText("658.535.144-40");
		cadastro.textBox("Matricula").enterText("110038096");
		cadastro.textBox("E-mail").enterText("Teste automatizado");

		cadastro.button("Cadastrar").click();
		cadastro.optionPane().requireMessage("Telefone Invalido.");
		sleep();
		cadastro.optionPane().okButton().click();
	}
	
}