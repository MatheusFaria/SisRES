package test.persistence;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Vector;

import model.Equipamento;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.EquipamentoDAO;
import exception.PatrimonioException;


public class EquipamentoDAOTest {
	
	@BeforeClass
	public static void setUpClass() throws PatrimonioException, SQLException {
	}
	
	@AfterClass
	public static void tearDownClass() throws SQLException, PatrimonioException {
	}
	
	@Test
	public void testInstance() {
		assertTrue("Instanciando EquipamentoDAO", EquipamentoDAO.getInstance() instanceof EquipamentoDAO);
	}
	
	@Test
	public void testSingleton() {
		EquipamentoDAO inst1 = EquipamentoDAO.getInstance();
		EquipamentoDAO inst2 = EquipamentoDAO.getInstance();
		assertSame("Testando o Padrao Singleton", inst2, inst1);
	}
	
	@Test
	public void testIncluir() throws PatrimonioException, SQLException {
		Equipamento antigo = new Equipamento("codigo", "descricao - antigo");
		EquipamentoDAO.getInstance().incluir(antigo);
		assertTrue("Testando Inclusao no Banco", EquipamentoDAO.getInstance().inDB(antigo));
	}
	@Test
	public void testBuscarTodos() throws SQLException, PatrimonioException {
		Vector<Equipamento> busca = EquipamentoDAO.getInstance().buscarTodos();
		assertNotNull("Testando a busca de elementos no BD.", busca);
	}
	
	@Test
	public void testAlterar() throws PatrimonioException, SQLException {
		Equipamento antigo = new Equipamento("codigo", "descricao - antigo");
		Equipamento novo = new Equipamento("codigo", "descricao - alterada");
		EquipamentoDAO.getInstance().alterar(antigo, novo);
		assertTrue("Testando Alteracao no Banco", EquipamentoDAO.getInstance().inDB(novo));
	}
	
	@Test (expected= PatrimonioException.class)
	public void testIncluirExistente() throws PatrimonioException, SQLException {
		Equipamento novo = new Equipamento("codigo", "descricao - alterada");
		EquipamentoDAO.getInstance().incluir(novo);
		EquipamentoDAO.getInstance().incluir(novo);
	}
	
	@Test (expected= PatrimonioException.class)
	public void testAlterarNaoExistente() throws PatrimonioException, SQLException {
		Equipamento equip = new Equipamento("codigo", "eqpt nao existente");
		Equipamento equipAlter = new Equipamento("codigo", "eqpt nao existente alteraddo");
		EquipamentoDAO.getInstance().alterar(equip, equipAlter);
	}
	
	@Test (expected= PatrimonioException.class)
	public void testAlterarIgual() throws PatrimonioException, SQLException {
		Equipamento novo = new Equipamento("codigo", "descricao - alterada");
		EquipamentoDAO.getInstance().alterar(novo, novo);
	}
	
	@Test (expected= PatrimonioException.class)
	public void testExcluirNaoExistente() throws PatrimonioException, SQLException {
		Equipamento eq = new Equipamento("codigo"," nao existe descricao");
		EquipamentoDAO.getInstance().excluir(eq);
		EquipamentoDAO.getInstance().excluir(eq);
	}
	
	@Test
	public void testExcluirExistente() throws PatrimonioException, SQLException {
		Equipamento novo = new Equipamento("codigo", "descricao - alterada");
		EquipamentoDAO.getInstance().excluir(novo);
		assertFalse("Testando Exclusao no Banco", EquipamentoDAO.getInstance().inDB(novo));
	}
	
}
