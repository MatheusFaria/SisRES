package test.model;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.Professor;
import model.ReservaSalaProfessor;
import model.Sala;

import org.junit.Test;

import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;

public class ReservaSalaProfessorTest {

	
	@Test
	public void testInstance() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		ReservaSalaProfessor reserva = new ReservaSalaProfessor(this.dataAtual(), this.horaAtual(), sala,
				"Reuniao", professor);
		assertTrue("Teste de Instancia.", reserva instanceof ReservaSalaProfessor);
	}

	
	
	
	@Test (expected= ReservaException.class)
	public void testProfessorNulo() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Professor professor = null;
		new ReservaSalaProfessor(this.dataAtual(), this.horaAtual(), sala, "Pesquisa", professor);
	}
	
	@Test (expected= ReservaException.class)
	public void testFinalidadeNula() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		new ReservaSalaProfessor(this.dataAtual(), this.horaAtual(), sala, null, professor);
	}
	@Test (expected= ReservaException.class)
	public void testFinalidadeVazia() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		new ReservaSalaProfessor(this.dataAtual(), this.horaAtual(), sala, "     ", professor);
	}
	
	
	
	@Test (expected= ReservaException.class)
	public void testSalaNula() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = null;
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		new ReservaSalaProfessor(this.dataAtual(), this.horaAtual(), sala, "Pesquisa", professor);
	}
	
	
	
	@Test
	public void testHora() throws PatrimonioException, ClienteException, ReservaException {
		String hora = this.horaAtualAMais(100000000);
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		ReservaSalaProfessor reserva = new ReservaSalaProfessor(this.dataAtual(),
				hora, sala, "Reuniao", professor);
		assertTrue("", reserva.getHora() == hora);
	}
	@Test (expected= ReservaException.class)
	public void testHoraNula() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		new ReservaSalaProfessor(this.dataAtual(), null, sala, "Reuniao", professor);
	}
	@Test (expected= ReservaException.class)
	public void testHoraVazia() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		new ReservaSalaProfessor(this.dataAtual(), "    ", sala, "Pesquisa", professor);
	}
	@Test (expected= ReservaException.class)
	public void testHoraDespadronizada() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		new ReservaSalaProfessor(this.dataAtual(), "1000", sala, "Reuniao", professor);
	}
	
	@Test
	public void testData() throws PatrimonioException, ClienteException, ReservaException {
		String data = "12/2/33";
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		ReservaSalaProfessor reserva = new ReservaSalaProfessor(data,
				this.horaAtual(), sala, "Aula de DS", professor);

		assertTrue("", reserva.getData().equals("12/02/2033"));
	}
	@Test (expected= ReservaException.class)
	public void testDataNula() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		new ReservaSalaProfessor(null, this.horaAtual(), sala, "Aula de C1", professor);
	}
	@Test (expected= ReservaException.class)
	public void testDataVazia() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		new ReservaSalaProfessor("    ", this.horaAtual(), sala, "Aula de fisica", professor);
	}
	
	@Test (expected= ReservaException.class)
	public void testDataComChar() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaProfessor("12/q2/2030", this.horaAtual(), sala, "Grupo de Estudos", professor);
	}
	
	@Test
	public void testEqualsTrue() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		ReservaSalaProfessor reserva = new ReservaSalaProfessor(this.dataAtual(), this.horaAtual(), sala,
				"Reforco", professor);
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor(this.dataAtual(), this.horaAtual(), sala,
				"Reforco", professor);
		assertTrue("Teste de Equals.", reserva.equals(reserva2));
	}
	@Test
	public void testEqualsFalseSala() throws PatrimonioException, ClienteException, ReservaException {//mesma reserva mas em salas dif
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Sala sala2 = new Sala("1233", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		ReservaSalaProfessor reserva = new ReservaSalaProfessor(this.dataAtual(), this.horaAtual(), sala,
				"Reuniao", professor);
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor(this.dataAtual(), this.horaAtual(), sala2,
				"Reuniao", professor);
		
		assertFalse("Teste de Equals False.", reserva.equals(reserva2));
	}
	@Test
	public void testEqualsFalseProfessor() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		Professor professor2 = new Professor("testInstanceD", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		ReservaSalaProfessor reserva = new ReservaSalaProfessor(this.dataAtual(), this.horaAtual(), sala,
				"Reuniao", professor);
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor(this.dataAtual(), this.horaAtual(), sala,
				"Reuniao", professor2);
		assertFalse("Teste de Equals False.", reserva.equals(reserva2));
	}
	@Test
	public void testEqualsFalseData() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		ReservaSalaProfessor reserva = new ReservaSalaProfessor(this.dataAtualAMais(100000000), this.horaAtual(), sala,
				"Grupo de Estudos", professor);
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor(this.dataAtual(), this.horaAtual(), sala,
				"Grupo de Estudos", professor);
		assertFalse("Teste de Equals False.", reserva.equals(reserva2));
	}
	@Test
	public void testEqualsFalseHora() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		ReservaSalaProfessor reserva = new ReservaSalaProfessor(this.dataAtual(), this.horaAtualAMais(10000000), sala,
				"Reuniao", professor);
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor(this.dataAtual(), this.horaAtual(), sala,
				"Reuniao", professor);
		assertFalse("Teste de Equals False.", reserva.equals(reserva2));
	}
	@Test
	public void testEqualsFalseFinalidade() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Professor professor = new Professor("testInstance", "040.757.021-70", "0058801", "3333-3333", "Node@email");
		ReservaSalaProfessor reserva = new ReservaSalaProfessor(this.dataAtual(), this.horaAtual(), sala,
				"Reuniao", professor);
		ReservaSalaProfessor reserva2 = new ReservaSalaProfessor(this.dataAtual(), this.horaAtual(), sala,
				"Pesquisa", professor);
		assertFalse("Teste de Equals False.", reserva.equals(reserva2));
	}
	
	
	private String dataAtual(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		return formatador.format(date);
	}
	
	private String horaAtual(){
		Date date = new Date(System.currentTimeMillis());
		return date.toString().substring(11, 16);
	}
	
	private String horaAtualAMais(int fator){
		Date date = new Date(System.currentTimeMillis()+fator);
		return date.toString().substring(11, 16);
	}
	
	private String dataAtualAMais(int fator){
		Date date = new Date(System.currentTimeMillis()+fator);
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		return formatador.format(date);
	}

}
