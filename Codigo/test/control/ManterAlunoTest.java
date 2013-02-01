/**
 * 
 */
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

import persistence.AlunoDAO;

import control.ManterAluno;
import exception.ClienteException;

/**
 * @author Beatriz
 *
 */
public class ManterAlunoTest {

	ManterAluno instance;
	Vector<Aluno> alunos;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		instance = null;
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
		ManterAluno instancia = ManterAluno.getInstance();
		assertNotNull("Verifica se o metodo getInstance() retorna um objeto nulo.", instancia);
		//fail("Get instance retorna objeto nulo");
	}

	/**
	 * Test method for {@link control.ManterAluno#getAluno_vet()}.
	 * @throws ClienteException 
	 * @throws SQLException 
	 */
	@Test
	public void testGetAluno_vet() throws SQLException, ClienteException {
		instance = ManterAluno.getInstance();
		alunos = instance.getAluno_vet();
		assertNotNull("Verifica o método getAlunos_vet().", alunos);
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link control.ManterAluno#inserir(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws SQLException 
	 * @throws ClienteException 
	 */
	@Test
	public void testInserirDadosCorretos() throws ClienteException, SQLException {
		instance = ManterAluno.getInstance();
		instance.inserir("Marina", "040.757.021-70", "12345", "3333-3333", "maria@email");
		Aluno a = new Aluno("Marina", "040.757.021-70", "12345", "3333-3333", "maria@email");
		alunos = instance.getAluno_vet();
		assertTrue("Verifica se o aluno foi inserido.", alunos.contains(a));
		//fail("Not yet implemented");
	}
	/**
	 * Test method for {@link control.ManterAluno#alterar(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, model.Aluno)}.
	 * @throws ClienteException 
	 * @throws SQLException 
	 */
	@Test 
	public void testAlterar() throws ClienteException, SQLException {
		instance = ManterAluno.getInstance();
		Aluno a = new Aluno("Marina", "040.757.021-70", "12345", "3333-3333", "maria@email");
		instance.alterar("Nome", "040.757.021-70", "123456", "1234-5678", "Nome@email", a);
		a = new Aluno("Nome", "040.757.021-70", "123456", "1234-5678", "Nome@email");
		alunos = instance.getAluno_vet();
		assertNotNull("Verifica se os dados foram alterados.", alunos.contains(a));
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link control.ManterAluno#excluir(model.Aluno)}.
	 * @throws ClienteException 
	 * @throws SQLException 
	 */
	@Test 
	public void testExcluir() throws ClienteException, SQLException {
		instance = ManterAluno.getInstance();
		Aluno a = new Aluno("Marina", "040.757.021-70", "12345", "3333-3333", "maria@email");
		instance.excluir(a);
		alunos = instance.getAluno_vet();
		assertFalse("Verifica se o objeto foi excluido.", alunos.contains(a));
		//fail("Not yet implemented");
	}
	
	@Test (expected= NullPointerException.class)
	public void testExcluir2() throws ClienteException, SQLException {
		instance = ManterAluno.getInstance();
		Aluno a = null;
		instance.excluir(a);
		alunos = instance.getAluno_vet();
		//fail("Not yet implemented");
	}

}
