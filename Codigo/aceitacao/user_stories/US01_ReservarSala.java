package user_stories;

import java.awt.Dimension;
import java.sql.SQLException;

import model.Aluno;
import model.Sala;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.AlunoDAO;
import persistence.SalaDAO;
import view.Main2;
import view.mainViews.AlunoView;
import exception.ClienteException;
import exception.PatrimonioException;

/**
 * US1 T�tulo: Reservar sala. Como cliente (aluno/professor), Eu quero reservar
 * uma sala Para que eu possa usufruir, sempre que necess�rio, do espa�o
 * dispon�vel na FGA.
 * 
 * Cen�rio 1: Professor deseja reservar sala dispon�vel. Dado que o professor
 * est� cadastrado, E a sala est� cadastrada, E a sala est� dispon�vel, Quando o
 * usu�rio solicitar a reserva da sala pelo professor, Ent�o o sistema reserva a
 * sala, E informar que a reserva foi realizada com sucesso.
 * 
 * Cen�rio 2: Aluno deseja reservar sala dispon�vel. Dado que um aluno possui
 * cadastro, E a sala est� cadastrada, E a sala est� dispon�vel, Quando o
 * usu�rio solicitar a reserva pelo aluno, Ent�o o sistema reserva a sala, E
 * informar que a reserva foi realizada com sucesso.
 * 
 * Cen�rio 3: Professor deseja reservar sala j� reservada por aluno. Dado que o
 * professor est� cadastrado, E a sala est� cadastrada, E a sala est� reservada
 * por um aluno, Quando o usu�rio solicitar a reserva da sala pelo professor,
 * Ent�o o sistema cancela a reserva feita pelo aluno, E o sistema reserva a
 * sala pelo professor, E informar que a reserva foi realizada com sucesso.
 * 
 * 
 * Cen�rio 4: Professor deseja reservar sala reservada por professor Dado que o
 * professor est� cadastrado, E a sala est� cadastrada, E a sala j� est�
 * reservada por um professor, Quando o usu�rio solicitar a reserva da sala pelo
 * professor, Ent�o o sistema dever� negar a reserva, E o sistema deve informar
 * que a sala est� indispon�vel para o dia e hor�rio escolhido. E o sistema n�o
 * deve substituir a reserva.
 */

public class US01_ReservarSala {

    private FrameFixture window;
    private Robot robot;
    private Sala sala;
    private Aluno aluno;
    private DialogFixture dialog;
    private int index;

    @Before public void setUp() throws PatrimonioException, SQLException, ClienteException {
        robot = BasicRobot.robotWithNewAwtHierarchy();
        robot.settings().delayBetweenEvents(5);

        window = new FrameFixture(robot, new Main2());
        window.show(new Dimension(900, 500)); // shows the frame to test

        sala = new Sala("code", "Sala para testes de aceitacao", "123");
        SalaDAO.getInstance().incluir(sala);

        aluno = new Aluno("Teste", "034.355.431-32", "110038096", "9211-2144", "teste incluir repetido");
        AlunoDAO.getInstance().incluir(aluno);

        index = SalaDAO.getInstance().buscarTodos().size() - 1;

        window.button("Sala").click();
        dialog = window.dialog("SalaView");
    }

    @After public void tearDown() throws SQLException, PatrimonioException, ClienteException {
        if (sala != null)
            SalaDAO.getInstance().excluir(sala);
        if (aluno != null)
            AlunoDAO.getInstance().excluir(aluno);
        window.cleanUp();
    }

    public void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test public void testCenario2() throws SQLException, ClienteException {

        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Visualizar Horarios").click();

        DialogFixture diaReservaSala = dialog.dialog("DiaReservaSala");
        diaReservaSala.button("VisualizarButton").click();

    }

}
