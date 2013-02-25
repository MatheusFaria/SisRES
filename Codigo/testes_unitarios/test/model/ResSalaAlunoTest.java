package test.model;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.Aluno;
import model.ReservaSalaAluno;
import model.Sala;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;

public class ResSalaAlunoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testInstance() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala,
				"Grupo de Estudos", "120", aluno);
		assertTrue("Teste de Instancia.", reserva instanceof ReservaSalaAluno);
	}
	
	
	
	@Test (expected= ReservaException.class)
	public void testAlunoNulo() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = null;
		new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala, "Grupo de Estudos", "30", aluno);
	}
	
	
	
	@Test (expected= ReservaException.class)
	public void testCadeirasNula() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala, "Grupo de Estudos", null, aluno);
	}
	
	@Test (expected= ReservaException.class)
	public void testCadeirasVazias() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala, "Grupo de Estudos", "     ", aluno);
	}
	
	@Test (expected= ReservaException.class)
	public void testCadeirasDespadronizadas() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala, "Grupo de Estudos", "3A-", aluno);
	}
	
	@Test (expected= ReservaException.class)
	public void testCadeirasAcimaCapacidade() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala, "Grupo de Estudos", "121", aluno);
	}
	
	
	
	@Test (expected= ReservaException.class)
	public void testFinalidadeNula() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala, null, "11", aluno);
	}
	@Test (expected= ReservaException.class)
	public void testFinalidadeVazia() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala, "     ", "11", aluno);
	}
	
	
	
	@Test (expected= ReservaException.class)
	public void testSalaNula() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = null;
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala, "Grupo de Estudos", "30", aluno);
	}
	
	
	
	@Test
	public void testHora() throws PatrimonioException, ClienteException, ReservaException {
		String hora = this.horaAtualAMais(100000000);
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtual(),
				hora, sala,
				"Grupo de Estudos", "120", aluno);
		assertTrue("", reserva.getHora() == hora);
	}
	@Test (expected= ReservaException.class)
	public void testHoraNula() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(this.dataAtual(), null, sala, "Grupo de Estudos", "120", aluno);
	}
	@Test (expected= ReservaException.class)
	public void testHoraVazia() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(this.dataAtual(), "    ", sala, "Grupo de Estudos", "120", aluno);
	}
	@Test (expected= ReservaException.class)
	public void testHoraDespadronizada() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(this.dataAtual(), "1000", sala, "Grupo de Estudos", "120", aluno);
	}
	
	
	
	@Test
	public void testData() throws PatrimonioException, ClienteException, ReservaException {
		String data = "12/2/33";
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		ReservaSalaAluno reserva = new ReservaSalaAluno(data,
				"8:00", sala, "Grupo de Estudos", "120", aluno);

		assertTrue("", reserva.getData().equals("12/02/2033"));
	}
	@Test (expected= ReservaException.class)
	public void testDataNula() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno(null, this.horaAtual(), sala, "Grupo de Estudos", "120", aluno);
	}
	@Test (expected= ReservaException.class)
	public void testDataVazia() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno("    ", this.horaAtual(), sala, "Grupo de Estudos", "120", aluno);
	}
	@Test (expected= ReservaException.class)
	public void testDataDespadronizada() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		new ReservaSalaAluno("12/q2/2030", this.horaAtual(), sala, "Grupo de Estudos", "120", aluno);
	}
	
	
	@Test
	public void testEqualsTrue() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala,
				"Grupo de Estudos", "120", aluno);
		ReservaSalaAluno reserva2 = new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala,
				"Grupo de Estudos", "120", aluno);
		assertTrue("Teste de Equals.", reserva.equals(reserva2));
	}
	@Test
	public void testEqualsFalseSala() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Sala sala2 = new Sala("1233", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala,
				"Grupo de Estudos", "120", aluno);
		ReservaSalaAluno reserva2 = new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala2,
				"Grupo de Estudos", "120", aluno);
		assertFalse("Teste de Equals False.", reserva.equals(reserva2));
	}
	@Test
	public void testEqualsFalseAluno() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		Aluno aluno2 = new Aluno("testInstanceD", "501.341.852-69", "456678", "", "");
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala,
				"Grupo de Estudos", "120", aluno);
		ReservaSalaAluno reserva2 = new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala,
				"Grupo de Estudos", "120", aluno2);
		assertFalse("Teste de Equals False.", reserva.equals(reserva2));
	}
	@Test
	public void testEqualsFalseData() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtualAMais(100000000), this.horaAtual(), sala,
				"Grupo de Estudos", "120", aluno);
		ReservaSalaAluno reserva2 = new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala,
				"Grupo de Estudos", "120", aluno);
		assertFalse("Teste de Equals False.", reserva.equals(reserva2));
	}
	@Test
	public void testEqualsFalseHora() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtual(), this.horaAtualAMais(10000000), sala,
				"Grupo de Estudos", "120", aluno);
		ReservaSalaAluno reserva2 = new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala,
				"Grupo de Estudos", "120", aluno);
		assertFalse("Teste de Equals False.", reserva.equals(reserva2));
	}
	@Test
	public void testEqualsFalseFinalidade() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala,
				"Grupo de Estudos So q n", "120", aluno);
		ReservaSalaAluno reserva2 = new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala,
				"Grupo de Estudos", "120", aluno);
		assertFalse("Teste de Equals False.", reserva.equals(reserva2));
	}
	@Test
	public void testEqualsFalseCadierasReservadas() throws PatrimonioException, ClienteException, ReservaException {
		Sala sala = new Sala("123", "Sala de Aula", "120");
		Aluno aluno = new Aluno("testInstance", "501.341.852-69", "456678", "", "");
		ReservaSalaAluno reserva = new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala,
				"Grupo de Estudos", "120", aluno);
		ReservaSalaAluno reserva2 = new ReservaSalaAluno(this.dataAtual(), this.horaAtual(), sala,
				"Grupo de Estudos", "1", aluno);
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
