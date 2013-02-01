/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Parley
 */
public class EquipamentoDAOTest {
	

	static EquipamentoDAO instance;
	
	public EquipamentoDAOTest() {
	}
	
	@BeforeClass
	public static void setUpClass() throws PatrimonioException, SQLException {
		instance = EquipamentoDAO.getInstance();
	}
	
	@AfterClass
	public static void tearDownClass() throws SQLException, PatrimonioException {
		instance = null;
	}
	
	@Test
	public void testInstance() {
		assertTrue("Instanciando EquipamentoDAO",instance instanceof EquipamentoDAO);
	}
	
	@Test
	public void testSingleton() {
		EquipamentoDAO inst = EquipamentoDAO.getInstance();
		assertSame("Testando o Padrao Singleton", instance, inst);
	}
	
	@Test
	public void testIncluir() throws PatrimonioException, SQLException {
		Equipamento antigo = new Equipamento("codigo", "descricao - antigo");
		instance.incluir(antigo);
		assertTrue("Testando Inclusao no Banco", instance.inDB(antigo));
	}
	@Test
	public void testBuscarTodos() throws SQLException, PatrimonioException {
		Vector<Equipamento> busca = instance.buscarTodos();
		assertNotNull("Testando a busca de elementos no BD.", busca);
	}
	
	@Test
	public void testAlterar() throws PatrimonioException, SQLException {
		Equipamento antigo = new Equipamento("codigo", "descricao - antigo");
		Equipamento novo = new Equipamento("codigo", "descricao - alterada");
		instance.alterar(antigo, novo);
		assertTrue("Testando Alteracao no Banco", instance.inDB(novo));
	}
	
	@Test (expected= PatrimonioException.class)
	public void testIncluirExistente() throws PatrimonioException, SQLException {
		Equipamento novo = new Equipamento("codigo", "descricao - alterada");
		instance.incluir(novo);
		instance.incluir(novo);
	}
	
	@Test (expected= PatrimonioException.class)
	public void testAlterarNaoExistente() throws PatrimonioException, SQLException {
		Equipamento equip = new Equipamento("codigo", "eqpt nao existente");
		Equipamento equipAlter = new Equipamento("codigo", "eqpt nao existente alteraddo");
		instance.alterar(equip, equipAlter);
	}
	
	@Test (expected= PatrimonioException.class)
	public void testAlterarIgual() throws PatrimonioException, SQLException {
		Equipamento novo = new Equipamento("codigo", "descricao - alterada");
		instance.alterar(novo, novo);
	}
	
	@Test (expected= PatrimonioException.class)
	public void testExcluirNaoExistente() throws PatrimonioException, SQLException {
		Equipamento eq = new Equipamento("codigo"," nao existe descricao");
		instance.excluir(eq);
		instance.excluir(eq);
	}
	
	@Test
	public void testExcluirExistente() throws PatrimonioException, SQLException {

		//Equipamento antigo = new Equipamento("codigo", "descricao - antigo");
		Equipamento novo = new Equipamento("codigo", "descricao - alterada");
		instance.excluir(novo);
		//instance.excluir(antigo);
		assertFalse("Testando Exclusao no Banco", instance.inDB(novo));
	}
	
}
