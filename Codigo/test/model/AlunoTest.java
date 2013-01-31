package test.model;

import static org.junit.Assert.*;

import model.Aluno;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import exception.ClienteException;

public class AlunoTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	

	@Test
	public void testInstance() throws ClienteException {
		Aluno p = new Aluno("Nome", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		assertTrue("Teste de Instanciamento do Aluno", p instanceof Aluno);
	}
	
	@Test
	public void testMatricula() throws ClienteException {
		Aluno p = new Aluno("Nome", "868.563.327-34", "12/0034564", "1234-5678", "Nome@email");
		assertTrue("Teste da Matricula do Aluno", "12/0034564" == p.getMatricula());
	}
	
	
	
	@Test (expected= ClienteException.class)
	public void testMatriculaVazia() throws ClienteException {
		new Aluno("Nome", "868.563.327-34", "", "1234-5678", "Nome@email");
	}
	
	@Ignore// (expected= ClienteException.class)
	public void testMatriculaDespadronizada() throws ClienteException {
		new Aluno("Nome", "868.563.327-34", "120034564", "1234-5678", "Nome@email");
	}
	
	@Test (expected= ClienteException.class)
	public void testMatriculaNula() throws ClienteException {
		new Aluno("Nome", "868.563.327-34", null, "1234-5678", "Nome@email");
	}


}
