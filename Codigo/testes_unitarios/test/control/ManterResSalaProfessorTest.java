package test.control;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Professor;
import model.ReservaSalaProfessor;
import model.Sala;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import control.ManterResSalaProfessor;
import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;

import persistence.FactoryConnection;
import persistence.ProfessorDAO;
import persistence.SalaDAO;

public class ManterResSalaProfessorTest {
	private static Sala sala1;
	private static Professor professor1;
	private static Vector<ReservaSalaProfessor> vet;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		vet = ManterResSalaProfessor.getInstance().getResProfessorSala_vet();
		sala1 = new Sala("123", "Sala de Aula", "120");
		professor1 = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "nome@email");
		
		ProfessorDAO.getInstance().incluir(professor1);
		SalaDAO.getInstance().incluir(sala1);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ProfessorDAO.getInstance().excluir(professor1);
		SalaDAO.getInstance().excluir(sala1);
	}

	@Test
	public void testInstance() {
		assertTrue("Teste de Instancia.", ManterResSalaProfessor.getInstance() instanceof ManterResSalaProfessor);
	}
	@Test
	public void testSingleton() {
		assertSame("Teste de Instancia.", ManterResSalaProfessor.getInstance(), ManterResSalaProfessor.getInstance());
	}
	
	
	@Test
	public void testInserir() throws SQLException, ReservaException, ClienteException, PatrimonioException {
		String finalidade = "Sala de Estudos";
		String data = "20/12/33";
		String hora = "9:11";
		ReservaSalaProfessor reserva = new ReservaSalaProfessor(data, hora, sala1, finalidade, professor1);
		ManterResSalaProfessor.getInstance().inserir(sala1, professor1, data, hora, finalidade);
		boolean resultado = this.inDB(reserva);
		boolean resultado2 = reserva.equals(vet.lastElement());
		if(resultado)
			this.delete_from(reserva);
		assertTrue("Teste de Insercao.", resultado && resultado2);
	}
	@Test
	public void testAlterar() throws ReservaException, SQLException, ClienteException, PatrimonioException {
		
		ReservaSalaProfessor reserva = new ReservaSalaProfessor("20/12/33", "9:11", sala1, "Pesquisa", professor1);
		this.insert_into(reserva);
		vet.add(reserva);
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor("20/12/33", "9:11", sala1, "Reuniao", professor1);
		ManterResSalaProfessor.getInstance().alterar("Reuniao", vet.lastElement());
		boolean resultado = this.inDB(reserva2);
		boolean resultado2 = reserva2.equals(vet.lastElement());
		if(resultado)
			this.delete_from(reserva2);
		if(this.inDB(reserva))
			this.delete_from(reserva);
		assertTrue("Teste de Alteracao.", resultado && resultado2);
	}
	@Test
	public void testExcluir() throws ReservaException, SQLException {
		String finalidade = "Pesquisa";
		String data = "20/12/33";
		String hora = "9:11";
		ReservaSalaProfessor reserva = new ReservaSalaProfessor(data, hora, sala1, finalidade, professor1);
		this.insert_into(reserva);
		vet.add(reserva);
		ManterResSalaProfessor.getInstance().excluir(reserva);
		boolean resultado = this.inDB(reserva);
		vet.remove(reserva);

		if(resultado)
			this.delete_from(reserva);
		assertTrue("Teste de Exclusao.", !resultado );
	}

	private String select_id_professor(Professor prof){
		return "SELECT id_professor FROM professor WHERE " +
				"professor.nome = \"" + prof.getNome() + "\" and " +
				"professor.cpf = \"" + prof.getCpf() + "\" and " +
				"professor.telefone = \"" + prof.getTelefone() + "\" and " +
				"professor.email = \"" + prof.getEmail() + "\" and " +
				"professor.matricula = \"" + prof.getMatricula() + "\"";
	}
	private String select_id_sala(Sala sala){
		return "SELECT id_sala FROM sala WHERE " +
				"sala.codigo = \"" + sala.getCodigo() + "\" and " +
				"sala.descricao = \"" + sala.getDescricao() +  "\" and " +
				"sala.capacidade = " + sala.getCapacidade();
	}
	private String where_reserva_sala_professor(ReservaSalaProfessor reserva){
		return " WHERE " +
		"id_professor = ( " + select_id_professor(reserva.getProfessor()) + " ) and " +
		"id_sala = ( " + select_id_sala(reserva.getSala()) + " ) and " +
		"finalidade = \"" + reserva.getFinalidade() + "\" and " +
		"hora = \"" + reserva.getHora() + "\" and " +
		"data = \"" + reserva.getData() + "\" ";
	}
	private String values_reserva_sala_professor(ReservaSalaProfessor reserva){
		return "( " + select_id_professor(reserva.getProfessor()) + " ), " +
		"( " + select_id_sala(reserva.getSala()) + " ), " +
		"\"" + reserva.getFinalidade() + "\", " +
		"\"" + reserva.getHora() + "\", " +
		"\"" + reserva.getData() + "\"";
	}
	private void insert_into(ReservaSalaProfessor reserva){
		try {
			this.executeQuery("INSERT INTO " +
					"reserva_sala_professor (id_professor, id_sala, finalidade, hora, data) " +
					"VALUES ( " + values_reserva_sala_professor(reserva) + " );");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void delete_from(ReservaSalaProfessor reserva){
		try {
			this.executeQuery("DELETE FROM reserva_sala_professor " + 
								this.where_reserva_sala_professor(reserva) + " ;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private boolean inDB(ReservaSalaProfessor reserva) throws SQLException{
		return this.inDBGeneric("SELECT * FROM reserva_sala_professor " + 
								this.where_reserva_sala_professor(reserva) + " ;");
	}
	private boolean inDBGeneric(String query) throws SQLException{
		Connection con = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		
		if(!rs.next())
		{
			rs.close();
			pst.close();
			con.close();
			return false;
		}
		else {
			rs.close();
			pst.close();
			con.close();
			return true;
		}
	}	
	private void executeQuery(String msg) throws SQLException{
		Connection con =  FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = con.prepareStatement(msg);
		pst.executeUpdate();		
		pst.close();
		con.close();
	}

	

}
