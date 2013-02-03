package test.control;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Vector;

import model.Aluno;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import control.ManterAluno;
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
	public void testGetInstance() {
		assertTrue("Verifica método getInstance().", ManterAluno.getInstance() instanceof ManterAluno);
	}

	@Test
	public void testSingleton() {
		ManterAluno p = ManterAluno.getInstance();
		ManterAluno q = ManterAluno.getInstance();
		assertSame("Testando o Padrao Singleton", p, q);
	}

	
	
	@Test
	public void testInserir() throws ClienteException, SQLException {
		Aluno a = new Aluno("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		ManterAluno.getInstance().inserir("Nome para Incluir", "868.563.327-34", "123456", "1234-5678", "Nome@email");
		
		assertTrue("Verifica se o aluno foi inserido.", alunos.lastElement().equals(a));
	}
	
	@Test 
	public void testAlterar() throws ClienteException, SQLException {
		Aluno a = new Aluno("Nome para Alterar",  "868.563.327-34", "123456", "1234-5678", "Nome@email");
		ManterAluno.getInstance().alterar("Nome para Alterar",  "868.563.327-34", "123456", "1234-5678", "Nome@email", alunos.lastElement());
		
		assertNotNull("Verifica se os dados foram alterados.", alunos.lastElement().equals(a));
	}

	@Test 
	public void testExcluir() throws ClienteException, SQLException {
		Aluno a = alunos.lastElement();
		ManterAluno.getInstance().excluir(a);
		
		assertFalse("Verifica se o objeto foi excluido.", alunos.contains(a));
	}

}
