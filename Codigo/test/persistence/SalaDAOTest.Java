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

	private Sala sala, sala_new;
	private Vector<Sala> busca;	
	
	@BeforeClass
	public static void setUpClass() throws PatrimonioException, SQLException {
	
	}
	
	@AfterClass
	public static void tearDownClass() throws SQLException, PatrimonioException {
		
	}	
	
	@Before
	public void setUp() throws SQLException, PatrimonioException {
		sala = new Sala("codigo", "descricao", "1");
		
	}
	
	//criar outros testes de alterar.
	@Before
	public void setUpAlterar() throws SQLException, PatrimonioException {
		sala = new Sala("codigo_old", "descricao", "1");
		sala_new = new Sala("codigo", "descricao", "2");
		this.executaNoBanco("INSERT INTO " +
				"sala (codigo, descricao, capacidade) VALUES (" +
				"\"" + sala.getCodigo() + "\", " +
				"\"" + sala.getDescricao() + "\", " +
				"\"" + sala.getCapacidade() + "\"); ");
	}
	
	@After
	public void tearDownAlterar() throws SQLException, PatrimonioException {
		this.executaNoBanco("DELETE FROM sala WHERE " +
					"sala.codigo = \"" + sala_new.getCodigo() + "\" and " +
					"sala.descricao = \"" + sala_new.getDescricao() +  "\" and " +
					"sala.capacidade = \"" + sala_new.getCapacidade() + "\";"
					);
		this.executaNoBanco("DELETE FROM sala WHERE " +
					"sala.codigo = \"" + sala.getCodigo() + "\" and " +
					"sala.descricao = \"" + sala.getDescricao() +  "\" and " +
					"sala.capacidade = \"" + sala.getCapacidade() + "\";"
					);				
	}

	@After
	public void tearDown() throws SQLException, PatrimonioException {
		sala = null;
		sala_new = null;
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
		setUp();
		SalaDAO.getInstance().incluir(sala);
		assertTrue("Testando Inclusao no Banco", this.inDB(sala));
		tearDownAlterar();
	}
	
	@Test (expected= PatrimonioException.class)
	public void testIncluirExistente() throws PatrimonioException, SQLException {
		setUp();
		SalaDAO.getInstance().incluir(sala);
		SalaDAO.getInstance().incluir(sala);
		tearDownAlterar();
	}
	
	@Test (expected= PatrimonioException.class)
	public void testAlterarPrimeiroNulo() throws PatrimonioException, SQLException {
		setUp();
		SalaDAO.getInstance().alterar(null ,sala);
		tearDownAlterar();
	}

	@Test (expected= PatrimonioException.class)
	public void testAlterarSegundoNulo() throws PatrimonioException, SQLException {
		setUpAlterar();
		SalaDAO.getInstance().alterar(sala, null);
		tearDownAlterar();
	}

	@Test
	public void testBuscarTodos() throws SQLException, PatrimonioException {
		Vector<Sala> busca = SalaDAO.getInstance().buscarTodos();
		assertNotNull("Testando a busca de elementos no BD.", busca);
	}

	@Test
	public void testAlterar() throws PatrimonioException, SQLException {
		setUpAlterar();
		SalaDAO.getInstance().alterar(sala, sala_new);
		assertTrue("Testando Alteracao no Banco", this.inDB(sala_new));
		tearDownAlterar();
	}
	
	@Test
	public void testAlterarCodigo() throws PatrimonioException, SQLException {
		setUpAlterar();
		sala_new = new Sala("qazwsx", "descricao", "1");
		SalaDAO.getInstance().alterar(sala, sala_new);
		assertTrue("Testando Alteracao no Banco", this.inDB(sala_new));
		tearDownAlterar();
	}

	@Test
	public void testAlterarDescricao() throws PatrimonioException, SQLException {
		setUpAlterar();
		sala_new = new Sala("codigo", "edcrfv", "1");
		SalaDAO.getInstance().alterar(sala, sala_new);
		assertTrue("Testando Alteracao no Banco", this.inDB(sala_new));
		tearDownAlterar();
	}

	@Test
	public void testAlterarCapacidade() throws PatrimonioException, SQLException {
		setUpAlterar();
		sala_new = new Sala("codigo", "descricao", "2");
		SalaDAO.getInstance().alterar(sala, sala_new);
		assertTrue("Testando Alteracao no Banco", this.inDB(sala_new));
		tearDownAlterar();
	}
	
	@Test (expected= PatrimonioException.class)
	public void testIncluirExistente() throws PatrimonioException, SQLException {
		Sala novo = new Sala("codigo", "descricao", "capacidade");
		SalaDAO.getInstance().incluir(novo);
		SalaDAO.getInstance().incluir(novo);
	}
	
	@Test (expected= PatrimonioException.class)
	public void testAlterarNaoExistente() throws PatrimonioException, SQLException {
		setUp();
		Sala sala_null = new Sala("tyui", "ghjk", "5");
		SalaDAO.getInstance().alterar(sala_null, sala);
	}
	
	@Test (expected= PatrimonioException.class)
	public void testAlterarIgual() throws PatrimonioException, SQLException {
		setUpAlterar();
		SalaDAO.getInstance().alterar(sala, sala);
	}
	
	@Test (expected= PatrimonioException.class)
	public void testExcluirNaoExistente() throws PatrimonioException, SQLException {
		setUp();
		SalaDAO.getInstance().excluir(sala);
	}
	
	@Test
	public void testExcluirExistente() throws PatrimonioException, SQLException {
		setUpAlterar();
		SalaDAO.getInstance().excluir(sala);
		assertFalse("Testando Exclusao no Banco", this.inDB(sala));
		tearDownAlterar();
	}
	
}
