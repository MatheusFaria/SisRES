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

/**
 * US2 Titulo: Reservar equipamento. Como professor, Eu quero reservar um
 * equipamento, Para que eu possa usufruir, sempre que necessário, dos recursos
 * disponíveis na FGA.
 * 
 * Cenário 1: Aluno deseja reservar equipamento. Dado que um aluno possui
 * cadastro, E solicita reserva de equipamento, Quando o usuário solicitar a
 * reserva pelo aluno, Então o sistema deve negar a reserva.
 * 
 * Cenário 2: Professor deseja reservar equipamento disponível. Dado que o
 * professor está cadastrado, E o equipamento está cadastrado, E o equipamento
 * está disponível, Quando o usuário solicitar a reserva do equipamento pelo
 * professor, Então o sistema reserva o equipamento, E informar que a reserva
 * foi realizada com sucesso.
 * 
 * Cenário 3: Professor deseja reservar equipamento indisponível. Dado que o
 * professor está cadastrado, E o equipamento está cadastrado, E o equipamento
 * já está reservado, Quando o usuário solicitar a reserva do equipamento pelo
 * professor, Então o sistema deverá negar a reserva, E o sistema deve informar
 * que o equipamento está indisponível para o dia e horário escolhido. E o
 * sistema não deve substituir a reserva.
 */
public class US02_ReservarEquipamento {
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

    @Test public void testCenario1() {
        sleep();
    }
}
