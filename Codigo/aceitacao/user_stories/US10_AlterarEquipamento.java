package user_stories;

import java.awt.Dimension;
import java.sql.SQLException;

import model.Equipamento;
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

/**US10
Título: Alterar cadastro de equipamento
Como usuário
Eu gostaria de alterar dados de equipamentos
Para que haja confiabilidade nos dados contidos no sistema.

Cenário 1: Existe equipamento cadastrado e dados novos são válidos.
Dado que o equipamento está cadastrado;
Quando o usuário edita algum campo
E todos os dados são válidos,
E solicita alteração;
Então o sistema deve alterar os registros do equipamento.
E informar o sucesso da alteração.

Cenário 2: Não existe equipamento cadastrado.
Dado que não existe o registro do equipamento;
Quando o usuário solicita alteração;
Então o sistema informa que não há o registro.

Cenário 3: Existe equipamento cadastrado e dados novos não são válidos.
Dado que o equipamento está cadastrado;
Quando o usuário edita algum campo
E algum dado não é válido,
E solicita alteração;
Então o sistema deve exibir a seguinte mensagem: “O campo [campo] é inválido”,
E não realizar alteração.
*/

public class US10_AlterarEquipamento {
    private FrameFixture window;
    private Robot robot;
    private Equipamento equipamento;
    private DialogFixture dialog;
    private int index;

    @Before public void setUp() throws PatrimonioException, SQLException {
        
    robot = BasicRobot.robotWithNewAwtHierarchy();
    robot.settings().delayBetweenEvents(5);

    window = new FrameFixture(robot, new Main2());
    window.show(new Dimension(900, 500)); // shows the frame to test

    equipamento = new Equipamento("code", "Equipamento para testes de aceitacao");
    EquipamentoDAO.getInstance().incluir(equipamento);

    index = EquipamentoDAO.getInstance().buscarTodos().size() - 1;

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
    dialog.table("tabelaPatrimonio").selectRows(index);
    dialog.button("Alterar").click();
    DialogFixture cadastro = dialog.dialog("AlterarEquipamento");
    cadastro.button("Cancelar").click();
}

@Test public void testCenario1() throws SQLException, PatrimonioException {

    dialog.table("tabelaPatrimonio").selectRows(index);

    dialog.button("Alterar").click();
    DialogFixture cadastro = dialog.dialog("AlterarEquipamento");

    cadastro.textBox("Codigo").setText("123");

    cadastro.button("Alterar").click();
    cadastro.optionPane().requireMessage("Equipamento alterado com sucesso");
    sleep();
    cadastro.optionPane().okButton().click();

    equipamento = EquipamentoDAO.getInstance().buscarTodos().get(index);
}

@Test public void testCenario2() throws SQLException, PatrimonioException {

    if (equipamento != null)
        EquipamentoDAO.getInstance().excluir(equipamento);
    equipamento = null;
    dialog.button("Alterar").click();
    dialog.optionPane().requireMessage("Selecione uma linha!");
    sleep();

}

@Test public void testCenario3CodigoBranco() throws SQLException, PatrimonioException {

    dialog.table("tabelaPatrimonio").selectRows(index);
    dialog.button("Alterar").click();
    DialogFixture cadastro = dialog.dialog("AlterarEquipamento");

    
    cadastro.textBox("Codigo").setText("");
    cadastro.textBox("Descricao").setText("Equipamento para testes de aceitacao");

    cadastro.button("Alterar").click();
    cadastro.optionPane().requireMessage("Codigo em Branco.");
    sleep();
    cadastro.optionPane().okButton().click();
    equipamento = EquipamentoDAO.getInstance().buscarTodos().get(index);
}

@Test public void testCenario3DescricaoBranco() throws SQLException, PatrimonioException {

    dialog.table("tabelaPatrimonio").selectRows(index);
    dialog.button("Alterar").click();
    DialogFixture cadastro = dialog.dialog("AlterarEquipamento");

    
    cadastro.textBox("Codigo").setText("code");
    cadastro.textBox("Descricao").setText("");

    cadastro.button("Alterar").click();
    cadastro.optionPane().requireMessage("Descricao em Branco.");
    sleep();
    cadastro.optionPane().okButton().click();
    equipamento = EquipamentoDAO.getInstance().buscarTodos().get(index);
}

}
