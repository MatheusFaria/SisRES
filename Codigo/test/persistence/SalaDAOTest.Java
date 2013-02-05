package test.persistence;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Vector;

import model.Sala;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.SalaDAO;
import exception.PatrimonioException;


public class SalaDAOTest {
  
	@BeforeClass
	public static void setUpClass() throws PatrimonioException, SQLException {
	
	}
	
	@AfterClass
	public static void tearDownClass() throws SQLException, PatrimonioException {
		
	}	
	
	@Test
	public void testInstance() {
		assertTrue("Instanciando SalaDAO", SalaDAO.getInstance() instanceof SalaDAO);
	}
	
	@Test
	public void testSingleton() {
		SalaDAO inst1 = SalaDAO.getInstance();
		SalaDAO inst2 = SalaDAO.getInstance();
		assertSame("Testando o Padrao Singleton", inst2, inst1);
	}
	
	@Test
	public void testIncluir() throws PatrimonioException, SQLException {
		Sala antigo = new Sala("codigo", "descricao", "capacidade");
		SalaDAO.getInstance().incluir(antigo);
		assertTrue("Testando Inclusao no Banco", SalaDAO.getInstance().inDB(antigo));
	}
	
	@Test
	public void testBuscarTodos() throws SQLException, PatrimonioException {
		Vector<Sala> busca = SalaDAO.getInstance().buscarTodos();
		assertNotNull("Testando a busca de elementos no BD.", busca);
	}
	
	@Test
	public void testAlterar() throws PatrimonioException, SQLException {
		Sala antigo = new Sala("codigo", "descricao", "capacidade");
		Sala novo = new Sala("codigo", "descricao - alterada", "capacidade - alterada");
		SalaDAO.getInstance().alterar(antigo, novo);
		assertTrue("Testando Alteracao no Banco", SalaDAO.getInstance().inDB(novo));
	}
	
	@Test (expected= PatrimonioException.class)
	public void testIncluirExistente() throws PatrimonioException, SQLException {
		Sala novo = new Sala("codigo", "descricao", "capacidade");
		SalaDAO.getInstance().incluir(novo);
		SalaDAO.getInstance().incluir(novo);
	}
	
	@Test (expected= PatrimonioException.class)
	public void testAlterarNaoExistente() throws PatrimonioException, SQLException {
		Sala equip = new Sala("codigo444", "descricao444", "capacidade444");
		Sala equipAlter = new Sala("codigo", "descricao", "capacidade");
		SalaDAO.getInstance().alterar(equip, equipAlter);
	}
	
	@Test (expected= PatrimonioException.class)
	public void testAlterarIgual() throws PatrimonioException, SQLException {
		Sala novo = new Sala("codigo", "descricao", "capacidade");
		SalaDAO.getInstance().alterar(novo, novo);
	}

	@Test (expected= PatrimonioException.class)
	public void testExcluirNaoExistente() throws PatrimonioException, SQLException {
		Sala eq = new Sala("codigo", "descricao", "capacidade");
		SalaDAO.getInstance().excluir(eq);
		SalaDAO.getInstance().excluir(eq);
	}
}
