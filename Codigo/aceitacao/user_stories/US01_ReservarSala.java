package user_stories;

import java.awt.Dimension;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Aluno;
import model.Professor;
import model.ReservaSalaAluno;
import model.ReservaSalaProfessor;
import model.Sala;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.AlunoDAO;
import persistence.ProfessorDAO;
import persistence.ResSalaAlunoDAO;
import persistence.ResSalaProfessorDAO;
import persistence.SalaDAO;
import view.Main2;
import view.mainViews.AlunoView;
import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;

/**
 * US1 Título: Reservar sala. Como cliente (aluno/professor), Eu quero reservar
 * uma sala Para que eu possa usufruir, sempre que necessário, do espaço
 * disponível na FGA.
 * 
 * Cenário 1: Professor deseja reservar sala disponível. Dado que o professor
 * está cadastrado, E a sala está cadastrada, E a sala está disponível, Quando o
 * usuário solicitar a reserva da sala pelo professor, Então o sistema reserva a
 * sala, E informar que a reserva foi realizada com sucesso.
 * 
 * Cenário 2: Aluno deseja reservar sala disponível. Dado que um aluno possui
 * cadastro, E a sala está cadastrada, E a sala está disponível, Quando o
 * usuário solicitar a reserva pelo aluno, Então o sistema reserva a sala, E
 * informar que a reserva foi realizada com sucesso.
 * 
 * Cenário 3: Professor deseja reservar sala já reservada por aluno. Dado que o
 * professor está cadastrado, E a sala está cadastrada, E a sala está reservada
 * por um aluno, Quando o usuário solicitar a reserva da sala pelo professor,
 * Então o sistema cancela a reserva feita pelo aluno, E o sistema reserva a
 * sala pelo professor, E informar que a reserva foi realizada com sucesso.
 * 
 * 
 * Cenário 4: Professor deseja reservar sala reservada por professor Dado que o
 * professor está cadastrado, E a sala está cadastrada, E a sala já está
 * reservada por um professor, Quando o usuário solicitar a reserva da sala pelo
 * professor, Então o sistema deverá negar a reserva, E o sistema deve informar
 * que a sala está indisponível para o dia e horário escolhido. E o sistema não
 * deve substituir a reserva.
 */

public class US01_ReservarSala {

    private FrameFixture window;
    private Robot robot;
    private Sala sala;
    private ReservaSalaProfessor reservaProf;
    private ReservaSalaAluno reservaAluno;
    private Aluno aluno;
    private Professor prof;
    private DialogFixture dialog;
    private int index;
    private int indexReserva;
    private String data;

    // private int index;

    private void dataAtual() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        this.data = formatador.format(date);
    }

    @Before public void setUp() throws PatrimonioException, SQLException, ClienteException, ReservaException {
        robot = BasicRobot.robotWithNewAwtHierarchy();
        robot.settings().delayBetweenEvents(5);

        window = new FrameFixture(robot, new Main2());
        window.show(new Dimension(900, 500)); // shows the frame to test

        sala = new Sala("code", "Sala para testes de aceitacao", "123");
        SalaDAO.getInstance().incluir(sala);

        prof = new Professor("Professor Teste", "658.535.144-40", "110038096", "9211-2144", "teste incluir repetido");
        ProfessorDAO.getInstance().incluir(prof);

        aluno = new Aluno("Aluno Teste", "658.535.144-40", "110038096", "9211-2144", "teste incluir repetido");
        AlunoDAO.getInstance().incluir(aluno);

        dataAtual();

        index = SalaDAO.getInstance().buscarTodos().size() - 1;
        indexReserva = ResSalaProfessorDAO.getInstance().buscarPorData(data).size() - 1;

        window.button("Sala").click();
        dialog = window.dialog("SalaView");
    }

    @After public void tearDown() throws SQLException, PatrimonioException, ClienteException, ReservaException {
        if (reservaProf != null)
            ResSalaProfessorDAO.getInstance().excluir(reservaProf);
        if (reservaAluno != null)
            ResSalaAlunoDAO.getInstance().excluir(reservaAluno);
        if (sala != null)
            SalaDAO.getInstance().excluir(sala);
        if (aluno != null)
            AlunoDAO.getInstance().excluir(aluno);
        if (prof != null)
            ProfessorDAO.getInstance().excluir(prof);
        window.cleanUp();
    }

    public void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test public void testCenario1Professor() throws SQLException, ClienteException, PatrimonioException, ReservaException {

        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Visualizar Horarios").click();

        DialogFixture diaReservaSala = dialog.dialog("DiaReservaSala");
        diaReservaSala.button("VisualizarButton").click();

        DialogFixture horarioReservaSala = dialog.dialog("HorarioReservaSala");
        horarioReservaSala.button("ReservarButton").click();

        DialogFixture fazerReservaSalaView = dialog.dialog("FazerReservaSalaView");
        fazerReservaSalaView.radioButton("professorRadioButton").click();
        fazerReservaSalaView.textBox("CPF").enterText("658.535.144-40");
        fazerReservaSalaView.button("BuscarCpfButton").click();
        fazerReservaSalaView.textBox("Finalidade").enterText("aula");
        fazerReservaSalaView.textBox("Hora").enterText("23:59");
        fazerReservaSalaView.button("Reservar").click();

        fazerReservaSalaView.optionPane().requireMessage("Reserva feita com sucesso");
        fazerReservaSalaView.optionPane().okButton().click();

        indexReserva = ResSalaProfessorDAO.getInstance().buscarPorData(data).size() - 1;
        reservaProf = ResSalaProfessorDAO.getInstance().buscarPorData(data).get(indexReserva);
    }

    @Test public void testCenario1ProfessorCpfInvalido() throws SQLException, ClienteException, PatrimonioException, ReservaException {

        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Visualizar Horarios").click();

        DialogFixture diaReservaSala = dialog.dialog("DiaReservaSala");
        diaReservaSala.button("VisualizarButton").click();

        DialogFixture horarioReservaSala = dialog.dialog("HorarioReservaSala");
        horarioReservaSala.button("ReservarButton").click();

        DialogFixture fazerReservaSalaView = dialog.dialog("FazerReservaSalaView");
        fazerReservaSalaView.radioButton("professorRadioButton").click();
        fazerReservaSalaView.textBox("CPF").enterText("65853514440");
        fazerReservaSalaView.button("BuscarCpfButton").click();
        fazerReservaSalaView.optionPane().requireMessage(
                "Professor nao Cadastrado. Digite o CPF correto ou cadastre o professor desejado");
        fazerReservaSalaView.optionPane().okButton().click();
        reservaProf = null;
    }

    @Test public void testProfessorHoraAnterior() throws SQLException, ClienteException, PatrimonioException, ReservaException {

        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Visualizar Horarios").click();

        DialogFixture diaReservaSala = dialog.dialog("DiaReservaSala");
        diaReservaSala.button("VisualizarButton").click();

        DialogFixture horarioReservaSala = dialog.dialog("HorarioReservaSala");
        horarioReservaSala.button("ReservarButton").click();

        DialogFixture fazerReservaSalaView = dialog.dialog("FazerReservaSalaView");
        fazerReservaSalaView.radioButton("professorRadioButton").click();
        fazerReservaSalaView.textBox("CPF").enterText("658.535.144-40");
        fazerReservaSalaView.button("BuscarCpfButton").click();
        fazerReservaSalaView.textBox("Finalidade").enterText("aula");
        fazerReservaSalaView.textBox("Hora").enterText("00:00");
        fazerReservaSalaView.button("Reservar").click();

        fazerReservaSalaView.optionPane().requireMessage("A hora escolhida ja passou.");
        fazerReservaSalaView.optionPane().okButton().click();
        
    }

    @Test public void testCenario2Aluno() throws SQLException, ClienteException, PatrimonioException, ReservaException {

        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Visualizar Horarios").click();

        DialogFixture diaReservaSala = dialog.dialog("DiaReservaSala");
        diaReservaSala.button("VisualizarButton").click();

        DialogFixture horarioReservaSala = dialog.dialog("HorarioReservaSala");
        horarioReservaSala.button("ReservarButton").click();

        DialogFixture fazerReservaSalaView = dialog.dialog("FazerReservaSalaView");
        fazerReservaSalaView.radioButton("alunoRadioButton").click();
        fazerReservaSalaView.textBox("CPF").enterText("658.535.144-40");
        fazerReservaSalaView.button("BuscarCpfButton").click();
        fazerReservaSalaView.textBox("Finalidade").enterText("aula");
        fazerReservaSalaView.textBox("Hora").enterText("23:59");
        fazerReservaSalaView.button("VerificarCadeirasButton").click();
        fazerReservaSalaView.textBox("Quantidade de Cadeiras Reservadas").enterText("123");
        fazerReservaSalaView.button("Reservar").click();

        fazerReservaSalaView.optionPane().requireMessage("Reserva feita com sucesso");
        fazerReservaSalaView.optionPane().okButton().click();

        indexReserva = ResSalaAlunoDAO.getInstance().buscarPorDia(data).size() - 1;
        reservaAluno = ResSalaAlunoDAO.getInstance().buscarTodos().lastElement();
    }

    @Test public void testCenario2AlunoCpfInvalido() throws SQLException, ClienteException, PatrimonioException, ReservaException {

        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Visualizar Horarios").click();

        DialogFixture diaReservaSala = dialog.dialog("DiaReservaSala");
        diaReservaSala.button("VisualizarButton").click();

        DialogFixture horarioReservaSala = dialog.dialog("HorarioReservaSala");
        horarioReservaSala.button("ReservarButton").click();

        DialogFixture fazerReservaSalaView = dialog.dialog("FazerReservaSalaView");
        fazerReservaSalaView.radioButton("alunoRadioButton").click();
        fazerReservaSalaView.textBox("CPF").enterText("65853514440");
        fazerReservaSalaView.button("BuscarCpfButton").click();
        fazerReservaSalaView.optionPane().requireMessage("Aluno nao Cadastrado. Digite o CPF correto ou cadastre o aluno desejado");
        fazerReservaSalaView.optionPane().okButton().click();
        reservaProf = null;
    }

    @Test public void testCenario2AlunoHoraAnterior() throws SQLException, ClienteException, PatrimonioException, ReservaException {

        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Visualizar Horarios").click();

        DialogFixture diaReservaSala = dialog.dialog("DiaReservaSala");
        diaReservaSala.button("VisualizarButton").click();

        DialogFixture horarioReservaSala = dialog.dialog("HorarioReservaSala");
        horarioReservaSala.button("ReservarButton").click();

        DialogFixture fazerReservaSalaView = dialog.dialog("FazerReservaSalaView");
        fazerReservaSalaView.radioButton("alunoRadioButton").click();
        fazerReservaSalaView.textBox("CPF").enterText("658.535.144-40");
        fazerReservaSalaView.button("BuscarCpfButton").click();
        fazerReservaSalaView.textBox("Finalidade").enterText("aula");
        fazerReservaSalaView.textBox("Hora").enterText("00:00");
        fazerReservaSalaView.button("VerificarCadeirasButton").click();
        fazerReservaSalaView.textBox("Quantidade de Cadeiras Reservadas").enterText("123");
        fazerReservaSalaView.button("Reservar").click();

        fazerReservaSalaView.optionPane().requireMessage("A hora escolhida ja passou.");
        fazerReservaSalaView.optionPane().okButton().click();

    }

    @Test public void testCenario2AlunoCadeirasIndisponiveis() throws SQLException, ClienteException, PatrimonioException, ReservaException {
                
        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Visualizar Horarios").click();

        DialogFixture diaReservaSala = dialog.dialog("DiaReservaSala");
        diaReservaSala.button("VisualizarButton").click();

        DialogFixture horarioReservaSala = dialog.dialog("HorarioReservaSala");
        horarioReservaSala.button("ReservarButton").click();

        DialogFixture fazerReservaSalaView = dialog.dialog("FazerReservaSalaView");
        fazerReservaSalaView.radioButton("alunoRadioButton").click();
        fazerReservaSalaView.textBox("CPF").enterText("658.535.144-40");
        fazerReservaSalaView.button("BuscarCpfButton").click();
        fazerReservaSalaView.textBox("Finalidade").enterText("aula");
        fazerReservaSalaView.textBox("Hora").enterText("00:00");
        fazerReservaSalaView.button("VerificarCadeirasButton").click();
        fazerReservaSalaView.textBox("Quantidade de Cadeiras Reservadas").enterText("1234");
        fazerReservaSalaView.button("Reservar").click();

        fazerReservaSalaView.optionPane().requireMessage("A sala nao possui este numero de cadeiras para reservar.");
        fazerReservaSalaView.optionPane().okButton().click();

    }

    
    @Test public void testCenario3() throws SQLException, ClienteException, PatrimonioException, ReservaException {

        reservaAluno = new ReservaSalaAluno(data, "23:59", sala, "abc", sala.getCapacidade(), aluno);
        ResSalaAlunoDAO.getInstance().incluir(reservaAluno);

        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Visualizar Horarios").click();

        DialogFixture diaReservaSala = dialog.dialog("DiaReservaSala");
        diaReservaSala.button("VisualizarButton").click();

        DialogFixture horarioReservaSala = dialog.dialog("HorarioReservaSala");
        horarioReservaSala.button("ReservarButton").click();

        DialogFixture fazerReservaSalaView = dialog.dialog("FazerReservaSalaView");
        fazerReservaSalaView.radioButton("professorRadioButton").click();
        fazerReservaSalaView.textBox("CPF").enterText("658.535.144-40");
        fazerReservaSalaView.button("BuscarCpfButton").click();
        fazerReservaSalaView.textBox("Finalidade").enterText("aula");
        fazerReservaSalaView.textBox("Hora").enterText("23:59");
        fazerReservaSalaView.button("Reservar").click();

        fazerReservaSalaView.optionPane().requireMessage("Reserva feita com sucesso");
        fazerReservaSalaView.optionPane().okButton().click();

        indexReserva = ResSalaProfessorDAO.getInstance().buscarPorData(data).size() - 1;
        reservaProf = ResSalaProfessorDAO.getInstance().buscarPorData(data).get(indexReserva);
        reservaAluno = null;
    }

    
    @Test public void testCenario3AlunoReserva() throws SQLException, ClienteException, PatrimonioException, ReservaException {
        Aluno aluno2 = new Aluno("Aluno Teste", "382.808.446-00", "110", "", "abc");
        AlunoDAO.getInstance().incluir(aluno2);

        ReservaSalaAluno reservaAluno2 = new ReservaSalaAluno(data, "23:59", sala, "abc", "100", aluno2);
        ResSalaAlunoDAO.getInstance().incluir(reservaAluno2);

        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Visualizar Horarios").click();

        DialogFixture diaReservaSala = dialog.dialog("DiaReservaSala");
        diaReservaSala.button("VisualizarButton").click();

        DialogFixture horarioReservaSala = dialog.dialog("HorarioReservaSala");
        horarioReservaSala.button("ReservarButton").click();

        DialogFixture fazerReservaSalaView = dialog.dialog("FazerReservaSalaView");
        fazerReservaSalaView.radioButton("alunoRadioButton").click();
        fazerReservaSalaView.textBox("CPF").enterText("658.535.144-40");
        fazerReservaSalaView.button("BuscarCpfButton").click();
        fazerReservaSalaView.textBox("Finalidade").enterText("aula");
        fazerReservaSalaView.textBox("Hora").enterText("23:59");
        fazerReservaSalaView.button("VerificarCadeirasButton").click();
        fazerReservaSalaView.textBox("Quantidade de Cadeiras Reservadas").enterText("23");
        fazerReservaSalaView.button("Reservar").click();

        fazerReservaSalaView.optionPane().requireMessage("Reserva feita com sucesso");
        fazerReservaSalaView.optionPane().okButton().click();

        indexReserva = ResSalaAlunoDAO.getInstance().buscarPorDia(data).size() - 1;
        reservaAluno = ResSalaAlunoDAO.getInstance().buscarPorDia(data).get(indexReserva);
        
        ResSalaAlunoDAO.getInstance().excluir(reservaAluno2);
        AlunoDAO.getInstance().excluir(aluno2);        
    }

    
    @Test public void testCenario4() throws SQLException, ClienteException, ReservaException, PatrimonioException {

        reservaProf = new ReservaSalaProfessor(data, "23:59", sala, "abc", prof);
        ResSalaProfessorDAO.getInstance().incluir(reservaProf);

        dialog.table("tabelaPatrimonio").selectRows(index);
        dialog.button("Visualizar Horarios").click();

        DialogFixture diaReservaSala = dialog.dialog("DiaReservaSala");
        diaReservaSala.button("VisualizarButton").click();

        DialogFixture horarioReservaSala = dialog.dialog("HorarioReservaSala");
        horarioReservaSala.button("ReservarButton").click();

        DialogFixture fazerReservaSalaView = dialog.dialog("FazerReservaSalaView");
        fazerReservaSalaView.radioButton("professorRadioButton").click();
        fazerReservaSalaView.textBox("CPF").enterText("658.535.144-40");
        fazerReservaSalaView.button("BuscarCpfButton").click();
        fazerReservaSalaView.textBox("Finalidade").enterText("aula");
        fazerReservaSalaView.textBox("Hora").enterText("23:59");
        fazerReservaSalaView.button("Reservar").click();

        indexReserva = ResSalaProfessorDAO.getInstance().buscarPorData(data).size() - 1;
        reservaProf = ResSalaProfessorDAO.getInstance().buscarPorData(data).get(indexReserva);
        reservaAluno = null;

        fazerReservaSalaView.optionPane().requireMessage("A Sala esta reservada no mesmo dia e horario.");
        fazerReservaSalaView.optionPane().okButton().click();

    }

}
