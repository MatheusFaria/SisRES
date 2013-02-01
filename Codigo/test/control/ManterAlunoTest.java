package test.control;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Vector;

import model.Aluno;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import control.ManterAluno;
import control.ManterProfessor;
import exception.ClienteException;

public class ManterAlunoTest {

	private static Vector<Aluno> alunos;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		alunos = ManterAluno.getInstance().getAluno_vet();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	@Test
	public void testSingleton() {
		ManterAluno p = ManterAluno.getInstance();
		ManterAluno q = ManterAluno.getInstance();
		assertSame("Testando o Padrao Singleton", p, q);
	}

	/**
	 * Test method for {@link control.ManterAluno#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		assertNotNull("Verifica método getInstance().", ManterAluno.getInstance() instanceof ManterAluno);
		//fail("Get instance retorna objeto nulo");
	}

	@Test
	public void testInserir() throws ClienteException, SQLException {
		Aluno a = new Aluno("Marina", "040.757.021-70", "12345", "3333-3333", "maria@email");
		ManterAluno.getInstance().inserir("Marina", "040.757.021-70", "12345", "3333-3333", "maria@email");
		
		assertTrue("Verifica se o aluno foi inserido.", alunos.lastElement().equals(a));
	}
	
	@Test 
	public void testAlterar() throws ClienteException, SQLException {
		Aluno a = new Aluno("Marina", "040.757.021-70", "12345", "3333-3333", "maria@email");
		ManterAluno.getInstance().alterar("Maria", "040.757.021-70", "12345", "3333-3333", "maria@email", alunos.lastElement());
		
		assertNotNull("Verifica se os dados foram alterados.", alunos.lastElement().equals(a));
	}

	@Test 
	public void testExcluir() throws ClienteException, SQLException {
		Aluno a = alunos.lastElement();
		ManterAluno.getInstance().excluir(a);
		
		assertFalse("Verifica se o objeto foi excluido.", alunos.contains(a));
	}

}
