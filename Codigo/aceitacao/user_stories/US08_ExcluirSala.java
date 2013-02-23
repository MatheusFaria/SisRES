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
import exception.ClienteException;
import exception.PatrimonioException;

/**
 * US8 Título: Excluir sala. Como usuário Eu quero excluir uma sala Para que a
 * mesma seja indisponibilizada para reserva.
 * 
 * Cenário 1: Existe sala cadastrada. Dado que a sala está cadastrada; Quando o
 * usuário solicita a exclusão; Então o sistema deve eliminar os registros da
 * sala, E informar o sucesso da exclusão.
 * 
 * Cenário 2: Não existe sala cadastrada. Dado que não existe o registro da
 * sala; Quando o usuário solicita exclusão; Então o sistema não exclui nenhum
 * registro de sala, E informa que não há o registro.
 */

public class US08_ExcluirSala {

    private FrameFixture window;
    private Robot robot;
    private Sala sala;
    private DialogFixture dialog;
    private int index;

    @Before public void setUp() throws PatrimonioException, SQLException {
        robot = BasicRobot.robotWithNewAwtHierarchy();
        robot.settings().delayBetweenEvents(5);

        window = new FrameFixture(robot, new Main2());
        window.show(new Dimension(900, 500)); // shows the frame to test

        sala = new Sala("code", "Sala para testes de aceitacao", "123");
        SalaDAO.getInstance().incluir(sala);

        index = SalaDAO.getInstance().buscarTodos().size() - 1;

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
    @Test
    public void testCenario1() throws SQLException, ClienteException{
        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Excluir").click();
        dialog.optionPane().requireMessage("Deseja mesmo excluir Sala: " + sala.getDescricao() + "?");
        sleep();
        dialog.optionPane().yesButton().click();
        sleep();
        dialog.optionPane().requireMessage("Sala excluida com sucesso");
        
        sala = null;
    }
    
    @Test
    public void testCenario2() throws SQLException, ClienteException{
        
        dialog.button("Excluir").click();
        dialog.optionPane().requireMessage("Selecione uma linha!");
        sleep();
        dialog.optionPane().okButton().click();
    }
    
   }
