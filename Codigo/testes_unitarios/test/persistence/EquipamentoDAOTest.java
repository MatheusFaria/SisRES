package test.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import model.Equipamento;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import persistence.EquipamentoDAO;
import exception.PatrimonioException;


public class EquipamentoDAOTest {
	static EquipamentoDAO instance;
	Equipamento antigo, novo;
	Vector <Equipamento> todos;
	
	@BeforeClass
	public static void setUpClass() throws PatrimonioException, SQLException {
		instance = EquipamentoDAO.getInstance();
	}
	
	@AfterClass
	public static void tearDownClass() throws SQLException, PatrimonioException {
		instance = null;
	}
	
	@Before
	public void setUp() throws PatrimonioException, SQLException {
		 antigo = new Equipamento("codigo", "descricao - antigo");
		 novo = new Equipamento("codigo", "descricao - alterada");
		 instance.incluir(antigo);
		 todos = instance.buscarTodos();
	}
	
	@After
	public void tearDown() throws SQLException, PatrimonioException {
		todos = instance.buscarTodos();
		Iterator<Equipamento> i = todos.iterator();
		while(i.hasNext()){
			Equipamento e = i.next();
			instance.excluir(e);
		}
		antigo = null;
		novo = null;
	}
	
	@Test
	public void testInstance() {
		assertTrue("Instanciando EquipamentoDAO", instance instanceof EquipamentoDAO);
	}
	
	@Test
	public void testSingleton() {
		EquipamentoDAO inst1 = EquipamentoDAO.getInstance();
		EquipamentoDAO inst2 = EquipamentoDAO.getInstance();
		assertSame("Testando o Padrao Singleton", inst2, inst1);
	}
	
	@Test
	public void testIncluir() throws PatrimonioException, SQLException {
		assertNotNull("Equipamento nao foi incluido", procurarNoVetor(antigo));
	}
	@Test
	public void testBuscarTodos() throws SQLException, PatrimonioException {
		assertNotNull("Testando a busca de elementos no BD.", todos);
	}
	
	@Test
	public void testBuscarPorCodigo() throws SQLException, PatrimonioException {
		assertNotNull("Testando a busca por codigo de elementos no BD.", instance.buscarPorCodigo(antigo.getCodigo()));
	}
	
	@Test
	public void testBuscarPorDescricao() throws SQLException, PatrimonioException {
		assertNotNull("Testando a busca por descricao de elementos no BD.", instance.buscarPorDescricao(antigo.getDescricao()));
	}
	
	@Test
	public void testBuscarPorCodigoNull() throws SQLException, PatrimonioException {
		assertTrue("Testando a busca por codigo nulo de elementos no BD.", instance.buscarPorCodigo(null).isEmpty());
	}
	
	@Test
	public void testBuscarPorDescricaoNull() throws SQLException, PatrimonioException {
		assertTrue("Testando a busca por descricao nula de elementos no BD.", instance.buscarPorDescricao(null).isEmpty());
	}
	
	@Test
	public void testAlterar() throws PatrimonioException, SQLException {
		instance.alterar(antigo, novo);
		Equipamento e = procurarNoVetor(antigo);
		assertNull("Equipamento nao foi alterado", e);
		assertNotNull("Equipamento nao foi alterado", procurarNoVetor(novo));
	}
	
	@Test (expected= PatrimonioException.class)
	public void testIncluirComCodigoExistente() throws PatrimonioException, SQLException {
		instance.incluir(antigo);
	}
	
	@Test (expected= PatrimonioException.class)
	public void testIncluirNulo() throws PatrimonioException, SQLException {
		instance.incluir(null);
	}
	
	@Test (expected= PatrimonioException.class)
	public void testAlterarNull() throws PatrimonioException, SQLException {
		instance.alterar(null, null);
	}
	
	@Test (expected= PatrimonioException.class)
	public void testAlterarSegundoNull() throws PatrimonioException, SQLException {
		instance.alterar(antigo, null);
	}
	
	
	@Test (expected= PatrimonioException.class)
	public void testAlterarNaoExistente() throws PatrimonioException, SQLException {
		Equipamento equip = new Equipamento("codigo", "eqpt nao existente");
		Equipamento equipAlter = new Equipamento("codigo", "eqpt nao existente alteraddo");
		instance.alterar(equip, equipAlter);
	}
	
	@Test (expected= PatrimonioException.class)
	public void testAlterarIgual() throws PatrimonioException, SQLException {
		instance.alterar(novo, novo);
	}
	
	@Test (expected= PatrimonioException.class)
	public void testAlterarParaOutroEquipamento() throws PatrimonioException, SQLException {
		Equipamento e = new Equipamento("novo", "teste Alterar para outro");
		instance.incluir(e);
		instance.alterar(e, novo);
	}
	
	@Test (expected= PatrimonioException.class)
	public void testExcluirNull() throws PatrimonioException, SQLException {
		instance.excluir(null);
	}
	
	@Test (expected= PatrimonioException.class)
	public void testExcluirNaoExistente() throws PatrimonioException, SQLException {
		Equipamento eq = new Equipamento("codigo"," nao existe descricao");
		instance.excluir(eq);
	}
	
	@Test
	public void testExcluirExistente() throws PatrimonioException, SQLException {
		Equipamento novoExclusao = new Equipamento("cdg", "teste exclusao");
		instance.incluir(novoExclusao);
		instance.excluir(novoExclusao);
		assertNull("Equipamento nao foi alterado", procurarNoVetor(novoExclusao));
	}
	
	public Equipamento procurarNoVetor(Equipamento teste) throws PatrimonioException, SQLException {
		todos = instance.buscarTodos();
		Iterator<Equipamento> i = todos.iterator();
		while(i.hasNext()){
			Equipamento e = i.next();
			if(e.equals(teste))
				return e;			
		}
		return null;
	}
	
}
