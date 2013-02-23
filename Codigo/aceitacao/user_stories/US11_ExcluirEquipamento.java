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
import exception.ClienteException;
import exception.PatrimonioException;

/**
 * US11
Título: Excluir equipamento.
Como um usuário,
Eu gostaria de excluir o equipamento no sistema,
Para que o equipamento seja indisponibilizado para reserva.

Cenário 1: Existe equipamento cadastrado.
Dado que o equipamento está cadastrado;
Quando o usuário solicita a exclusão;
Então o sistema deve eliminar os registros do equipamento.
E informar o sucesso da exclusão.

Cenário 2: Não existe equipamento cadastrado.
Dado que não existe o registro do equipamento;
Quando o usuário solicita exclusão;
Então o sistema não exclui nenhum registro de equipamento,
E informa que não há o registro.

*/

public class US11_ExcluirEquipamento {
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
    @Test
    public void testCenario1() throws SQLException, ClienteException{
        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Excluir").click();
        dialog.optionPane().requireMessage("Deseja mesmo excluir Equipamento: " + equipamento.getDescricao() + "?");
        sleep();
        dialog.optionPane().yesButton().click();
        sleep();
        dialog.optionPane().requireMessage("Equipamento excluido com sucesso");
        
        equipamento = null;
    }
    
    @Test
    public void testCenario2() throws SQLException, ClienteException{
        
        dialog.button("Excluir").click();
        dialog.optionPane().requireMessage("Selecione uma linha!");
        sleep();
        dialog.optionPane().okButton().click();
    }
    
}
