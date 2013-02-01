package test.persistence;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import model.Aluno;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import exception.ClienteException;

import persistence.FactoryConnection;
import persistence.AlunoDAO;

public class AlunoDAOTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	
	@Test
	public void testInstance() {
		assertTrue("Instanciando AlunoDAO", AlunoDAO.getInstance() instanceof AlunoDAO);
	}
	
	@Test
	public void testSingleton() {
		AlunoDAO p = AlunoDAO.getInstance();
		AlunoDAO q = AlunoDAO.getInstance();
		assertSame("Testando o Padrao Singleton", p, q);
	}
	
	
	
	@Test
	public void testIncluir() throws ClienteException, SQLException {
		Aluno p = new Aluno("TestInclusao", "040.757.021-70", "1234", "9999-9999", "Nome@email");
		AlunoDAO.getInstance().incluir(p);
		assertTrue("Testando Inclusao no Banco", AlunoDAO.getInstance().inDB(p));
	}
	@Test
	public void testBuscarTodos() throws SQLException, ClienteException {
		Vector<Aluno> busca = AlunoDAO.getInstance().buscarTodos();
		assertFalse("Testando a busca de elementos no BD.", busca.isEmpty());
	}
	
	@Test
	public void testAlterar() throws ClienteException, SQLException {
		Aluno p = new Aluno("TestInclusao", "040.757.021-70", "1234", "9999-9999", "Nome@email");
		Aluno pn = new Aluno("TestAlterar", "040.757.021-70", "00000", "(999)9999-9999", "email@Nome");
		AlunoDAO.getInstance().alterar(p, pn);
		assertTrue("Testando Alteracao no Banco", AlunoDAO.getInstance().inDB(pn));
	}
	
	@Test
	public void testExcluirExistente() throws ClienteException, SQLException {
		Aluno p = new Aluno("TestAlterar", "040.757.021-70", "00000", "(999)9999-9999", "email@Nome");
		AlunoDAO.getInstance().excluir(p);
		assertTrue("Testando Exclusao no Banco", !AlunoDAO.getInstance().inDB(p));
	}
	
	
	
	@Test (expected= ClienteException.class)
	public void testIncluirExistente() throws ClienteException, SQLException {
		Aluno p = new Aluno("TestInclusao", "040.757.021-70", "1234", "9999-9999", "Nome@email");
		AlunoDAO.getInstance().incluir(p);
		AlunoDAO.getInstance().incluir(p);
	}
	
	@Test (expected= ClienteException.class)
	public void testAlterarNaoExistente() throws ClienteException, SQLException {
		Aluno p = new Aluno("AlterarNaoExistente", "040.757.021-70", "1234", "9999-9999", "Nome@email");
		Aluno pa = new Aluno("Alterar", "040.757.021-70", "1234", "9999-9999", "Nome@email");
		AlunoDAO.getInstance().alterar(p, pa);
	}
	
	@Test (expected= ClienteException.class)
	public void testAlterarParaExistente() throws ClienteException, SQLException {
		Aluno p = new Aluno("TestInclusao", "040.757.021-70", "1234", "9999-9999", "Nome@email");
		Aluno pa = new Aluno("TestInclusao", "040.757.021-70", "1234", "9999-9999", "Nome@email");
		AlunoDAO.getInstance().alterar(p, pa);
	}
	
	@Test (expected= ClienteException.class)
	public void testExcluirNaoExistente() throws ClienteException, SQLException {
		Aluno p = new Aluno("TestInclusao", "040.757.021-70", "1234", "8888-8888", "Nome@email");
		AlunoDAO.getInstance().excluir(p);
		AlunoDAO.getInstance().excluir(p);
	}

}
