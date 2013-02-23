package user_stories;

import java.awt.Dimension;
import java.sql.SQLException;

import model.Equipamento;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.EquipamentoDAO;
import view.Main2;
import exception.PatrimonioException;

/**US9
Título: Cadastrar equipamento.
Como usuário
Eu quero cadastrar equipamentos
Para que haja possibilidade de reserva quando disponível.

Cenário 1: Não há cadastro e dados inseridos são válidos.
Dado que não há cadastro do equipamento,
E os dados inseridos todos são válidos,
Quando o usuário solicitar o cadastro do equipamento,
Então o sistema deve registrar o novo cadastro,
E informar o sucesso da operação.

Cenário 2: Há cadastro e dados inseridos são válidos.
Dado que há o cadastro do equipamento,
E os dados do novo cadastro são válidos,
Quando o usuário solicitar o cadastro do equipamento,
Então o sistema deve informar que o equipamento já está cadastrado,
E não deve registrar um novo cadastro.

Cenário 3: Não há cadastro e dados inseridos são inválidos.
Dado que não há o cadastro do equipamento,
E os dados do novo cadastro são inválidos,
Quando o usuário solicitar o cadastro do equipamento,
Então o sistema deve exibir a seguinte mensagem: “O campo [campo] é inválido”,
E não deve registrar um novo cadastro.

*/
public class US09_CadastrarEquipamento {
    private FrameFixture window;
    private Robot robot;
    private Equipamento equipamento;
    private DialogFixture dialog;
    private int index;

    @Before public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();
        robot.settings().delayBetweenEvents(5);

        window = new FrameFixture(robot, new Main2());
        window.show(new Dimension(900, 500)); // shows the frame to test
        window.button("Equipamento").click();
        dialog = window.dialog("EquipamentoView");
    }

    @After public void tearDown() throws SQLException, PatrimonioException {
        if (equipamento != null)
            EquipamentoDAO.getInstance().excluir(equipamento);
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
        DialogFixture cadastro = dialog.dialog("CadastroEquipamento");
        cadastro.button("Cancelar").click();
    }

    @Test public void testCenario1() throws SQLException, PatrimonioException {
        dialog.button("Cadastrar").click();
        DialogFixture cadastro = dialog.dialog("CadastroEquipamento");

        cadastro.textBox("Codigo").enterText("code");
        cadastro.textBox("Descricao").enterText("Equipamento para testes de aceitacao");

        cadastro.button("Cadastrar").click();
        cadastro.optionPane().requireMessage("Equipamento Cadastrado com sucesso");
        sleep();
        cadastro.optionPane().okButton().click();

        index = EquipamentoDAO.getInstance().buscarTodos().size() - 1;
        equipamento = EquipamentoDAO.getInstance().buscarTodos().get(index);
    }

    @Test public void testCenario2() throws SQLException, PatrimonioException {

        equipamento = new Equipamento("code","Equipamento para testes de aceitacao");
        EquipamentoDAO.getInstance().incluir(equipamento);

        dialog.button("Cadastrar").click();
        DialogFixture cadastro = dialog.dialog("CadastroEquipamento");

        cadastro.textBox("Codigo").enterText("code");
        cadastro.textBox("Descricao").enterText("Equipamento para testes de aceitacao");

        cadastro.button("Cadastrar").click();
        cadastro.optionPane().requireMessage("Equipamento com o mesmo codigo ja cadastrado.");
        sleep();
        cadastro.optionPane().okButton().click();
    }

    
    @Test public void testCenario3CodigoBranco() throws SQLException, PatrimonioException {

        dialog.button("Cadastrar").click();
        DialogFixture cadastro = dialog.dialog("CadastroEquipamento");

        cadastro.textBox("Codigo").enterText("");
        cadastro.textBox("Descricao").enterText("Equipamento para testes de aceitacao");

        cadastro.button("Cadastrar").click();
        cadastro.optionPane().requireMessage("Codigo em Branco.");
        sleep();
        cadastro.optionPane().okButton().click();
    }

    @Test public void testCenario3DescricaoBranco() throws SQLException, PatrimonioException {

        dialog.button("Cadastrar").click();
        DialogFixture cadastro = dialog.dialog("CadastroEquipamento");

        cadastro.textBox("Codigo").enterText("code");
        cadastro.textBox("Descricao").enterText("");

        cadastro.button("Cadastrar").click();
        cadastro.optionPane().requireMessage("Descricao em Branco.");
        sleep();
        cadastro.optionPane().okButton().click();
    }
}
