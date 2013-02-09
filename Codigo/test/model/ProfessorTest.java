package test.model;

import static org.junit.Assert.*;

import model.Professor;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import exception.ClienteException;

public class ProfessorTest {
	
	/**
	 *	Os teste da classe Cliente foram feitos aqui, por se tratar de uma classe
	 * abstrata, ela nao eh instaciavel, entao todas as suas funcionalidade foram
	 * testadas a partir das instancias da classe Professor.
	 */

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	
	@Test
	public void testInstance() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		assertTrue("Teste de Instanciamento do Professor", p instanceof Professor);
	}
	
	@Test
	public void testNome() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		assertTrue("Teste do Nome do Professor", "Nome" == p.getNome());
	}

	@Test
	public void testCpf() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		assertTrue("Teste do CPF do Professor", "868.563.327-34" == p.getCpf());
	}
	
	@Test
	public void testMatricula() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		assertTrue("Teste da Matricula do Professor", "123456" == p.getMatricula());
	}
	
	@Test
	public void testTelefone() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		assertTrue("Teste de Telefone do Professor", "1234-5678" == p.getTelefone());
	}
	
	@Test
	public void testEmail() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		assertTrue("Teste do E-mail do Professor", "Nome@email" == p.getEmail());
	}

	
	
	@Test (expected= ClienteException.class)
	public void testNomeVazio() throws ClienteException {
		new Professor("", "868.563.327-34", "123456", "1234-5678", "Nome@email");
	}

	@Test (expected= ClienteException.class)
	public void testNomeNumero() throws ClienteException {
		new Professor("Nome1", "868.563.327-34", "123456", "1234-5678", "Nome@email");
	}
	
	@Test (expected= ClienteException.class)
	public void testNomeCaractere() throws ClienteException {
		new Professor("Nome+", "868.563.327-34", "123456", "1234-5678", "Nome@email");
	}
	
	@Test (expected= ClienteException.class)
	public void testNomeNulo() throws ClienteException {
		new Professor(null, "868.563.327-34", "123456", "1234-5678", "Nome@email");
	}

	
	
	@Test (expected= ClienteException.class)
	public void testCpfVazio() throws ClienteException {
		new Professor("Nome", "", "123456", "1234-5678", "Nome@email");
	}

	@Test (expected= ClienteException.class)
	public void testCpfLetras() throws ClienteException {
		new Professor("Nome", "868.563.327-3d", "123456", "1234-5678", "Nome@email");
	}
	
	@Test (expected= ClienteException.class)
	public void testCpfDespadronizado() throws ClienteException {
		new Professor("Nome", "86856332734", "123456", "1234-5678", "Nome@email");
	}
	
	@Test (expected = ClienteException.class)
	public void testCpfInvalido() throws ClienteException {
		new Professor("Nome", "868.563.327-21", "123456", "1234-5678", "Nome@email");
	}
	
	@Test (expected= ClienteException.class)
	public void testCpfNulo() throws ClienteException {
		new Professor("Nome", null, "123456", "1234-5678", "Nome@email");
	}
	
	
	
	@Test (expected= ClienteException.class)
	public void testMatriculaVazia() throws ClienteException {
		new Professor("Nome", "868.563.327-34", "", "1234-5678", "Nome@email");
	}
	
	@Test (expected= ClienteException.class)
	public void testMatriculaNula() throws ClienteException {
		new Professor("Nome", "868.563.327-34", null, "1234-5678", "Nome@email");
	}
	
	
	
	@Test
	public void testTelefoneVazio() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "", "Nome@email");
		assertTrue("Teste de Telefone Vazio do Professor", "" == p.getTelefone());
	}
	
	@Test (expected= ClienteException.class)
	public void testTelefoneDespadronizado() throws ClienteException {
		new Professor("Nome", "868.563.327-34", "123456", "(901234-5678", "Nome@email");
	}
	
	@Test (expected= ClienteException.class)
	public void testTelefoneNulo() throws ClienteException {
		new Professor("Nome", "868.563.327-34", "123456", null, "Nome@email");
	}

	
	
	@Test
	public void testEmailVazio() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "1234-5678", "");
		assertTrue("Teste de Email Vazio do Professor", "" == p.getEmail());
	}
	
	@Test (expected= ClienteException.class)
	public void testEmailNulo() throws ClienteException {
		new Professor("Nome", "868.563.327-34", "123456", "123456", null);
	}

	
	
	@Test
	public void testEqualsTrue() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "", "Nome@email");
		Professor q = new Professor("Nome", "868.563.327-34", "123456", "", "Nome@email");
		assertTrue("Teste do E-mail do Professor", p.equals(q));
	}
	
	@Test
	public void testEqualsFalseNome() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "", "Nome@email");
		Professor q = new Professor("NomeDiferente", "868.563.327-34", "12356", "(90) 1234-3344", "Nom@email");
		assertFalse("Teste do E-mail do Professor", p.equals(q));
	}
	@Test
	public void testEqualsFalseCpf() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "", "Nome@email");
		Professor q = new Professor("Nome", "338.688.964-65", "12356", "(90) 1234-3344", "Nom@email");
		assertFalse("Teste do E-mail do Professor", p.equals(q));
	}
	@Test
	public void testEqualsFalseMatricula() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "", "Nome@email");
		Professor q = new Professor("Nome", "868.563.327-34", "12356", "(90) 1234-3344", "Nom@email");
		assertFalse("Teste do E-mail do Professor", p.equals(q));
	}
	@Test
	public void testEqualsFalseTelefone() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "", "Nome@email");
		Professor q = new Professor("Nome", "868.563.327-34", "123456", "(90) 1234-3344", "Nom@email");
		assertFalse("Teste do E-mail do Professor", p.equals(q));
	}
	@Test
	public void testEqualsFalseEmail() throws ClienteException {
		Professor p = new Professor("Nome", "868.563.327-34", "123456", "", "Nome@email");
		Professor q = new Professor("Nome", "868.563.327-34", "123456", "", "Nom@el");
		assertFalse("Teste do E-mail do Professor", p.equals(q));
	}
}
