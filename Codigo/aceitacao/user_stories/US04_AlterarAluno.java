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
 * US4 Título: Alterar Aluno. Como aluno Eu quero alterar meus dados Para
 * que meu cadastro esteja sempre atualizado.
 * 
 * Cenário 1: Não há cadastro do aluno. Dado que o aluno não está
 * cadastrado, Quando o usuário solicita a alteração do cadastro, Então o
 * sistema informa que o cadastro não existe.
 * 
 * Cenário 2: Há cadastro e o novos dados todos são válidos. Dado que há o
 * cadastro do aluno, E todos os novos dados inseridos são válidos, Quando o
 * usuário solicita alteração do cadastro do aluno, Então o sistema altera
 * os dados, E informa que os dados foram alterados.
 * 
 * Cenário 3: Há cadastro e algum novo(s) dado(s) é (são) inválidos. Dado
 * que há o cadastro do aluno, E algum novo dado inserido é inválido, Quando
 * o usuário solicita alteração do cadastro do aluno, Então o sistema deve
 * exibir a seguinte mensagem: “O campo [campo] é inválido”, E o sistema não
 * altera os dados.
 */

public class US04_AlterarAluno {
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
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testCancelar() {
		dialog.table("tabelaCliente").selectRows(index);
		dialog.button("Alterar").click();
		DialogFixture cadastro = dialog.dialog("AlterarAluno");
		cadastro.button("Cancelar").click();
	}
	
	@Test
	public void testCenario1() throws SQLException, ClienteException{
		if(aluno != null)
			AlunoDAO.getInstance().excluir(aluno);
		dialog.button("Alterar").click();
		dialog.optionPane().requireMessage("Selecione uma linha!");
		aluno = null;
	}
	
	@Test
	public void testCenario2() throws SQLException, ClienteException{
		
		dialog.table("tabelaCliente").selectRows(index);
		dialog.button("Alterar").click();
		DialogFixture cadastro = dialog.dialog("AlterarAluno");
		
		cadastro.textBox("Nome").setText("Novo Teste");
		cadastro.textBox("E-mail").setText("Alteracao Teste automatizado");

		cadastro.button("Alterar").click();
		cadastro.optionPane().requireMessage("Aluno alterado com sucesso");
		sleep();
		cadastro.optionPane().okButton().click();

		aluno = AlunoDAO.getInstance().buscarTodos().get(index);
		sleep();
	}
	
	@Test
	public void testCenario3NomeInvalido() throws SQLException, ClienteException{
		
		dialog.table("tabelaCliente").selectRows(index);
		dialog.button("Alterar").click();
		DialogFixture cadastro = dialog.dialog("AlterarAluno");
		
		cadastro.textBox("Nome").setText("123");
		cadastro.textBox("E-mail").setText("Alteracao Teste automatizado");

		cadastro.button("Alterar").click();
		cadastro.optionPane().requireMessage("Nome Invalido.");
		sleep();
		cadastro.optionPane().okButton().click();

		aluno = AlunoDAO.getInstance().buscarTodos().get(index);
		sleep();
	}
	
	@Test
	public void testCenario3NomeEmBranco() throws SQLException, ClienteException{
		
		dialog.table("tabelaCliente").selectRows(index);
		dialog.button("Alterar").click();
		DialogFixture cadastro = dialog.dialog("AlterarAluno");
		
		cadastro.textBox("Nome").setText("");
		cadastro.textBox("E-mail").setText("Alteracao Teste automatizado");

		cadastro.button("Alterar").click();
		cadastro.optionPane().requireMessage("Nome em Branco.");
		sleep();
		cadastro.optionPane().okButton().click();

		aluno = AlunoDAO.getInstance().buscarTodos().get(index);
		sleep();
	}
	
	@Test
	public void testCenario3CpfInvalido() throws SQLException, ClienteException{
		
		dialog.table("tabelaCliente").selectRows(index);
		dialog.button("Alterar").click();
		DialogFixture cadastro = dialog.dialog("AlterarAluno");
		
		cadastro.textBox("CPF").setText("123");
		cadastro.textBox("E-mail").setText("Alteracao Teste automatizado");

		cadastro.button("Alterar").click();
		cadastro.optionPane().requireMessage("CPF Invalido.");
		sleep();
		cadastro.optionPane().okButton().click();

		aluno = AlunoDAO.getInstance().buscarTodos().get(index);
		sleep();
	}
	
	@Test
	public void testCenario3CpfEmBranco() throws SQLException, ClienteException{
		
		dialog.table("tabelaCliente").selectRows(index);
		dialog.button("Alterar").click();
		DialogFixture cadastro = dialog.dialog("AlterarAluno");
		
		cadastro.textBox("CPF").setText("");
		cadastro.textBox("E-mail").setText("Alteracao Teste automatizado");

		cadastro.button("Alterar").click();
		cadastro.optionPane().requireMessage("CPF em Branco.");
		sleep();
		cadastro.optionPane().okButton().click();

		aluno = AlunoDAO.getInstance().buscarTodos().get(index);
		sleep();
	}
	
	@Test
	public void testCenario3TelefeoneInvalido() throws SQLException, ClienteException{
		
		dialog.table("tabelaCliente").selectRows(index);
		dialog.button("Alterar").click();
		DialogFixture cadastro = dialog.dialog("AlterarAluno");
		
		cadastro.textBox("Telefone").setText("123");
		cadastro.textBox("E-mail").setText("Alteracao Teste automatizado");

		cadastro.button("Alterar").click();
		cadastro.optionPane().requireMessage("Telefone Invalido.");
		sleep();
		cadastro.optionPane().okButton().click();

		aluno = AlunoDAO.getInstance().buscarTodos().get(index);
		sleep();
	}
}
