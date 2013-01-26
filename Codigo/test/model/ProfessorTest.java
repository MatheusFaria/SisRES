package test.model;

import static org.junit.Assert.*;

import model.Professor;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import exception.ClienteException;

public class ProfessorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Ignore
	public void testProfessor() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetNome() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		assertEquals("Resultado", "Nome", p.getNome());
	}

	@Test
	public void testGetCpf() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		assertEquals("Resultado", "868.563.327-34", p.getCpf());
	}

	@Test
	public void testGetTelefone() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		assertEquals("Resultado", "1234-5678", p.getTelefone());
	}

	@Test
	public void testGetEmail() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		assertEquals("Resultado", "Nome@email", p.getEmail());
	}

	@Test
	public void testGetMatricula() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		assertEquals("Resultado", "123456", p.getMatricula());
	}

	@Test
	public void testSetNome() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		String s = "NomeNome";
		p.setNome(s);
		assertEquals("Resultado", s, p.getNome());
	}

	@Test
	public void testSetCpf() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		String s = "136.338.714-62";
		p.setCpf(s);
		assertEquals("Resultado", s, p.getCpf());
	}

	@Test
	public void testSetTelefone() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		String s = "4444-5678";
		p.setTelefone(s);
		assertEquals("Resultado", s, p.getTelefone());
	}

	@Test
	public void testSetEmail() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		String s = "NomeNomeNome@email";
		p.setEmail(s);
		assertEquals("Resultado", s, p.getEmail());
	}

	@Test
	public void testSetMatricula() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		String s = "098765";
		p.setMatricula(s);
		assertEquals("Resultado", s, p.getMatricula());
	}

	@Test
	public void testToString() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		String s = "Cliente [nome=" + "Nome" + ", cpf=" + "868.563.327-34" + ", telefone="
				+ "1234-5678" + ", email=" + "Nome@email" + ", matricula=" + "123456"
				+ "]";
		assertEquals("Resultado", s, p.toString());
	}
}
