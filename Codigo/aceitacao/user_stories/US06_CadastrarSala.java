package user_stories;

import java.awt.Dimension;
import java.sql.SQLException;

import model.Sala;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.SalaDAO;
import view.Main2;
import exception.PatrimonioException;

/**
 * US6 Título: Cadastrar sala. Como usuário Eu quero cadastrar salas Para que
 * haja possibilidade de reserva quando disponível.
 * 
 * Cenário 1: Não há cadastro e dados inseridos são válidos. Dado que não há
 * cadastro da sala, E os dados inseridos todos são válidos, Quando o usuário
 * solicitar o cadastro da sala, Então o sistema deve registrar o novo cadastro,
 * E informar o sucesso da operação.
 * 
 * Cenário 2: Há cadastro e dados inseridos são válidos. Dado que há o cadastro
 * da sala, E os dados do novo cadastro são válidos, Quando o usuário solicitar
 * o cadastro da sala, Então o sistema deve informar que a sala já está
 * cadastrada, E não deve registrar um novo cadastro.
 * 
 * Cenário 3: Não há cadastro e dados inseridos são inválidos. Dado que não há o
 * cadastro da sala, E os dados do novo cadastro são inválidos, Quando o usuário
 * solicitar o cadastro da sala, Então o sistema deve exibir a seguinte
 * mensagem: “O campo [campo] é inválido”, E não deve registrar um novo
 * cadastro.
 */

public class US06_CadastrarSala {

    private FrameFixture window;
    private Robot robot;
    private Sala sala;
    private DialogFixture dialog;
    private int index;

    @Before public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();
        robot.settings().delayBetweenEvents(5);

        window = new FrameFixture(robot, new Main2());
        window.show(new Dimension(900, 500)); // shows the frame to test
        window.button("Sala").click();
        dialog = window.dialog("SalaView");
    }

    @After public void tearDown() throws SQLException, PatrimonioException {
        if (sala != null)
            SalaDAO.getInstance().excluir(sala);
        window.cleanUp();
    }

    public void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test public void testCancelar() {
        dialog.button("Cadastrar").click();
        DialogFixture cadastro = dialog.dialog("CadastroSala");
        cadastro.button("Cancelar").click();
    }

    @Test public void testCenario1() throws SQLException, PatrimonioException {
        dialog.button("Cadastrar").click();
        DialogFixture cadastro = dialog.dialog("CadastroSala");

        cadastro.textBox("Capacidade").enterText("123");
        cadastro.textBox("Codigo").enterText("code");
        cadastro.textBox("Descricao").enterText("Sala para testes de aceitacao");

        cadastro.button("Cadastrar").click();
        cadastro.optionPane().requireMessage("Sala Cadastrada com sucesso");
        sleep();
        cadastro.optionPane().okButton().click();

        index = SalaDAO.getInstance().buscarTodos().size() - 1;
        sala = SalaDAO.getInstance().buscarTodos().get(index);
    }

    @Test public void testCenario2() throws SQLException, PatrimonioException {

        sala = new Sala("code","Sala para testes de aceitacao","123");
        SalaDAO.getInstance().incluir(sala);

        dialog.button("Cadastrar").click();
        DialogFixture cadastro = dialog.dialog("CadastroSala");

        cadastro.textBox("Capacidade").enterText("123");
        cadastro.textBox("Codigo").enterText("code");
        cadastro.textBox("Descricao").enterText("Sala para testes de aceitacao");

        cadastro.button("Cadastrar").click();
        cadastro.optionPane().requireMessage("Sala com o mesmo codigo ja cadastrada.");
        sleep();
        cadastro.optionPane().okButton().click();
    }

    @Test public void testCenario3CapacidadeInvalida() throws SQLException, PatrimonioException {

        dialog.button("Cadastrar").click();
        DialogFixture cadastro = dialog.dialog("CadastroSala");
        
        cadastro.textBox("Capacidade").enterText("abc");
        cadastro.textBox("Codigo").enterText("code");
        cadastro.textBox("Descricao").enterText("Sala para testes de aceitacao");

        cadastro.button("Cadastrar").click();
        cadastro.optionPane().requireMessage("Capacidade Invalida.");
        sleep();
        cadastro.optionPane().okButton().click();

    }
    
    @Test public void testCenario3CapacidadeBranco() throws SQLException, PatrimonioException {

        dialog.button("Cadastrar").click();
        DialogFixture cadastro = dialog.dialog("CadastroSala");

        cadastro.textBox("Capacidade").enterText("");
        cadastro.textBox("Codigo").enterText("code");
        cadastro.textBox("Descricao").enterText("Sala para testes de aceitacao");

        cadastro.button("Cadastrar").click();
        cadastro.optionPane().requireMessage("Capacidade em Branco.");
        sleep();
        cadastro.optionPane().okButton().click();
    }


    @Test public void testCenario3CodigoBranco() throws SQLException, PatrimonioException {

        dialog.button("Cadastrar").click();
        DialogFixture cadastro = dialog.dialog("CadastroSala");

        cadastro.textBox("Capacidade").enterText("123");
        cadastro.textBox("Codigo").enterText("");
        cadastro.textBox("Descricao").enterText("Sala para testes de aceitacao");

        cadastro.button("Cadastrar").click();
        cadastro.optionPane().requireMessage("Codigo em Branco.");
        sleep();
        cadastro.optionPane().okButton().click();
    }

    @Test public void testCenario3DescricaoBranco() throws SQLException, PatrimonioException {

        dialog.button("Cadastrar").click();
        DialogFixture cadastro = dialog.dialog("CadastroSala");

        cadastro.textBox("Capacidade").enterText("123");
        cadastro.textBox("Codigo").enterText("code");
        cadastro.textBox("Descricao").enterText("");

        cadastro.button("Cadastrar").click();
        cadastro.optionPane().requireMessage("Descricao em Branco.");
        sleep();
        cadastro.optionPane().okButton().click();
    }
    
}
